package com.org.example.library_management_system.controller;

import com.org.example.library_management_system.entity.Book;
import com.org.example.library_management_system.entity.BorrowRecord;
import com.org.example.library_management_system.entity.Member;
import com.org.example.library_management_system.service.BookService;
import com.org.example.library_management_system.service.BorrowService;
import com.org.example.library_management_system.service.MemberService;




import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/borrow")
public class BorrowRecordController {
    private final BorrowService service;
    private final BookService bookService;
    private final MemberService memberService;

    public BorrowRecordController(BorrowService service, BookService bookService, MemberService memberService){
        this.service = service;
        this.bookService = bookService;
        this.memberService = memberService;
    }

    @GetMapping
    public String list(Model model){
        model.addAttribute("records", service.getAllRecord());
        return "borrow/list";
    }

    @GetMapping("/new")
    public String add(Model model){
        model.addAttribute("books", bookService.findAll());
        model.addAttribute("members", memberService.findAll());
        return "borrow/form";
    }

    @PostMapping({"/issue"})
    public String issue(@RequestParam Long bookId, Long memberId, RedirectAttributes redirectAttributes) {
        try {
            this.service.issueBook(bookId, memberId);
            redirectAttributes.addFlashAttribute("message", "Book issued successfully");
            return "redirect:/borrow";
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            throw new RuntimeException(e);
        }
    }
    @GetMapping("return/{id}")
    public String returnBook(@PathVariable Long id, RedirectAttributes redirectAttributes){
        service.returnBook(id);
        redirectAttributes.addFlashAttribute("message", "Book returned");

        return "redirect:/borrow";
    }


    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes redirectAttributes){
        service.delete(id);
        redirectAttributes.addFlashAttribute("message", "Borrow record deleted");
        return "redirect:/borrow";
    }
}

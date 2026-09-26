package com.org.example.library_management_system.controller;

import com.org.example.library_management_system.service.BookService;
import com.org.example.library_management_system.service.BorrowService;
import com.org.example.library_management_system.service.CategoryService;
import com.org.example.library_management_system.service.MemberService;
import com.org.example.library_management_system.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class HomeController {
    private final BookService bookService;
    private final MemberService memberService;
    private final BorrowService borrowService;
    private final CategoryService categoryService;
    private final UserService userService;

    public HomeController(BookService bookService,
                          MemberService memberService,
                          BorrowService borrowService,
                          CategoryService categoryService,
                          UserService userService) {
        this.bookService = bookService;
        this.memberService = memberService;
        this.borrowService = borrowService;
        this.categoryService = categoryService;
        this.userService = userService;
    }

    @GetMapping("/")
    public String home(Principal principal, Model model) {
        int bookCount = bookService.findAll().size();
        int memberCount = memberService.findAll().size();
        int borrowCount = borrowService.getAllRecord().size();
        int categoryCount = categoryService.getAllCategory().size();

        model.addAttribute("bookCount", bookCount);
        model.addAttribute("memberCount", memberCount);
        model.addAttribute("borrowCount", borrowCount);
        model.addAttribute("categoryCount", categoryCount);

        if (principal != null) {
            userService.findByEmail(principal.getName()).ifPresent(user -> model.addAttribute("user", user));
        }

        return "index";
    }
}

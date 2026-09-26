package com.org.example.library_management_system.controller;

import com.org.example.library_management_system.entity.Book;
import com.org.example.library_management_system.service.BookService;
import com.org.example.library_management_system.service.CategoryService;


import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/books")
public class BookController {
    private final BookService service;
    private final CategoryService categoryService;

    public BookController(BookService service, CategoryService categoryService){
        this.categoryService = categoryService;
        this.service = service;
    }


    @GetMapping
    public String listBooks(@RequestParam(value = "q",required = false) String q, Model model) {
        model.addAttribute("books", this.service.search(q));
        model.addAttribute("q", q);
        return "book/list";
    }

    @GetMapping("/new")
    public String addBook(Model model){
        model.addAttribute("book", new Book());
        model.addAttribute("allCategories", categoryService.getAllCategory());
        return "book/form";
    }

    @GetMapping("/edit/{id}")
    public String editBook(@PathVariable Long id, Model model){
        model.addAttribute("book", service.findById(id));
        model.addAttribute("allCategories", categoryService.getAllCategory());
        return "book/form";
    }

    @PostMapping({"/save"})
    public String saveBook(@ModelAttribute("book") @Valid Book book, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "book/form";
        } else {
            this.service.save(book);
            redirectAttributes.addFlashAttribute("message", "Book saved successfully");
            return "redirect:/books";
        }
    }
    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable Long id, RedirectAttributes redirectAttributes){
        service.delete(id);
        redirectAttributes.addFlashAttribute("message", "Book deleted");
        return "redirect:/books";
    }
}

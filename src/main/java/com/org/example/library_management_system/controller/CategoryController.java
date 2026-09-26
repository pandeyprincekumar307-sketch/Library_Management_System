package com.org.example.library_management_system.controller;

import com.org.example.library_management_system.entity.Category;
import com.org.example.library_management_system.service.CategoryService;



import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/categories")
public class CategoryController {
    private final CategoryService service;

    public CategoryController(CategoryService service){
        this.service = service;
    }

    @GetMapping
    public String list(Model model){
        model.addAttribute("categories", service.getAllCategory());
        return "category/list";
    }

    @GetMapping("/new")
    public String add(Model model){
        model.addAttribute("category", new Category());
        return "category/form";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model){
        model.addAttribute("category", service.findById(id));
        return "category/form";
    }

    @PostMapping("/save")
    public String save(@Valid @ModelAttribute("category") Category category, BindingResult result, RedirectAttributes redirectAttributes){
        if(result.hasErrors()){
            return "category/form";
        }
        service.save(category);
        redirectAttributes.addFlashAttribute("message", "Category saved successfully");

        return "redirect:/categories";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes redirectAttributes){
        service.delete(id);
        redirectAttributes.addFlashAttribute("message", "Category deleted");
        return "redirect:/categories";
    }
}

package com.org.example.library_management_system.controller;

import com.org.example.library_management_system.entity.Member;
import com.org.example.library_management_system.service.MemberService;

import com.org.example.library_management_system.entity.Address;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/member")
public class MemberController {
    private final MemberService service;

    public MemberController(MemberService service){
        this.service = service;
    }

    @GetMapping
    public String members(Model model){
        model.addAttribute("members", service.findAll());
        return "member/list";
    }

    @GetMapping("/new")
    public String addMember(Model model){
        Member member = new Member();
        member.setAddress(new Address());
        model.addAttribute("member",member);
        return "member/form";
    }

    @PostMapping({"/save"})
    public String save(@ModelAttribute("member") @Valid Member member, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "member/form";
        } else {
            this.service.save(member);
            redirectAttributes.addFlashAttribute("message", "Member saved successfully");
            return "redirect:/member";
        }
    }

    @GetMapping("/delete/{id}")
    public String deleteMember(@PathVariable Long id){
        service.delete(id);
        return "redirect:/member";
    }
}

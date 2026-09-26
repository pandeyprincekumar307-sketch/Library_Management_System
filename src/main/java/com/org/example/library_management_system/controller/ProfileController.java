package com.org.example.library_management_system.controller;

import com.org.example.library_management_system.dto.ChangePasswordDto;
import com.org.example.library_management_system.dto.UpdateProfileDto;
import com.org.example.library_management_system.entity.User;
import com.org.example.library_management_system.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

@Controller
@RequestMapping("/profile")
public class ProfileController {

    private final UserService userService;

    public ProfileController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping({"", "/", "/view"})
    public String viewProfile(Principal principal, Model model) {
        if (principal == null) {
            return "redirect:/auth/login";
        }
        User user = userService.findByEmail(principal.getName()).orElse(null);
        if (user == null) {
            return "redirect:/auth/login";
        }

        UpdateProfileDto dto = new UpdateProfileDto();
        dto.setFullName(user.getFullName());
        dto.setEmail(user.getEmail());

        model.addAttribute("user", user);
        model.addAttribute("updateProfileDto", dto);

        return "profile/view";
    }

    @PostMapping("/update")
    public String updateProfile(Principal principal,
                                @Valid @ModelAttribute("updateProfileDto") UpdateProfileDto dto,
                                BindingResult result,
                                Model model,
                                RedirectAttributes ra) {
        if (principal == null) {
            return "redirect:/auth/login";
        }
        User user = userService.findByEmail(principal.getName()).orElse(null);
        if (user == null) {
            return "redirect:/auth/login";
        }

        if (result.hasErrors()) {
            model.addAttribute("user", user);
            return "profile/view";
        }

        try {
            userService.updateProfile(user, dto);
            ra.addFlashAttribute("message", "Profile updated successfully!");
            return "redirect:/profile";
        } catch (IllegalArgumentException ex) {
            result.rejectValue("email", "error.dto", ex.getMessage());
            model.addAttribute("user", user);
            return "profile/view";
        }
    }

    @GetMapping("/change-password")
    public String changePasswordPage(Principal principal, Model model) {
        if (principal == null) {
            return "redirect:/auth/login";
        }
        userService.findByEmail(principal.getName()).ifPresent(user -> model.addAttribute("user", user));
        model.addAttribute("changePasswordDto", new ChangePasswordDto());
        return "profile/change-password";
    }

    @PostMapping("/change-password")
    public String changePassword(Principal principal,
                                 @Valid @ModelAttribute("changePasswordDto") ChangePasswordDto dto,
                                 BindingResult result,
                                 Model model,
                                 RedirectAttributes ra) {
        if (principal == null) {
            return "redirect:/auth/login";
        }
        User user = userService.findByEmail(principal.getName()).orElse(null);
        if (user == null) {
            return "redirect:/auth/login";
        }

        if (result.hasErrors()) {
            model.addAttribute("user", user);
            return "profile/change-password";
        }

        if (!dto.passwordMatch()) {
            result.rejectValue("confirmPassword", "error.dto", "New passwords do not match.");
            model.addAttribute("user", user);
            return "profile/change-password";
        }

        try {
            userService.changePassword(user, dto);
            ra.addFlashAttribute("message", "Password changed successfully. Please sign in again.");
            return "redirect:/auth/login?logout=true";
        } catch (IllegalArgumentException ex) {
            result.rejectValue("currentPassword", "error.dto", ex.getMessage());
            model.addAttribute("user", user);
            return "profile/change-password";
        }
    }
}

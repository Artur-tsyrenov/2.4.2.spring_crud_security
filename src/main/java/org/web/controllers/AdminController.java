package org.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.web.models.Role;
import org.web.models.User;
import org.web.service.UserService;

import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private UserService userService;

    @GetMapping()
    public String getAllUsers(Model model) {
        model.addAttribute("listUsers", userService.listUsers());
        model.addAttribute("roles", userService.findAllRoles());
        return "admin/all";
    }

    @GetMapping("/{id}")
    public String getUser(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userService.read(id));
        return "user/show";
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable("id") int id) {
        userService.delete(id);
        return "redirect:/admin";
    }

    @GetMapping("/{id}/edit")
    public String update(Model model, @PathVariable("id") int id) {
        model.addAttribute("user", userService.read(id));
        return "admin/edit";
    }

    @PatchMapping("/{id}")
    public String updateUser(@ModelAttribute("user") User user, @PathVariable("id") int id) {
        userService.update(id, user);
        return "redirect:/admin";
    }

    @PostMapping
    public String createUser(
            @ModelAttribute(value = "roleAdmin") String roleAdmin,
            @ModelAttribute("user") User user) {

        Set<Role> setRole = new HashSet<>();
        if (roleAdmin.contains("on")) {
            setRole.add(userService.findAllRoles().get(1));
            setRole.add(userService.findAllRoles().get(0));
        } else {
            setRole.add(userService.findAllRoles().get(0));
        }
        user.setRoles(setRole);
        userService.save(user);
        return "redirect:/admin";
    }

    @GetMapping("/create")
    public String newUser(Model model) {
        String roleAdmin = null;
        model.addAttribute("user", new User());
        model.addAttribute("roles", userService.findAllRoles());
        model.addAttribute("roleAdmin", roleAdmin);
        return "admin/new";
    }
}

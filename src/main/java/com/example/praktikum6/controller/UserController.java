package com.example.praktikum6.controller;

import com.example.praktikum6.model.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {

    // ===================== ROOT =====================
    @GetMapping("/")
    public String index() {
        return "redirect:/login";
    }

    // ===================== LOGIN =====================
    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String loginProcess(@RequestParam String username,
                               @RequestParam String password,
                               HttpSession session,
                               Model model) {
        if (username.equals("admin") && password.equals("20230140140")) {
            session.setAttribute("loggedIn", true);
            return "redirect:/home";
        } else {
            model.addAttribute("error", "Username atau password salah!");
            return "login";
        }
    }

    // ===================== HOME =====================
    @GetMapping("/home")
    public String homePage(HttpSession session, Model model) {
        if (session.getAttribute("loggedIn") == null) {
            return "redirect:/login";
        }
        List<User> userList = getListFromSession(session);
        model.addAttribute("userList", userList);
        return "home";
    }

    // ===================== FORM =====================
    @GetMapping("/form")
    public String formPage(HttpSession session) {
        if (session.getAttribute("loggedIn") == null) {
            return "redirect:/login";
        }
        return "form";
    }

    @PostMapping("/form")
    public String formProcess(@RequestParam String nama,
                              @RequestParam String nim,
                              @RequestParam String jenisKelamin,
                              HttpSession session) {
        if (session.getAttribute("loggedIn") == null) {
            return "redirect:/login";
        }
        List<User> userList = getListFromSession(session);
        userList.add(new User(nama, nim, jenisKelamin));
        session.setAttribute("userList", userList);
        return "redirect:/home";
    }

    // ===================== LOGOUT =====================
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }

    // ===================== HELPER =====================
    @SuppressWarnings("unchecked")
    private List<User> getListFromSession(HttpSession session) {
        List<User> userList = (List<User>) session.getAttribute("userList");
        if (userList == null) {
            userList = new ArrayList<>();
            session.setAttribute("userList", userList);
        }
        return userList;
    }
}
package com.example.bookstore.controller;

import com.example.bookstore.domain.AppUser;
import com.example.bookstore.domain.AppUserRepository;
import com.example.bookstore.domain.SignupForm;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserController {
    @Autowired
    private AppUserRepository repository;
    @RequestMapping(value = "signup")
    public String addStudent(Model model) {
        model.addAttribute("signupform", new SignupForm());
        return "signup";
    }
    @RequestMapping(value = "saveuser", method = RequestMethod.POST)
    public String save(@Valid @ModelAttribute("signupform") SignupForm signupForm, BindingResult bindingResult) {
        System.out.println(bindingResult.toString());
        if (!bindingResult.hasErrors()) {
            // Check if password match
            if (signupForm.getPassword().equals(signupForm.getPasswordCheck())) {
                String pwd = signupForm.getPassword();
                BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
                String hashPwd = bc.encode(pwd);

                AppUser newUser = new AppUser();
                newUser.setPasswordHash(hashPwd);
                newUser.setUsername(signupForm.getUsername());
                newUser.setRole("USER");

                if (repository.findByUsername(signupForm.getUsername()) == null) {
                    repository.save(newUser);
                } else {
                    bindingResult.rejectValue("username", "error.userexists", "Username already exists");
                    return "signup";
                }
            }
            else {
                bindingResult.rejectValue("passwordCheck", "error.pwdmatch", "Passwords does not match");
                return "signup";
            }
        } else {
            return "signup";
        }
        return "redirect:/login";
    }
}

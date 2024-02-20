package com.example.homework2;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloThymeleafController {
    @GetMapping("/hello")
    String getPeople(@RequestParam(name="name") String name,
            @RequestParam(name="age") int age,
            Model model) {
        model.addAttribute("name", name);
        model.addAttribute("age", age);
        return "people";
    }
}

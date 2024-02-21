package com.example.handlinglists.controller;


import com.example.handlinglists.domain.Student;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HelloController {
    @GetMapping("/hello")
    public String hello(Model model) {
        List<Student> students = new ArrayList<>();
        students.add(new Student("John", "Doe"));
        students.add(new Student("Jane", "Doe"));
        students.add(new Student("Alice", "Smith"));
        model.addAttribute("students", students);
        return "hello";
    }
}

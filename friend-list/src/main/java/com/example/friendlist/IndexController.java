package com.example.friendlist;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class IndexController {
    private List<String> friends = new ArrayList<>();
    @GetMapping("/index")
    public String getIndex(Model model) {
        model.addAttribute("friends", friends);
        model.addAttribute("newFriend", new FriendForm());
        return "index";
    }
    @PostMapping("/index")
    public String addFriend(FriendForm friendForm) {
        friends.add(friendForm.getName());
        return "redirect:/index";
    }
    public static class FriendForm {
        private String name;
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
    }
}

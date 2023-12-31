package com.users.users.controller;

import com.users.users.dto.Search;
import com.users.users.service.CommentsService;
import com.users.users.service.PostService;
import com.users.users.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.users.users.dto.UserDTO;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
public class UserController {


    private final UserService userService;


    private final PostService postService;


    private final CommentsService commentsService;

    public UserController(UserService userService, PostService postService, CommentsService commentsService) {
        this.userService = userService;
        this.postService = postService;
        this.commentsService = commentsService;
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ROLE_Admin')")
    public String admin(Model model){

        List<UserDTO> users = userService.findAll().stream()
                .filter(user -> user.getRole().equals("User"))
                .collect(Collectors.toList());
        model.addAttribute("allUsers", users);
        model.addAttribute("search", new Search());
        return "site/admin";
    }

    @GetMapping("/admin/remove/{id}")
    @PreAuthorize("hasRole('ROLE_Admin')")
    public String remove(@PathVariable Integer id) {
        userService.findById(id).ifPresent(user -> commentsService.deleteByName(user.getName()));
        postService.deleteByIndex(id);
        userService.deleteById(id);
        return "redirect:/admin";
    }

    @GetMapping("/searchuser")
    @PreAuthorize("hasRole('ROLE_Admin')")
    public String searchUser(@ModelAttribute("search") Search search, Model model){
        return userService.findUser(search, model);
    }

}

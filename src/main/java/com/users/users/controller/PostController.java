package com.users.users.controller;


import com.users.users.model.*;
import com.users.users.service.CommentsService;
import com.users.users.service.PostService;
import com.users.users.service.UserService;
import com.users.users.service.ViewsService;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@Controller
public class PostController {


    private final PostService postService;

    private final CommentsService commentsService;

    private final UserService userService;

    private final ViewsService viewsService;

    public PostController(PostService postService, CommentsService commentsService, UserService userService, ViewsService viewsService) {
        this.postService = postService;
        this.commentsService = commentsService;
        this.userService = userService;
        this.viewsService = viewsService;
    }

    @Transactional
    @PostMapping ("/upload")
    public String uploadPost(@ModelAttribute("post") Post post, @RequestParam("postImage") MultipartFile file, Model model) {

        model.addAttribute("posteNew", postService.getLastPosts());
        return postService.checkUploadPost(post, file, model);
    }

    @GetMapping("/readpost/{postId}")
    public String readPost(@PathVariable Integer postId, Model model) {

        Post post = postService.getPostById(postId);
        model.addAttribute("postread", postService.getPostById(postId));
        userService.findById(post.getName()).ifPresent(customUser -> model.addAttribute("author", customUser.getName()));
        model.addAttribute("comments", commentsService.findByIdPost(postId));
        model.addAttribute("postComment", new Comments());
        model.addAttribute("userName", SecurityContextHolder.getContext().getAuthentication().getName());
        int view = viewsService.findByRoles(postId);
        viewsService.update(view+1, postId);
        return "site/readpost";
    }

    @PostMapping("/savecomment")
    public String writeComments(@ModelAttribute("postComment") Comments comments,
                                @ModelAttribute("postId") Integer postId){

        postService.saveComment(comments, postId);
        saveNewViews(postId);
        return "redirect:/readpost/" + postId;
    }

    @GetMapping("/deletecomm/{id}")
    public String deleteComment(@PathVariable Integer id){

        Integer postId = null;
        Optional<Comments> comments = commentsService.findById(id);
        if(comments.isPresent()){
            postId = comments.get().getPost();
            commentsService.deleteById(id);
        }
        saveNewViews(postId);
        return "redirect:/readpost/" + postId;
    }

    private void saveNewViews (Integer postId) {
        int view = viewsService.findByRoles(postId);
        viewsService.update(view-1, postId);
    }


}

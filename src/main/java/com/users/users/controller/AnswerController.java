package com.users.users.controller;

import com.users.users.model.Answer;
import com.users.users.service.AnswerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class AnswerController {

    private final AnswerService answerService;

    public AnswerController(AnswerService answerService) {
        this.answerService = answerService;
    }

    @GetMapping("/createAttribute")
    public String createAttribute(Model model){
        Answer answer = new Answer();
        model.addAttribute("answer", answer);
        return "redirect:/save";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute("answer")Answer answer, Model model){
        String result = answerService.checkValid(answer);
        if(!result.isEmpty()){
            model.addAttribute("textException", result);
            model.addAttribute("error", true);
            return "site/contact";
        }
        model.addAttribute("okey", true);
        model.addAttribute("textValid", "Спасибо за обращение! Скоро с вами свяжутся");
        answerService.save(answer);
        return "site/contact";
    }
}

package com.users.users.service;

import com.users.users.model.Answer;
import com.users.users.repository.AnswerRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Service
public class AnswerService {
    private final AnswerRepository answerRepository;

    public AnswerService(AnswerRepository answerRepository) {
        this.answerRepository = answerRepository;
    }

    @Transactional
    public void save(Answer answer) {
        answerRepository.save(answer);
    }

    public String checkValid(Answer answer){
        String name = answer.getName();
        String email = answer.getEmail();
        String subject = answer.getSubject();
        if(name.length() < 3 || name.length() > 15){
            return "Некоректный логин! (от 3 до 15 символов)";
        }
        String regex = "[@. /;'+-]";
        if (Pattern.compile(regex).matcher(name).find()) {
            return "Логин содержит недопустимые символы";
        }
        if(email.length() > 100){
            return "Некоректный email! (до 100 символов)";
        }
        if(subject.length() > 50) {
            return "Некоректная тема! (до 50 символов)";
        }

        return "";
    }
}

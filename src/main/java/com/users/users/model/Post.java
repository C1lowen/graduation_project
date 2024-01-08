package com.users.users.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


import static jakarta.persistence.GenerationType.IDENTITY;

@Entity(name = "post")
@Data
@NoArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Integer id;

    private Integer name;

    private LocalDate date;
    private String title;
    private String text;
    private String folder;
    @Column(name = "comment")
    private Integer countComments;

    public Post(int id, Integer customUser, LocalDate date, String title, String text, String folder) {
        this.id = id;
        this.name = customUser;
        this.date = date;
        this.title = title;
        this.text = text;
        this.folder = folder;
    }
}

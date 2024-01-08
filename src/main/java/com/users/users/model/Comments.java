package com.users.users.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;



import java.time.LocalDate;

import static jakarta.persistence.GenerationType.IDENTITY;
@Entity(name = "comments")
@Data
@NoArgsConstructor
public class Comments {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Integer id;
    @Size(min = 3, max = 15)
    private String name;
    private String message;
    private Integer post;
    private LocalDate date;


}

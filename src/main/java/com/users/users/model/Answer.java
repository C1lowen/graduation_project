package com.users.users.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;



import static jakarta.persistence.GenerationType.IDENTITY;

@Entity(name = "answers")
@Data
@NoArgsConstructor
public class Answer {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    @Size(min = 3, max = 15)
    private String name;
    private String subject;
    private String email;
    @Size(min = 8, max = 50)
    private String message;
}

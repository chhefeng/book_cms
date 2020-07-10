package com.hef.book.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Subject {
    @Id
    @GeneratedValue
    private Long id;
    @NotBlank(message = "name should not be empty")
    private String name;

    @ToString.Exclude
    @OneToMany(mappedBy = "subject")
    private List<Book> books = new ArrayList<>();

}

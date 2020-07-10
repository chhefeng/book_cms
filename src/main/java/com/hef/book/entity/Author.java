package com.hef.book.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString
@AllArgsConstructor
public class Author {
    @Id
    @GeneratedValue
    private Long id;

    private String firstName;
    private String lastName;

    private String name = firstName + " " + lastName;

    @ToString.Exclude
    @ManyToMany(mappedBy = "authors")
    private List<Book> books = new ArrayList<>();

}

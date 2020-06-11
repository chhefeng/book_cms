package com.hef.book.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Book {
    @Id
    @GeneratedValue
    private Long id;

    private String title;

    @ManyToMany(cascade = {CascadeType.PERSIST})
    private List<Author> authors = new ArrayList<>();

    @ManyToOne(cascade = {CascadeType.PERSIST})
    private Subject subject;

    @ManyToMany(cascade = {CascadeType.PERSIST})
    private List<Tag> tags = new ArrayList<>();

}

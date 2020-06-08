package com.hef.book.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Book {
    @Id
    @GeneratedValue
    private Integer id;

    private String title;

    @ManyToMany
    private List<Author> authors;

    @ManyToOne
    private Subject subject;

    @ManyToMany
    private List<Tag> tags;

}

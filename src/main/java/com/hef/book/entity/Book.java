package com.hef.book.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Book {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(cascade = {CascadeType.PERSIST})
    private User user;

    private String title;
    private String Subtitle;
    private String picture;
    private String isbn;
    private String description;
    private String published;

    @ManyToMany(cascade = {CascadeType.PERSIST})
    private List<Author> authors = new ArrayList<>();

    @ManyToOne(cascade = {CascadeType.PERSIST})
    private Subject subject;

    @ManyToMany(cascade = {CascadeType.PERSIST})
    private List<Tag> tags = new ArrayList<>();


}

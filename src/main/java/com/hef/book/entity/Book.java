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

    @ManyToOne
    private User user;

    private String title;
    private String Subtitle;
    private String picture;
    private String isbn;
    @Basic(fetch = FetchType.LAZY)
    @Lob
    private String description;
    private String published;

    @ManyToMany(cascade = {CascadeType.PERSIST})
    private List<Author> authors = new ArrayList<>();

    @ManyToOne
    private Subject subject;

    @ManyToMany
    private List<Tag> tags = new ArrayList<>();


}

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
    @ToString.Exclude
    @Basic(fetch = FetchType.LAZY)
    @Lob
    private String description;
    private String published;

    @ToString.Exclude
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE , CascadeType.REFRESH})
    private List<Author> authors = new ArrayList<>();

    @ToString.Exclude
    @ManyToOne
    private Subject subject;

    @ToString.Exclude
    @ManyToMany
    private List<Tag> tags = new ArrayList<>();


}

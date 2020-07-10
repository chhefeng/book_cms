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
    @Transient
    private String tagIds;
    @Transient
    private String authorIds;

    @ToString.Exclude
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE , CascadeType.REFRESH})
    private List<Author> authors = new ArrayList<>();

    @ToString.Exclude
    @ManyToOne
    private Subject subject;

    @ToString.Exclude
    @ManyToMany
    private List<Tag> tags = new ArrayList<>();


    public void init() {
        if(!authors.isEmpty()){
            StringBuilder sb = new StringBuilder();
            for (Tag tag : tags) {
                sb.append(tag.getId());
                sb.append(",");
            }
            sb.deleteCharAt(sb.lastIndexOf(","));
            this.tagIds = sb.toString();
            System.out.println(tagIds);
        }

        if(!tags.isEmpty()){
            StringBuilder sb = new StringBuilder();
            for (Author author : authors) {
                sb.append(author.getId());
                sb.append(",");
            }
            sb.deleteCharAt(sb.lastIndexOf(","));
            this.authorIds = sb.toString();
            System.out.println(authorIds);
        }

    }




}

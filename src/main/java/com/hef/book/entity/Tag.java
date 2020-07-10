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
public class Tag {
    @Id
    @GeneratedValue
    private Long id;
    private String name;

    @ToString.Exclude
    @ManyToMany(mappedBy = "tags")
    private List<Book> books = new ArrayList<>();
}

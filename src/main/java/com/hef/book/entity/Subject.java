package com.hef.book.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Subject {
    @Id
    @GeneratedValue
    private Long id;
    @NotBlank(message = "name should not be empty")
    private String name;

    @OneToMany(mappedBy = "subject")
    private List<Book> books = new ArrayList<>();

}

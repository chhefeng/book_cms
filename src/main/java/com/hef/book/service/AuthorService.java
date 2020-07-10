package com.hef.book.service;

import com.hef.book.entity.Author;
import com.hef.book.entity.Author;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AuthorService {

    Author getOne(Long id);

    List<Author> findAll();

    Page<Author> findAll(Pageable pageable);

    Author save(Author author);

    Author update(Author author);

    void delete(Long id);

    Author findByFirstNameAndLastName(String firstName, String lastName);

}

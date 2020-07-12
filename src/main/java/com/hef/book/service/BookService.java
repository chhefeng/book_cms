package com.hef.book.service;

import com.hef.book.entity.Book;
import com.hef.book.entity.BookQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BookService {

    Book getOne(Long id);

    Page<Book> findAll(Pageable pageable);

    Book save(Book book);

    Book update(Book book);

    void delete(Long id);


    Page<Book> listBook(Long tagId, Pageable pageable);

    Page<Book> listBook(Pageable pageable, BookQuery book);
}

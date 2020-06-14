package com.hef.book.service;

import com.hef.book.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BookService {

    Book getBook(Long id);

    Page<Book> findAll(Pageable pageable);

    Book saveBook(Book book);

    Book updateBook(Book book);

    void deleteBook(Long id);


}

package com.hef.book.service.impl;

import com.hef.book.dao.BookRepository;
import com.hef.book.entity.Book;
import com.hef.book.service.BookService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookServiceImpl implements BookService {

    private BookRepository bookRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }

    @Override
    public Book getOne(Long id) {
        return bookRepository.getOne(id);
    }

    @Override
    public Page<Book> findAll(Pageable pageable) {
        return bookRepository.findAll(pageable);
    }

    @Override
    @Transactional
    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    @Override
    @Transactional
    public Book updateBook(Book book) {
        Book bookOld = bookRepository.findById(book.getId()).get();
        BeanUtils.copyProperties(book, bookOld);
        return bookRepository.save(bookOld);
    }

    @Override
    public void deleteBook(Long id) {
    }

}

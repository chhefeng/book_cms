package com.hef.book.service.impl;

import com.hef.book.dao.BookRepository;
import com.hef.book.entity.Book;
import com.hef.book.entity.BookQuery;
import com.hef.book.entity.Subject;
import com.hef.book.service.BookService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

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
    public Book save(Book book) {
        return bookRepository.save(book);
    }

    @Override
    @Transactional
    public Book update(Book book) {
        Book bookOld = bookRepository.findById(book.getId()).get();
        BeanUtils.copyProperties(book, bookOld);
        return bookRepository.save(bookOld);
    }

    @Override
    public void delete(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public Page<Book> listBook(Long tagId, Pageable pageable) {
        return bookRepository.findAll(new Specification<Book>() {
            @Override
            public Predicate toPredicate(Root<Book> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
                Join join = root.join("tags");
                return cb.equal(join.get("id"),tagId);
            }
        },pageable);
    }

    @Override
    public Page<Book> listBook(Pageable pageable, BookQuery book) {
        return bookRepository.findAll(new Specification<Book>() {
            @Override
            public Predicate toPredicate(Root<Book> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<>();
                if (!"".equals(book.getTitle()) && book.getTitle() != null) {
                    predicates.add(cb.like(root.<String>get("title"), "%"+book.getTitle()+"%"));
                }
                if (book.getSubjectId() != null) {
                    predicates.add(cb.equal(root.<Subject>get("type").get("id"), book.getSubjectId()));
                }

                cq.where(predicates.toArray(new Predicate[predicates.size()]));
                return null;
            }
        },pageable);
    }

}

package com.hef.book.dao;

import com.hef.book.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface BookRepository extends JpaRepository<Book, Long>, JpaSpecificationExecutor<Book> {


    @Query("select b from Book b where b.title like ?1 or b.isbn like ?1")
    Page<Book> searchByQuery(String query, Pageable pageable);
}

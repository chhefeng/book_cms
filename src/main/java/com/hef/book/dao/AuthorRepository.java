package com.hef.book.dao;

import com.hef.book.entity.Author;
import com.hef.book.entity.Book;
import com.hef.book.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AuthorRepository extends JpaRepository<Author, Long> {
    Author findByFirstNameAndLastName(String firstName, String lastName);


}

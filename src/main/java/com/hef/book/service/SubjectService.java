package com.hef.book.service;

import com.hef.book.entity.Subject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface SubjectService {

    Subject getOne(Long id);

    List<Subject> findAll();

    Page<Subject> findAll(Pageable pageable);

    Subject save(Subject subject);

    Subject update(Subject subject);

    void delete(Long id);


}

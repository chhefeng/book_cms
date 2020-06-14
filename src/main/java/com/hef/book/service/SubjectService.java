package com.hef.book.service;

import com.hef.book.entity.Subject;

import java.util.List;

public interface SubjectService {

    Subject getOne(Long id);

    List<Subject> findAll();

    Subject saveSubject(Subject subject);

    Subject updateSubject(Long id, Subject subject);

    void deleteSubject(Long id);


}

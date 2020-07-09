package com.hef.book.service.impl;

import com.hef.book.NotFoundException;
import com.hef.book.dao.SubjectRepository;
import com.hef.book.entity.Subject;
import com.hef.book.entity.Subject;
import com.hef.book.service.SubjectService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectServiceImpl implements SubjectService {

    private SubjectRepository subjectRepository;
    
    @Autowired
    public SubjectServiceImpl(SubjectRepository subjectRepository){
        this.subjectRepository = subjectRepository;
    }

    @Override
    public Subject getOne(Long id) {
        return subjectRepository.getOne(id);
    }

    @Override
    public List<Subject> findAll() {
        return subjectRepository.findAll();
    }

    @Override
    public Subject saveSubject(Subject subject) {
        return subjectRepository.save(subject);
    }

    @Override
    public Subject updateSubject(Long id, Subject subject) {
        Subject subject1 = subjectRepository.getOne(id);
        if (subject1 == null) {
            throw new NotFoundException("The Subject does not exist");
        }
        BeanUtils.copyProperties(subject, subject1);
        return subjectRepository.save(subject1);
    }

    @Override
    public void deleteSubject(Long id) {
        subjectRepository.deleteById(id);
    }
}

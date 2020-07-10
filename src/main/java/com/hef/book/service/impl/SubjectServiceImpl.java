package com.hef.book.service.impl;

import com.hef.book.dao.SubjectRepository;
import com.hef.book.entity.Subject;
import com.hef.book.service.SubjectService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public Page<Subject> findAll(Pageable pageable) {
        return subjectRepository.findAll(pageable);
    }

    @Override
    public Subject save(Subject subject) {
        return subjectRepository.save(subject);
    }

    @Override
    @Transactional
    public Subject update(Subject subject) {

        Subject subjectOld = subjectRepository.getOne(subject.getId());
        Long id = 28L;
        Subject subject2 = subjectRepository.getOne(28L);
        System.out.println(subject2);
        Subject subject1 =subjectRepository.findById(subject.getId()).get();
        System.out.println(subjectOld);
        System.out.println(subject1);
        BeanUtils.copyProperties(subject, subjectOld);
        return subjectRepository.save(subjectOld);
    }

    @Override
    public void delete(Long id) {
        subjectRepository.deleteById(id);
    }
}

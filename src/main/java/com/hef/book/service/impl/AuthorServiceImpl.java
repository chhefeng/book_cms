package com.hef.book.service.impl;

import com.hef.book.dao.AuthorRepository;
import com.hef.book.entity.Author;
import com.hef.book.entity.Author;
import com.hef.book.service.AuthorService;
import com.hef.book.utils.HelpFunction;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {

    private AuthorRepository authorRepository;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public Author getOne(Long id) {
        return authorRepository.getOne(id);
    }

    @Override
    public List<Author> findAll() {
        return authorRepository.findAll();
    }

    @Override
    public Page<Author> findAll(Pageable pageable) {
        return authorRepository.findAll(pageable);
    }

    @Override
    public Author save(Author author) {
        return authorRepository.save(author);
    }

    @Override
    @Transactional
    public Author update(Author author) {
        Author authorOld = authorRepository.getOne(author.getId());
        BeanUtils.copyProperties(author, authorOld);
        return authorRepository.save(authorOld);
    }

    @Override
    public void delete(Long id) {
        authorRepository.deleteById(id);
    }

    @Override
    @Transactional
    public List<Author> findAndSaveAuthors(String ids) {
        return authorRepository.findAllById(convertToList(ids));
    }


    private List<Long> convertToList(String ids){
        List<Long> list = new ArrayList<>();
        if(!"".equals(ids) && ids!=null){
            String[] idArray = ids.split(",");
            for (String s : idArray) {
                if (HelpFunction.isInteger(s)) {
                    list.add(Long.valueOf(s));
                } else {
                    Author author = new Author();
                    author.setName(s);
                    author = this.save(author);
                    list.add(author.getId());
                }
            }
        }
        return list;
    }

}

package com.hef.book.service.impl;

import com.hef.book.NotFoundException;
import com.hef.book.dao.TagRepository;
import com.hef.book.entity.Tag;
import com.hef.book.service.TagService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class TagServiceImpl implements TagService {

    private TagRepository tagRepository;

    @Autowired
    public TagServiceImpl(TagRepository tagRepository){
        this.tagRepository = tagRepository;
    }

    @Override
    public Tag getOne(Long id) {
        return tagRepository.getOne(id);
    }

    @Override
    public List<Tag> findAll() {
        return tagRepository.findAll();
    }

    @Override
    public Tag saveTag(Tag tag) {
        return tagRepository.save(tag);
    }

    @Override
    public Tag updateTag(Long id, Tag tag) {
        Tag tag1 = tagRepository.getOne(id);
        if (tag1 == null) {
            throw new NotFoundException("The tag does not exist");
        }
        BeanUtils.copyProperties(tag, tag1);
        return tagRepository.save(tag1);
    }

    @Override
    public void deleteTag(Long id) {
        tagRepository.deleteById(id);
    }
}

package com.hef.book.service.impl;

import com.hef.book.NotFoundException;
import com.hef.book.dao.TagRepository;
import com.hef.book.entity.Tag;
import com.hef.book.service.TagService;
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
    public Page<Tag> findAll(Pageable pageable) {
        return tagRepository.findAll(pageable);
    }

    @Override
    public Tag save(Tag tag) {
        return tagRepository.save(tag);
    }

    @Override
    @Transactional
    public Tag update(Tag tag) {
        Tag tagOld = tagRepository.getOne(tag.getId());
        BeanUtils.copyProperties(tag, tagOld);
        return tagRepository.save(tagOld);
    }

    @Override
    public void delete(Long id) {
        tagRepository.deleteById(id);
    }

    @Override
    @Transactional
    public List<Tag> findAndSaveTags(String ids) {
        return tagRepository.findAllById(convertToList(ids));
    }

    private List<Long> convertToList(String ids){
        List<Long> list = new ArrayList<>();
        if(!"".equals(ids) && ids!=null){
            String[] idArray = ids.split(",");
            for (String s : idArray) {
                if (HelpFunction.isInteger(s)) {
                    list.add(Long.valueOf(s));
                } else {
                    Tag tag = new Tag();
                    tag.setName(s);
                    tag = this.save(tag);
                    list.add(tag.getId());
                }
            }
        }
        return list;
    }


}

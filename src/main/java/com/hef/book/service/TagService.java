package com.hef.book.service;

import com.hef.book.entity.Tag;

import java.util.List;

public interface TagService {

    Tag getOne(Long id);

    List<Tag> findAll();

    Tag saveTag(Tag tag);

    Tag updateTag(Long id, Tag tag);

    void deleteTag(Long id);

}

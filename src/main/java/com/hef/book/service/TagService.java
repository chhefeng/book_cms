package com.hef.book.service;

import com.hef.book.entity.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TagService {

    Tag getOne(Long id);

    List<Tag> findAll();

    Page<Tag> findAll(Pageable pageable);

    Tag saveTag(Tag tag);

    Tag updateTag(Long id, Tag tag);

    void deleteTag(Long id);

    // methods
    List<Tag> findTags(String ids);

}

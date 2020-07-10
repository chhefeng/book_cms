package com.hef.book.controller;

import com.hef.book.entity.Subject;
import com.hef.book.entity.Tag;
import com.hef.book.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/tag")
public class TagController {

    private TagService tagService;

    @Autowired
    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    @GetMapping({"/list", ""})
    public String list(@PageableDefault(size = 5) Pageable pageable, Model model) {
        model.addAttribute("page", tagService.findAll(pageable));
        return "tag";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable Long id, Model model){
        model.addAttribute("tag", tagService.getOne(id));
        return "tag-input";
    }

    @PostMapping("/save")
    public String save(Tag tag){

        if (tag.getId()== null){
            tagService.save(tag);
        } else {
            tagService.update(tag);
        }
        return "redirect:/tag/list";
    }


}

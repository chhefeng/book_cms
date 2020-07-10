package com.hef.book.controller;

import com.hef.book.entity.Subject;
import com.hef.book.service.SubjectService;
import org.hibernate.annotations.Subselect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/subject")
public class SubjectController {

    private SubjectService subjectService;

    @Autowired
    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @GetMapping({"/list", ""})
    public String list(@PageableDefault(size = 5) Pageable pageable, Model model) {
        model.addAttribute("page", subjectService.findAll(pageable));
        return "subject";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable Long id, Model model){
        model.addAttribute("subject", subjectService.getOne(id));
        return "subject-input";
    }

    @GetMapping("/save")
    public String save(Subject subject, RedirectAttributes redirectAttributes){
        System.out.println(subject);
        Subject subjectNew;
        if (subject.getId()== null){
            subjectNew = subjectService.save(subject);
        } else {
            subjectNew = subjectService.update(subject);
        }
        if (subjectNew != null){
            redirectAttributes.addFlashAttribute("message", "successful");
        }
        return "redirect:/subject/list";

    }



}

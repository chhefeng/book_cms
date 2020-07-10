package com.hef.book.controller;

import com.hef.book.entity.Author;
import com.hef.book.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/author")
public class AuthorController {

    private AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping("/list")
    public String list(@PageableDefault(size = 5) Pageable pageable, Model model) {
        model.addAttribute("page", authorService.findAll(pageable));
        return "author";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable Long id, Model model){
        model.addAttribute("author", authorService.getOne(id));
        return "author-input";
    }

    @PostMapping("/save")
    public String save(Author author){

        if (author.getId()== null){
            authorService.save(author);
        } else {
            authorService.update(author);
        }
        return "redirect:/author/list";
    }

    @GetMapping("/{id}/delete")
    public String edit(@PathVariable Long id, RedirectAttributes redirectAttributes){
        authorService.delete(id);
        redirectAttributes.addFlashAttribute("message", "successful");
        return "redirect:/author/list";
    }


}

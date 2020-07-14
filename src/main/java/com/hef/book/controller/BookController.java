package com.hef.book.controller;


import com.hef.book.entity.*;
import com.hef.book.service.AuthorService;
import com.hef.book.service.BookService;
import com.hef.book.service.SubjectService;
import com.hef.book.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/book")
public class BookController {

    private BookService bookService;
    private TagService tagService;
    private SubjectService subjectService;
    private AuthorService authorService;

    @Autowired
    public BookController(AuthorService authorService, BookService bookService, TagService tagService, SubjectService subjectService) {
        this.authorService = authorService;
        this.bookService = bookService;
        this.tagService = tagService;
        this.subjectService = subjectService;
    }

    /**
     * get all books
     * @param pageable
     * @param model
     * @return
     */
    @GetMapping({"/list", ""})
    public String list(@PageableDefault(size = 8, sort = {"title"}, direction = Sort.Direction.DESC)
                                   Pageable pageable, Model model) {
        model.addAttribute("subject", subjectService.findAll());
        model.addAttribute("page", bookService.findAll(pageable));
        return "books";
    }

    @GetMapping("/{id}")
    public String getOne(@PathVariable long id, Model model){
        model.addAttribute("book", bookService.getOne(id));
        return "details";
    }

    /**
     * edit a exist book, find one, show form
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/{id}/edit")
    public String editOne(@PathVariable long id, Model model){
        Book book = bookService.getOne(id);
        book.init();
        model.addAttribute("book", book);
        model.addAttribute("authors", authorService.findAll());
        model.addAttribute("tags", tagService.findAll());
        model.addAttribute("subjects", subjectService.findAll());
        return "book-form";
    }

    /**
     * show book form to add a new book or edit a exist book
     * @param book
     * @param model
     * @return
     */
    @GetMapping("/add")
    public String showForm(@ModelAttribute Book book, Model model){
        model.addAttribute("authors", authorService.findAll());
        model.addAttribute("tags", tagService.findAll());
        model.addAttribute("subjects", subjectService.findAll());
        return "book-form";
    }


    /**
     * save book
     * @param book
     * @param attributes
     * @param session
     * @return
     */
    @PostMapping("/save-book")
    public String postForm(Book book, RedirectAttributes attributes, HttpSession session) {
        book.setUser((User) session.getAttribute("user"));
        System.out.println(book);

        List<Tag> tags = tagService.findAndSaveTags(book.getTagIds());
        book.setTags(tags);

        List<Author> authors = authorService.findAndSaveAuthors(book.getAuthorIds());
        book.setAuthors(authors);

        /**
         * Determine if subject is null, if it is null, set to subject "other"
         */
        if (book.getSubject() == null){
            book.setSubject(subjectService.getOne(3L));
        }

        Book bookNew;
        if (book.getId() == null) {
            bookNew = bookService.save(book);
        } else {
            bookNew = bookService.update(book);
        }
        return "redirect:/book/list";
    }

    /**
     * search book
     * @param pageable
     * @param query
     * @param model
     * @return
     */
    @PostMapping("/search")
    public String search(@PageableDefault(size = 4) Pageable pageable,
                         @RequestParam String query, Model model) {
        model.addAttribute("page", bookService.listBook(query, pageable));
        model.addAttribute("query", query);
        return "books";
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes red){
        bookService.delete(id);
        red.addFlashAttribute("message", "delete successful");
        return "redirect:/book/list";
    }


}

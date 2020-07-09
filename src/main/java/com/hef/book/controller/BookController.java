package com.hef.book.controller;


import com.hef.book.entity.Author;
import com.hef.book.entity.Book;
import com.hef.book.entity.Subject;
import com.hef.book.entity.User;
import com.hef.book.service.BookService;
import com.hef.book.service.SubjectService;
import com.hef.book.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/book")
public class BookController {

    private static final String PATH_LIST = "/book/list";
    private static final String PATH_SEARCH = "/book/search";
    private static final String PATH_ADD = "/book/add";

    private static final String RESOURCE_LIST = "index";
    private static final String RESOURCE_ADD = "form-book";
    private static final String REDIRECT_LIST = "redirect:/book/list";

    private BookService bookService;
    private TagService tagService;
    private SubjectService subjectService;

    @Autowired
    public BookController(BookService bookService, TagService tagService, SubjectService subjectService) {
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
    @GetMapping({"/","","/list"})
    public String list(@PageableDefault(size = 8) Pageable pageable, Model model) {
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
        model.addAttribute("book", bookService.getOne(id));
        model.addAttribute("tags", tagService.findAll());
        model.addAttribute("subjects", subjectService.findAll());
        return "form-book";
    }

    /**
     * show book form to add a new book or edit a exist book
     * @param book
     * @param model
     * @return
     */
    @GetMapping("/add")
    public String showForm(@ModelAttribute Book book, Model model){
        book.getAuthors().add(new Author());
        model.addAttribute("tags", tagService.findAll());
        model.addAttribute("subjects", subjectService.findAll());
        return "form-book";
    }

    /**
     * if a book has more than one author,
     * it is possible to add author form dynamically
     * @param book
     * @return
     */
    @PostMapping("/add-author")
    public String addAuthors(Book book){
        //model.addAttribute("authors", authors);
        book.getAuthors().add(new Author());
        return "form-book";
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

        if (book.getId() == null) {
            bookService.saveBook(book);
        } else {
            bookService.updateBook(book);
        }
        return REDIRECT_LIST;
    }

    /**
     * search book
     * @param pageable
     * @param book
     * @param model
     * @return
     */
    @PostMapping("/search")
    public String search(@PageableDefault(size = 8) Pageable pageable, Book book, Model model) {
        // code goes here
        return "index";
    }


}

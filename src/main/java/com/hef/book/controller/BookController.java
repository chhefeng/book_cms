package com.hef.book.controller;


import com.hef.book.entity.Author;
import com.hef.book.entity.Book;
import com.hef.book.entity.Subject;
import com.hef.book.entity.User;
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

    @GetMapping({"/","","/list"})
    public String list(@PageableDefault(size = 8) Pageable pageable, Model model) {
            model.addAttribute("subject", subjectService.findAll());
            model.addAttribute("page", bookService.findAll(pageable));
            return "books";
    }

    @GetMapping("/add")
    public String showForm(@ModelAttribute Book book, Model model){
        //List<Author> authors = new ArrayList<>();
        //model.addAttribute("book", new Book());
        //model.addAttribute("authors", authors);
        book.getAuthors().add(new Author());
        model.addAttribute("tags", tagService.findAll());
        model.addAttribute("subjects", subjectService.findAll());
        return "form-book";
    }

    @PostMapping(params="addAuthors", value = "/add")
    public String addAuthors(Book book){
        //model.addAttribute("authors", authors);
        book.getAuthors().add(new Author());
        return "form-book";
    }

    @PostMapping(params="save", value = "/add")
    public String postForm(Book book, RedirectAttributes attributes, HttpSession session) {
        // book.setUser((User) session.getAttribute("user"));
        System.out.println(book);
        Book book1;
        if (book.getId() == null) {
            book1 = bookService.saveBook(book);
        } else {
            book1 = bookService.updateBook(book);
        }
        return REDIRECT_LIST;

    }

    @PostMapping("/search")
    public String search(@PageableDefault(size = 8) Pageable pageable, Book book, Model model) {
        // code goes here
        return "index";
    }

}

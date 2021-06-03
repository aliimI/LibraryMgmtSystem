package com.factory.controller;

import com.factory.entity.Book;
import com.factory.service.AuthorService;
import com.factory.service.BookService;
import com.factory.service.CategoryService;
import com.factory.service.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class BookController {
    private final BookService bookService;
    private final AuthorService authorService;
    private final CategoryService categoryService;
    private final PublisherService publisherService;

    @Autowired
    public BookController(BookService bookService,AuthorService authorService, CategoryService categoryService, PublisherService publisherService){
        this.bookService = bookService;
        this.authorService = authorService;
        this.categoryService = categoryService;
        this. publisherService = publisherService;

    }

    @RequestMapping("/books")
    public String findAllBooks(Model model, HttpServletRequest request){
        final List<Book> books = bookService.findAllBooks();
        model.addAttribute("books", books);
        if(request.isUserInRole("USER")){
            return "redirect:/list-books-user";
        }
        return "list-books";

    }

    @RequestMapping("/searchBook")
    public String searchBook(@Param("keyword") String keyword, Model model) {
        final List<Book> books = bookService.searchBooks(keyword);

        model.addAttribute("books", books);
        model.addAttribute("keyword", keyword);
        return "list-books";
    }

    @GetMapping("/book/{id}")
    public String findBookById(@PathVariable("id") Long id, Model model) {
        final Book book = bookService.findBookById(id);

        model.addAttribute("book", book);
        return "list-book";
    }



    @GetMapping("/add")
    public String showCreateForm(Book book, Model model){
        model.addAttribute("categories", categoryService.findAllCategories());
        model.addAttribute("authors", authorService.findAllAuthors());
        model.addAttribute("publishers", publisherService.findAllPublishers());
        return "add-book";
    }

    @RequestMapping("/add-book")
    public String createBook(Book book, BindingResult result, Model model){
        if(result.hasErrors()){
            return "add-book";
        }
        bookService.createBook(book);
        model.addAttribute("book", bookService.findAllBooks());
        return "redirect:/books";
    }

    @GetMapping("/update/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
        final Book book = bookService.findBookById(id);

        model.addAttribute("book", book);
        return "update-book";
    }

    @RequestMapping("/update-book/{id}")
    public String updateBook(@PathVariable("id") Long id, Book book, BindingResult result, Model model) {
        if (result.hasErrors()) {
            book.setId(id);
            return "update-book";
        }

        bookService.updateBook(book);
        model.addAttribute("book", bookService.findAllBooks());
        return "redirect:/books";
    }

    @RequestMapping("/remove-book/{id}")
    public String deleteBook(@PathVariable("id") Long id, Model model) {
        bookService.deleteBook(id);

        model.addAttribute("book", bookService.findAllBooks());
        return "redirect:/books";
    }



}

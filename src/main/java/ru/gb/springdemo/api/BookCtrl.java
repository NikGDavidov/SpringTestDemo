package ru.gb.springdemo.api;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.gb.springdemo.model.Book;
import ru.gb.springdemo.service.BookService;


import java.util.List;

@Controller
public class BookCtrl {
    @Autowired
    private BookService service;

    ///ui/books - на странице должен отобразиться список всех доступных книг в системе
    @GetMapping( "/ui/books")
    public String books(Model model) {
        List<Book> books = service.getBooks();
        model.addAttribute("books", books);
        return "books";
    }
}

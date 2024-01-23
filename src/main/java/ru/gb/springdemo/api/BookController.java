package ru.gb.springdemo.api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.gb.springdemo.model.Book;
import ru.gb.springdemo.service.BookService;

import java.util.List;
import java.util.NoSuchElementException;


// * 1.1 Реализовать контроллер по управлению книгами с ручками:
// GET /book/{id} - получить описание книги, DELETE /book/{id} - удалить книгу, POST /book - создать книгу

@Slf4j
@RestController
@RequestMapping("/book")
public class BookController {
    @Autowired
    private BookService service;

    @GetMapping( "/{id}")
    public Book getBook(@PathVariable long id) {
        return service.getById(id);
    }

    @DeleteMapping ("/{id}")
    public ResponseEntity<?> delete(@PathVariable long id) {
        final boolean deleted = service.deleteById(id);

        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }


    @PostMapping
    ResponseEntity<Book> addBook(@RequestBody BookRequest request) {
             final Book book;
        try {
            book = service.addBook(request);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(book);
    }






}

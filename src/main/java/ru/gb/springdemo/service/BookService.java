package ru.gb.springdemo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import ru.gb.springdemo.api.BookRequest;
import ru.gb.springdemo.model.Book;
import ru.gb.springdemo.repository.JpaBookRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookService {

    private final JpaBookRepository bookRepository;

    public Book getById(long id) {

        Optional <Book> findBook = bookRepository.findById(id);


        if (findBook.isEmpty()){
            throw new NoSuchElementException("Не найдена книга с идентификатором \"" + id + "\"");
        }

        return findBook.get();
    }

    public boolean deleteById(long id) {


       bookRepository.deleteById(id);
       return true;
    }

    public Book addBook(BookRequest request) {
        Book book = new Book(request.getName());
        bookRepository.save(book);
        return book;
    }
    public List<Book> getBooks(){
        return bookRepository.findAll();
    }
}
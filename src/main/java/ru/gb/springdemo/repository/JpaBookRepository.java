package ru.gb.springdemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.repository.CrudRepository;
import ru.gb.springdemo.model.Book;

public interface JpaBookRepository extends JpaRepository<Book, Long> {

}

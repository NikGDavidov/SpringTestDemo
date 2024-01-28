package ru.gb.springdemo;

import lombok.SneakyThrows;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import ru.gb.springdemo.model.Book;
import ru.gb.springdemo.repository.JpaBookRepository;

import java.util.Optional;

@SpringBootApplication
public class Application {
	/*
	 * Сервер, отвечающий за выдачу книг в библиотеке.
	 * Нужно напрограммировать ручку, которая либо выдает книгу читателями, либо отказывает в выдаче.
	 *
	 * /book/** - книга
	 * GET /book/25 - получить книгу с идентификатором 25
	 *
	 * /reader/** - читатель
	 * /issue/** - информация о выдаче
	 * POST /issue {"readerId": 25, "bookId": 57} - выдать читателю с идентификатором 25 книгу с идентификатором 57
	 *
	 *
	*/
	@SneakyThrows
	public static void main(String[] args) {

		//SpringApplication.run(Application.class, args);
		ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);
		JpaBookRepository jpaBookRepository = context.getBean(JpaBookRepository.class);

		for (int i = 1; i <= 3; i++) {
			Book book = new Book();
			book.setId((long) i);
			book.setName("Book #" + i);
			jpaBookRepository.save(book);
		}
		Optional<Book> findBook = jpaBookRepository.findById(1L);
		findBook.ifPresent(System.out::println);
	}

}

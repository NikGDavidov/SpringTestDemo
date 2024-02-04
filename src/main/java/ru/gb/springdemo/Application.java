package ru.gb.springdemo;

import lombok.SneakyThrows;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import ru.gb.springdemo.model.Book;
import ru.gb.springdemo.repository.JpaBookRepository;
import ru.gb.springdemo.repository.RoleRepository;
import ru.gb.springdemo.repository.UserRepository;
import ru.gb.springdemo.model.User;
import ru.gb.springdemo.model.Role;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@SpringBootApplication
public class Application {
	/**
	 * 1. Для ресурсов, возвращающих HTML-страницы, реализовать авторизацию через login-форму.
	 * Остальные /api ресурсы, возвращающие JSON, закрывать не нужно!
	 * 2.1* Реализовать таблицы User(id, name, password) и Role(id, name), связанные многие ко многим
	 * 2.2* Реализовать UserDetailsService, который предоставляет данные из БД (таблицы User и Role)
	 * 3.3* Ресурсы выдачей (issue) доступны обладателям роли admin
	 * 3.4* Ресурсы читателей (reader) доступны всем обладателям роли reader
	 * 3.5* Ресурсы книг (books) доступны всем авторизованным пользователям
	 */
	static long id = 1L;
	@SneakyThrows
	public static void main(String[] args) {


		ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);

		JpaBookRepository jpaBookRepository = context.getBean(JpaBookRepository.class);


		for (int i = 1; i <= 3; i++) {
			Book book = new Book();
			book.setId((long) i);
			book.setName("Book #" + i);
			jpaBookRepository.save(book);
		}


		UserRepository userRepository=context.getBean(UserRepository.class);
		RoleRepository roleRepository = context.getBean(RoleRepository.class);
		saveUser(userRepository, roleRepository,"admin");
		saveUser(userRepository, roleRepository,"reader");

	}

	private static void saveUser(UserRepository repository,RoleRepository roleRepository, String login) {

		Role role = new Role ();
		role.setId(id++);
		role.setRole(login);
		User user = new User();
		user.setId(id++);
		user.setLogin(login);
		user.setPassword(login);
		List<Role> roles = new ArrayList<>();
		roles.add(role);
		user.setRoles(roles);
		List<User> users = new ArrayList<>();
		users.add(user);
		role.setUsers(users);
		repository.save(user);
		roleRepository.save(role);

	}

}



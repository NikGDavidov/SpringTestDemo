package ru.gb.springdemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import ru.gb.springdemo.model.User;
public interface UserRepository extends JpaRepository<User, Long> {
  Optional<User> findByLogin(String login);
}

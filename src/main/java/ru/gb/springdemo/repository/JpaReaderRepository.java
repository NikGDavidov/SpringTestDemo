package ru.gb.springdemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gb.springdemo.model.Reader;

public interface JpaReaderRepository extends JpaRepository<Reader,Long> {
}

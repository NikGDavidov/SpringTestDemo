package ru.gb.springdemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gb.springdemo.model.Issue;

public interface JpaIssueRepository extends JpaRepository<Issue,Long> {
}

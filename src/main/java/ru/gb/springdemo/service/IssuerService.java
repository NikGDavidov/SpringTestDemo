package ru.gb.springdemo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.gb.springdemo.api.IssueRequest;
import ru.gb.springdemo.model.Book;
import ru.gb.springdemo.model.Issue;
import ru.gb.springdemo.model.IssueException;
import ru.gb.springdemo.model.Reader;
import ru.gb.springdemo.repository.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class IssuerService {

  private final JpaBookRepository bookRepository;
  private final JpaReaderRepository readerRepository;
  private final JpaIssueRepository issueRepository;

  @Value("${application.max-allowed-books:1}")
  private int maxQty;
  public Issue issue(IssueRequest request) throws IssueException {


    Optional<Book> findBook = bookRepository.findById(request.getBookId());
    if (findBook.isEmpty())
      throw new NoSuchElementException("Не найдена книга с идентификатором \"" + request.getBookId() + "\"");


    Optional<Reader> findReader = readerRepository.findById(request.getReaderId());


    if (findReader.isEmpty())
      throw new NoSuchElementException("Не найден читатель с идентификатором \"" + request.getReaderId() + "\"");
    Reader reader = findReader.get();
    // можно проверить, что у читателя нет книг на руках (или его лимит не превышает в Х книг)
    // * 2.1 В сервис IssueService добавить проверку, что у пользователя на руках нет книг. Если есть - не выдавать книгу
  // * (статус ответа - 409 Conflict)

    List<Issue> issueList = issueRepository.findAll();
    int qty=0;
    for (Issue issue:issueList){
      if (issue.getReturnDate()!=null) {

        Reader currentReader = readerRepository.findById(issue.getReaderId()).get();
        if (reader.equals(currentReader)) {
          qty++;
          if (qty > maxQty) throw new IssueException("количество несданных книг читателя превышает допустимое");
        }
      }
    }

    Issue issue = new Issue(request.getBookId(), request.getReaderId());
    issueRepository.save(issue);
    return issue;
  }
  public Issue setReturnDate (long id){

    List<Issue>issues = issueRepository.findAll();

    Issue issue = issues.stream().filter(it -> Objects.equals(it.getId(), id))
            .findFirst()
            .orElse(null);
    if (issue == null) throw new NoSuchElementException ("Не найдена выдача с идентификатором \"" + id + "\"");
    issue.setReturnDate(LocalDateTime.now());
    return issue;
  }

  public List<Issue> getIssues (){

    return issueRepository.findAll();
  }

}

package ru.gb.springdemo.api;

import lombok.Data;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.web.reactive.server.WebTestClient;
import ru.gb.springdemo.JUnitSpringBootBase;
import ru.gb.springdemo.model.Book;
import ru.gb.springdemo.model.Issue;
import ru.gb.springdemo.model.Reader;
import ru.gb.springdemo.repository.JpaBookRepository;
import ru.gb.springdemo.repository.JpaIssueRepository;
import ru.gb.springdemo.repository.JpaReaderRepository;

import java.time.LocalDateTime;

public class IssueControllerTest extends JUnitSpringBootBase {
    @Autowired
    WebTestClient webTestClient;
    @Autowired
    JpaIssueRepository issueRepository;
    @Autowired
    JpaBookRepository bookRepository;
    @Autowired
   JpaReaderRepository readerRepository;

    @Autowired
    JdbcTemplate jdbcTemplate;
    @Data
    static class JUnitIssueResponse {
       private long readerId;
       private long bookId;
        private LocalDateTime returnDate;

    }
    @Test
    void testSave() {
        bookRepository.save(new Book(1L ,"testBook"));
        readerRepository.save(new Reader(1L,"testReader"));
        IssueControllerTest.JUnitIssueResponse request = new IssueControllerTest.JUnitIssueResponse ();
        //    request.setId(5L);
        request.setReaderId(1L);
        request.setBookId(1L);


        IssueControllerTest.JUnitIssueResponse responseBody = webTestClient.post()
                .uri("/issue")
                .bodyValue(request)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(IssueControllerTest.JUnitIssueResponse.class)
                .returnResult().getResponseBody();

        Assertions.assertNotNull(responseBody);

        Assertions.assertEquals(request.getReaderId(), responseBody.getReaderId());
        Assertions.assertEquals(request.getBookId(), responseBody.getBookId());
        // Assertions.assertTrue(bookRepository.findById(request.getId()).isPresent());
    }
    @Test
    void testPut(){
        readerRepository.save(new Reader(2L,"testReader_2"));
        bookRepository.save(new Book(2L ,"testBook_2"));

       Issue issue = new Issue();
       issue.setId(2L);
       issue.setReaderId(2L);
       issue.setBookId(2L);
       issueRepository.save(issue);

        IssueControllerTest.JUnitIssueResponse responseBody = webTestClient.put()
                .uri("/issue/"+issue.getId())
                .bodyValue(issue)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(IssueControllerTest.JUnitIssueResponse.class)
                .returnResult().getResponseBody();

        Assertions.assertNotNull(responseBody);
        Assertions.assertNotNull(responseBody.getReturnDate());

    }

}

package ru.gb.springdemo.api;

import lombok.Data;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;
import ru.gb.springdemo.JUnitSpringBootBase;
import ru.gb.springdemo.model.Book;
import ru.gb.springdemo.repository.JpaBookRepository;

import java.util.List;
import java.util.Objects;

/*@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient

 */
public class BookControllerTest extends JUnitSpringBootBase {
    @Autowired
    WebTestClient webTestClient;
    @Autowired
    JpaBookRepository bookRepository;
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Data
    static class JUnitBookResponse {
        private Long id;
        private String name;
    }

  /* @Test
public void testBooks(){
        bookRepository.saveAll(List.of(
                new Book(1L,"Book_1"),
                new Book(2L,"Book_2")
        ));

        List<Book> expected = bookRepository.findAll();


       List<JUnitBookResponse> responseBody = webTestClient.get()
                .uri("/ui/books")
                // .retrieve
                .exchange()
                .expectStatus().isOk()
                .expectBody(new ParameterizedTypeReference<List<JUnitBookResponse>>() {
                })
                .returnResult()
                .getResponseBody();
       ;

        Assertions.assertEquals(expected.size(), responseBody.size());
        for (JUnitBookResponse bookResponse : responseBody) {
            boolean found = expected.stream()
                    .filter(it -> Objects.equals(it.getId(), bookResponse.getId()))
                    .anyMatch(it -> Objects.equals(it.getName(), bookResponse.getName()));
            Assertions.assertTrue(found);
        }
    }
*/

    @Test
    void testFindByIdSuccess() {

        Book expected = bookRepository.save(new Book (1L,"Book_1"));

        JUnitBookResponse responseBody = webTestClient.get()
                .uri("/book/" + expected.getId())
                .exchange()
                .expectStatus().isOk()
                .expectBody(JUnitBookResponse.class)
                .returnResult().getResponseBody();

        Assertions.assertNotNull(responseBody);
        Assertions.assertEquals(expected.getId(), responseBody.getId());
        Assertions.assertEquals(expected.getName(), responseBody.getName());
    }


@Test
    void testFindByIdNotFound() {
        Book expected = bookRepository.save(new Book (2L,"Book_2"));
        Long maxIdplus = jdbcTemplate.queryForObject("select max(id) from books", Long.class)+1;

        webTestClient.get()
                .uri("/book/" +maxIdplus)
                .exchange()
                .expectStatus().is5xxServerError();
    }
    @Test
    void testSave() {
        JUnitBookResponse request = new JUnitBookResponse();
    //    request.setId(5L);
        request.setName("Book");

        JUnitBookResponse responseBody = webTestClient.post()
                .uri("/book")
                .bodyValue(request)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(JUnitBookResponse.class)
                .returnResult().getResponseBody();

        Assertions.assertNotNull(responseBody);
        Assertions.assertNotNull(responseBody.getId());

        Assertions.assertEquals(request.getName(), responseBody.getName());
       // Assertions.assertTrue(bookRepository.findById(request.getId()).isPresent());
    }
    @Test
    void testDelete() {
        Book toDelete = bookRepository.save(new Book (6L,"Book_6"));
        JUnitBookResponse request = new JUnitBookResponse();
        request.setId(6L);
        request.setName("Book_6");


        JUnitBookResponse responseBody = webTestClient.delete()
                .uri("/book/"+request.getId())
               // .bodyValue(request)
                .exchange()
                .expectStatus().isOk()
                .expectBody(JUnitBookResponse.class)
                .returnResult().getResponseBody();

        Assertions.assertNull(responseBody);
              Assertions.assertFalse(bookRepository.findById(request.getId()).isPresent());
    }

}



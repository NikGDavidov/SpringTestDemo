package ru.gb.springdemo.api;

import lombok.Data;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.web.reactive.server.WebTestClient;
import ru.gb.springdemo.JUnitSpringBootBase;
import ru.gb.springdemo.model.Book;
import ru.gb.springdemo.model.Reader;
import ru.gb.springdemo.repository.JpaReaderRepository;


public class ReaderControllerTest extends JUnitSpringBootBase {
    @Autowired
    WebTestClient webTestClient;
    @Autowired
    JpaReaderRepository readerRepository;
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Data
    static class JUnitReaderResponse {
        private Long id;
        private String name;
    }
    @Test
    void testFindByIdSuccess() {

        Reader expected =readerRepository.save(new Reader (1L,"Reader_1"));

        ReaderControllerTest.JUnitReaderResponse responseBody = webTestClient.get()
                .uri("/reader/" + expected.getId())
                .exchange()
                .expectStatus().isOk()
                .expectBody(ReaderControllerTest.JUnitReaderResponse.class)
                .returnResult().getResponseBody();

        Assertions.assertNotNull(responseBody);
        Assertions.assertEquals(expected.getId(), responseBody.getId());
        Assertions.assertEquals(expected.getName(), responseBody.getName());
    }
    @Test
    void testFindByIdNotFound() {
        Reader expected = readerRepository.save(new Reader (2L,"Reader_2"));
        Long maxIdplus = jdbcTemplate.queryForObject("select max(id) from readers", Long.class)+1;

        webTestClient.get()
                .uri("/reader/" +maxIdplus)
                .exchange()
                .expectStatus().is5xxServerError();
    }
    @Test
    void testSave() {
        ReaderControllerTest.JUnitReaderResponse request = new ReaderControllerTest.JUnitReaderResponse();
       // request.setId(5L);
        request.setName("Reader");

        ReaderControllerTest.JUnitReaderResponse responseBody = webTestClient.post()
                .uri("/reader")
                .bodyValue(request)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(ReaderControllerTest.JUnitReaderResponse.class)
                .returnResult().getResponseBody();

        Assertions.assertNotNull(responseBody);
        Assertions.assertNotNull(responseBody.getId());

        Assertions.assertEquals(request.getName(), responseBody.getName());
     //   Assertions.assertTrue(readerRepository.findById(request.getId()).isPresent());

    }
    @Test
    void testDelete() {
        Reader toDelete = readerRepository.save(new Reader (6L,"Reader_6"));
       ReaderControllerTest.JUnitReaderResponse request = new ReaderControllerTest.JUnitReaderResponse();
          request.setId(6L);
        request.setName("Reader_6");


        ReaderControllerTest.JUnitReaderResponse responseBody = webTestClient.delete()
                .uri("/reader/"+request.getId())
                // .bodyValue(request)
                .exchange()
                .expectStatus().isOk()
                .expectBody(ReaderControllerTest.JUnitReaderResponse.class)
                .returnResult().getResponseBody();

        Assertions.assertNull(responseBody);
        Assertions.assertFalse(readerRepository.findById(request.getId()).isPresent());
    }


}

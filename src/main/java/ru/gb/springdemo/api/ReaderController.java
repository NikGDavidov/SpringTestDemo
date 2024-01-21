package ru.gb.springdemo.api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ru.gb.springdemo.model.Issue;
import ru.gb.springdemo.model.Reader;

import ru.gb.springdemo.service.ReaderService;

import java.util.List;
import java.util.NoSuchElementException;


// * 1.2 Реализовать контроллер по управлению читателями с ручками:
// GET /reader/{id} - получить описание читателя, DELETE /reader/{id} - удалить читателя, POST /reader - создать читателя

@Slf4j
@RestController
@RequestMapping("/reader")
public class ReaderController {
    @Autowired
    private ReaderService service;

    @GetMapping("/{id}")
    public Reader getReader(@PathVariable long id) {
        return service.getById(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable long id) {
        final boolean deleted = service.deleteById(id);

        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }


    @PostMapping
    ResponseEntity<Reader> addReader(@RequestBody ReaderRequest request) {
        final Reader reader;
        try {
            reader = service.addReader(request);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(reader);
    }
    // 2.2 В сервис читателя добавить ручку GET /reader/{id}/issue - вернуть список всех выдачей для данного читателя

    @GetMapping("{id}/issue")
    public List<Issue> readerIssues(@PathVariable long id) {
        return service.getIssues(id);

    }
}


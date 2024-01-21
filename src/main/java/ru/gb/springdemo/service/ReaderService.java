package ru.gb.springdemo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gb.springdemo.api.ReaderRequest;
import ru.gb.springdemo.model.Issue;
import ru.gb.springdemo.model.Reader;
import ru.gb.springdemo.repository.BookRepository;
import ru.gb.springdemo.repository.IssueRepository;
import ru.gb.springdemo.repository.ReaderRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ReaderService {
    private final ReaderRepository readerRepository;
    private final BookRepository bookRepository;
    private final IssueRepository issueRepository;
    public Reader getById(long id) {
        Reader reader = readerRepository.getReaderById(id);
        if (reader == null) {
            throw new NoSuchElementException("Не найден читатель с идентификатором \"" + id + "\"");
        }
        return reader;
    }

    public boolean deleteById(long id) {
        return readerRepository.deletetReaderById(id);
    }

    public Reader addReader(ReaderRequest request) {
        Reader reader = new Reader(request.getName());
        readerRepository.save(reader);
        return reader;
    }

    public List<Issue> getIssues(long id){
   return issueRepository.getIssues().stream().filter(it ->Objects.equals((it.getReaderId()),id)).toList();
    }
}

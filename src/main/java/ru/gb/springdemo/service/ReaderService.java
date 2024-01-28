package ru.gb.springdemo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gb.springdemo.api.ReaderRequest;
import ru.gb.springdemo.model.Issue;
import ru.gb.springdemo.model.Reader;
import ru.gb.springdemo.repository.BookRepository;
import ru.gb.springdemo.repository.IssueRepository;
import ru.gb.springdemo.repository.JpaReaderRepository;
import ru.gb.springdemo.repository.ReaderRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReaderService {
    // private final ReaderRepository readerRepository;
    // private final BookRepository bookRepository;
    private  final JpaReaderRepository readerRepository;
    private final IssueRepository issueRepository;
    public Reader getById(long id) {
      //  Reader reader = readerRepository.getReaderById(id);
        Optional<Reader> findReader = readerRepository.findById(id);
           //if (reader == null) {
        if (findReader.isEmpty())
            throw new NoSuchElementException("Не найден читатель с идентификатором \"" + id + "\"");

        //return reader;
        return findReader.get();
    }

    public boolean deleteById(long id) {

        //return readerRepository.deletetReaderById(id);
        readerRepository.deleteById(id);
        return true;
    }

    public Reader addReader(ReaderRequest request) {
        Reader reader = new Reader(request.getName());
        readerRepository.save(reader);
        return reader;
    }

    public List<Issue> getIssues(long id){
   return issueRepository.getIssues().stream().filter(it ->Objects.equals((it.getReaderId()),id)).toList();
    }

    public List<Reader> getReaders(){
        //return readerRepository.getReaders();
        return readerRepository.findAll();
    }



}

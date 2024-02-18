package ru.gb.springdemo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gb.springdemo.api.ReaderRequest;
import ru.gb.springdemo.model.Issue;
import ru.gb.springdemo.model.Reader;
import ru.gb.springdemo.repository.JpaIssueRepository;
import ru.gb.springdemo.repository.JpaReaderRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReaderService {

  private  final JpaReaderRepository readerRepository;
  private final JpaIssueRepository issueRepository;


    public Reader getById(long id) {
        Optional<Reader> findReader = readerRepository.findById(id);

        if (findReader.isEmpty())
            throw new NoSuchElementException("Не найден читатель с идентификатором \"" + id + "\"");
              return findReader.get();
    }

    public boolean deleteById(long id) {
        readerRepository.deleteById(id);
        return true;
    }

    public Reader addReader(ReaderRequest request) {
        Reader reader = new Reader(request.getName());
        readerRepository.save(reader);
        return reader;
    }

    public List<Issue> getIssues(long id){
         return issueRepository.findAll().stream().filter(it ->Objects.equals((it.getReaderId()),id)).toList();
    }

    public List<Reader> getReaders(){
            return readerRepository.findAll();
    }



}

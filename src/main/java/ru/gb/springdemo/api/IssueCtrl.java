package ru.gb.springdemo.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import ru.gb.springdemo.model.Issue;
import ru.gb.springdemo.service.IssuerService;

import java.util.List;

@Controller
public class IssueCtrl {
     //* 1.3 /ui/issues - на странице отображается таблица,
    // в которой есть столбцы (книга, читатель, когда взял, когда вернул (если не вернул - пустая ячейка)).
     @Autowired
     private IssuerService service;

    ///ui/books - на странице должен отобразиться список всех доступных книг в системе
    @GetMapping( "/ui/issues")
    public String issues(Model model) {
        List<Issue> issues  = service.getIssues();
        model.addAttribute("issues", issues);
        return "issues";
    }
}


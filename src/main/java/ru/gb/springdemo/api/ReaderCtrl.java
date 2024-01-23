package ru.gb.springdemo.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import ru.gb.springdemo.model.Reader;
import ru.gb.springdemo.service.ReaderService;

import java.util.List;

@Controller
public class ReaderCtrl {
    @Autowired
    ReaderService service;
    @GetMapping( "/ui/readers")
    public String readers(Model model) {
        List<Reader> readers= service.getReaders();
        model.addAttribute("readers", readers);
        return "readers";
    }

}

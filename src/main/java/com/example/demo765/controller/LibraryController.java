package com.example.demo765.controller;

import com.example.demo765.entity.Book;
import com.example.demo765.mapper.BookMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/library")
public class LibraryController {
    @Autowired
    BookMapper bookMapper;

    @GetMapping("/findAllBook")
    public List<Book> findAllBook(){
        return bookMapper.findAll();
    }

    @GetMapping("/findAllBookByCid")
    public List<Book> findAllBookByCid(int cid){
        return bookMapper.findBookByCid(cid);
    }
}

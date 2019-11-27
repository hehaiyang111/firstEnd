package com.example.demo765.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.demo765.entity.Book;
import com.example.demo765.entity.Search;
import com.example.demo765.entity.User;
import com.example.demo765.mapper.BookMapper;
import com.example.demo765.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/library")
@CrossOrigin
public class LibraryController {
    @Autowired
    BookMapper bookMapper;
    @Autowired
    BookService bookService;
    JSONObject jsonObject = new JSONObject();

    @GetMapping("/findAllBook")
    public List<Book> findAllBook(){
//        jsonObject.put("data",bookMapper.findAll());
        return bookMapper.findAll();

//        return jsonObject;
    }

    @CrossOrigin
    @PostMapping("/findAllBookByCid")
    public List<Book> findAllBookByCid(@RequestBody Book book){
        int cid = book.getCid();
        System.out.println("获取到的cid：" +cid);
        if(cid == 0){
            //点击全部查找全部的图书
            return bookMapper.findAll();
        }else{
            return bookMapper.findBookByCid(cid);
        }
    }

    @PostMapping("/findByAuthorOrName")
    public List<Book> findByAuthorOrName(@RequestBody Search search){
        String keyword = search.getKeywords();
        if("".equals(keyword)){
            return bookMapper.findAll();
        }else{
            return bookMapper.findBookByAuthorOrBookName(keyword);
        }
    }

    @RequestMapping("/deleteBook")
    public void deleteBook(@RequestBody User user){
        int id = user.getId();
        System.out.printf("id:" +id);
        bookMapper.deleteByPrimaryKey(id);
    }

    @RequestMapping("/addorupdatebook")
    public int addOrUpdateBook(@RequestBody Book book){
        return bookService.addOrUpdate(book);
    }
}

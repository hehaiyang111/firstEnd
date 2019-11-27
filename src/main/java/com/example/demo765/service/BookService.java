package com.example.demo765.service;

import com.example.demo765.entity.Book;
import com.example.demo765.mapper.BookMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {
    @Autowired
    BookMapper bookMapper;
    public int addOrUpdate(Book book){
        if(book.getId()==null || book.getId()==0){
            return bookMapper.insertSelective(book);
        }else{
            return bookMapper.updateByPrimaryKeySelective(book);
        }
    }
}

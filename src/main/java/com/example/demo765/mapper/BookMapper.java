package com.example.demo765.mapper;

import com.example.demo765.entity.Book;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BookMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Book record);

    int insertSelective(Book record);

    Book selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Book record);

    int updateByPrimaryKey(Book record);

    List<Book> findAll();

    List<Book> findBookByCid(int cid);

    List<Book> findBookByAuthorOrBookName(String keywords);
}
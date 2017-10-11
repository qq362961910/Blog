package com.jy.blog.service.impl;

import com.jy.blog.blog.dao.BookDao;
import com.jy.blog.blog.dao.BaseDao;
import com.jy.blog.entity.Book;
import com.jy.blog.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl extends BaseServiceImpl<Book> implements BookService {

    @Autowired
    private BookDao bookDao;

    @Override
    public BaseDao<Book> getBaseDao() {
        return bookDao;
    }
}

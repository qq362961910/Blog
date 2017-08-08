package com.jy.service.impl;

import com.jy.dao.BaseDao;
import com.jy.dao.BookDao;
import com.jy.entity.Book;
import com.jy.service.BookService;
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

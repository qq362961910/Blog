package com.jy.response.service;

import com.jy.entity.Book;
import com.jy.response.entity.BookWrapper;
import org.springframework.stereotype.Component;

@Component
public class BookWrapperService extends ResponseBaseService {

    private static final BookWrapper empty = new BookWrapper();

    public BookWrapper buildBookWrapper(Book book) {
        if (book == null) {
            return empty;
        }
        BookWrapper bookWrapper = new BookWrapper();
        bookWrapper.setId(book.getId());
        bookWrapper.setName(book.getName());
        return bookWrapper;
    }
}

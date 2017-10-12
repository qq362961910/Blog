package com.jy.blog.response.service;

import com.jy.blog.entity.Book;
import com.jy.blog.response.entity.BaseWrapper;
import com.jy.blog.response.entity.BookWrapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BookWrapperService extends PageableWrapperService<Book> {

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

    @Override
    public List<? extends BaseWrapper> buildEntityListWrapper(List<Book> books) {
        if (books == null) {
            return null;
        }
        List<BookWrapper> bookWrapperList = new ArrayList<>(books.size());
        books.forEach(book -> bookWrapperList.add(buildBookWrapper(book)));
        return bookWrapperList;
    }
}

// IBookManager.aidl
package com.shadowwingz.androidlifedemo.binderdemo;

import com.shadowwingz.androidlifedemo.binderdemo.Book;

interface IBookManager {
    List<Book> getBookList();
    void addBook(in Book book);
}

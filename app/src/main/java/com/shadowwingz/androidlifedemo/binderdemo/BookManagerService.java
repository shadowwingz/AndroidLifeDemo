package com.shadowwingz.androidlifedemo.binderdemo;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;

import com.shadowwingz.androidlifedemo.utils.LogUtil;
import com.shadowwingz.androidlifedemo.utils.Util;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class BookManagerService extends Service {

    private CopyOnWriteArrayList<Book> mBookList = new CopyOnWriteArrayList<>();

    private Binder mBinder = new IBookManager.Stub() {
        @Override
        public List<Book> getBookList() throws RemoteException {
            LogUtil.d("服务端被调用，返回图书列表： " + mBookList + " 当前进程：" + Util.getProcessName(BookManagerService.this));
            return mBookList;
        }

        @Override
        public void addBook(Book book) throws RemoteException {
            LogUtil.d("服务端被调用，添加图书： " + book + " 当前进程：" + Util.getProcessName(BookManagerService.this));
            mBookList.add(book);
        }
    };

    public BookManagerService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mBookList.add(new Book(1, "Android"));
        mBookList.add(new Book(2, "Ios"));
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }
}

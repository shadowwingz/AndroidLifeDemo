package com.shadowwingz.androidlifedemo.binderdemo;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;

import com.shadowwingz.androidlifedemo.R;
import com.shadowwingz.androidlifedemo.utils.LogUtil;
import com.shadowwingz.androidlifedemo.utils.Util;

import java.util.List;

public class BookManagerActivity extends AppCompatActivity {

    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            IBookManager bookManager = IBookManager.Stub.asInterface(service);
            try {
                List<Book> bookList = bookManager.getBookList();
                LogUtil.d("客户端添加图书之前，查询到的图书列表 " + bookList +
                        " 当前进程：" + Util.getProcessName(BookManagerActivity.this));

                Book newBook = new Book(3, "Android 开发艺术探索");
                LogUtil.d("客户端添加图书 " + newBook +
                        " 当前进程：" + Util.getProcessName(BookManagerActivity.this));
                bookManager.addBook(newBook);

                List<Book> newList = bookManager.getBookList();
                LogUtil.d("客户端添加图书之后，查询到的图书列表 " + newList +
                        " 当前进程：" + Util.getProcessName(BookManagerActivity.this));
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_manager);
        Intent intent = new Intent(this, BookManagerService.class);
        bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
    }
}

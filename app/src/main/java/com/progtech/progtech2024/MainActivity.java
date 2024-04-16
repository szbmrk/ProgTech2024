package com.progtech.progtech2024;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.provider.ContactsContract;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BankDatabase db = Room.databaseBuilder(this, BankDatabase.class, "bank").allowMainThreadQueries().build();

        AccountDao accountDao = db.accountDao();
    }
}
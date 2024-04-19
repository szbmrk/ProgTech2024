package com.progtech.progtech2024;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;

import com.progtech.progtech2024.database.Account;
import com.progtech.progtech2024.database.AccountDao;
import com.progtech.progtech2024.database.BankDatabase;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BankDatabase db = Room.databaseBuilder(this, BankDatabase.class, "bank").allowMainThreadQueries().build();

        AccountDao accountDao = db.accountDao();

        Account account = new Account("test user", "password", 500, true);
        // example reg
        if (accountDao.isUsernameAvailable(account.username)) {
            accountDao.register(account);
        } else {
        }
    }
}
package com.progtech.progtech2024;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "bank.db";
    public static final int DATABASE_VERSION = 1;
    public ArrayList<IDatabaseTable> tables = new ArrayList<>();

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        tables.add(new AccountsTable());
        tables.add(new TransactionsTable());

        for (IDatabaseTable table:
             tables
        ) {
            db.execSQL(table.getCreateQuery());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion < newVersion) {
        }
    }
}

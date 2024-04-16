package com.progtech.progtech2024;

public final class AccountsTable implements IDatabaseTable {
    @Override
    public String getCreateQuery() {
        return "CREATE TABLE accounts (" +
                "id INTEGER PRIMARY KEY, " +
                "username TEXT NOT NULL, " +
                "password TEXT NOT NULL, " +
                "balance INTEGER NOT NULL, " +
                "is_junior BOOLEAN NOT NULL, " +
                "created_at DATETIME DEFAULT CURRENT_TIMESTAMP)";
    }
}

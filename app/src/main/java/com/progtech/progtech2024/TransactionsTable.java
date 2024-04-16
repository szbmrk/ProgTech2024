package com.progtech.progtech2024;

public final class TransactionsTable implements IDatabaseTable {
    @Override
    public String getCreateQuery() {
        return "CREATE TABLE transactions (" +
                "id INTEGER PRIMARY KEY, " +
                "comment TEXT, " +
                "from_account_id INTEGER NOT NULL, " +
                "to_account_id INTEGER, " +
                "transaction_type TEXT NOT NULL, " +
                "amount INTEGER NOT NULL, " +
                "created_at DATETIME DEFAULT CURRENT_TIMESTAMP, " +
                "FOREIGN KEY (from_account_id) REFERENCES accounts(id) ON DELETE CASCADE, " +
                "FOREIGN KEY (to_account_id) REFERENCES accounts(id) ON DELETE CASCADE)";
    }
}

package com.progtech.progtech2024.database.models;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TransactionUnitTest {
    @Test
    public void testTransactionEquals_true() {
        Transaction transaction1 = new Transaction(1, "12345", 1, 1, "WITHDRAW", 500);
        Transaction transaction2 = new Transaction(1, "12345", 1, 1, "WITHDRAW", 500);

        assertEquals(true, transaction1.equals(transaction2));
    }

    @Test
    public void testTransactionEquals_false() {
        Transaction transaction1 = new Transaction(1, "12345", 1, 1, "WITHDRAW", 500);
        Transaction transaction2 = new Transaction(2, "12345", 1, 1, "WITHDRAW", 500);

        assertEquals(false, transaction1.equals(transaction2));
    }
}

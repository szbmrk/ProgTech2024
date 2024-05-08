package com.progtech.progtech2024.builder;

import static org.junit.Assert.assertEquals;

import com.progtech.progtech2024.database.models.Transaction;

import org.junit.Test;

public class TransactionBuilderUnitTest {
    @Test
    public void testTransactionBuilder_buildDummyTransaction() {
        Transaction transaction1 = new Transaction(1, "message", 1, 1, "DEPOSIT", 500);
        TransactionBuilder tb = new TransactionBuilder();
        Transaction transaction2 = tb.setTransactionId(1).setMessage("message")
                .setFromAccountId(1).setToAccountId(1).setTransactionType("Deposit")
                .setAmount(500).build();

        assertEquals(transaction1, transaction2);
    }
}

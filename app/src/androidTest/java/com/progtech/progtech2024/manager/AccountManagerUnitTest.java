package com.progtech.progtech2024.manager;

import static org.junit.Assert.assertEquals;

import com.progtech.progtech2024.database.models.Account;

import org.junit.Test;

public class AccountManagerUnitTest {
    @Test
    public void testSetAndGetLoggedInAccount() {
        Account account = new Account(1, "12345", "user1", "pass1", 500, false);
        AccountManager.getInstance().setLoggedInAccount(account);
        assertEquals(account, AccountManager.getInstance().getLoggedInAccount());
    }
}

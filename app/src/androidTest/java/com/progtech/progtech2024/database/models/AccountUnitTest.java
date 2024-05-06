package com.progtech.progtech2024.database.models;

import static org.junit.Assert.assertEquals;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import com.progtech.progtech2024.database.repositories.AccountRepository;
import com.progtech.progtech2024.helper.TestRepositoriesHelper;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class AccountUnitTest {

    AccountRepository accountRepository;

    @Before
    public void SetUp() throws Exception {
        accountRepository = TestRepositoriesHelper.GetTestAccountRepository();
    }

    @Test
    public void testModifyBalance() throws Exception {
        Account account = new Account(1, "12345", "user1", "pass1", 500, false);
        accountRepository.Register(account);
        account.TestModifyBalance(InstrumentationRegistry.getInstrumentation().getTargetContext(), 1000);
        assertEquals(1000, account.balance);
    }

    @Test
    public void testAccountEquals_true() {
        Account account1 = new Account(1, "12345", "user1", "pass1", 500, false);
        Account account2 = new Account(1, "12345", "user1", "pass1", 500, false);

        assertEquals(true, account1.equals(account2));
    }

    @Test
    public void testAccountEquals_false() {
        Account account1 = new Account(1, "12345", "user1", "pass1", 500, false);
        Account account2 = new Account(2, "12345", "user2", "pass2", 500, false);

        assertEquals(false, account1.equals(account2));
    }
}

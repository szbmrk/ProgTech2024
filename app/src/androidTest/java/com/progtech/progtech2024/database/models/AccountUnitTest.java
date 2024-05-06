package com.progtech.progtech2024.database.models;

import static org.junit.Assert.assertEquals;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import com.progtech.progtech2024.database.repositories.AccountRepository;
import com.progtech.progtech2024.manager.DatabaseManager;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.ExecutionException;

@RunWith(AndroidJUnit4.class)
public class AccountUnitTest {

    AccountRepository accountRepository;

    @Before
    public void SetUp() throws Exception {
        accountRepository = DatabaseManager.getInstance
                        (InstrumentationRegistry.getInstrumentation().getTargetContext())
                .GetTestAccountRepository();
        accountRepository.DeleteAll();
    }

    @Test
    public void testModifyBalance() throws Exception {
        Account account = new Account(1, "12345", "user1", "pass1", 500, false);
        accountRepository.Register(account);
        account.TestModifyBalance(InstrumentationRegistry.getInstrumentation().getTargetContext(), 1000);
        assertEquals(1000, account.balance);
    }
}

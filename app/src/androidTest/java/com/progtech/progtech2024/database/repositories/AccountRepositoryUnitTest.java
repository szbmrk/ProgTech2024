package com.progtech.progtech2024.database.repositories;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.progtech.progtech2024.database.models.Account;
import com.progtech.progtech2024.exceptions.database.InvalidUsernameOrPasswordException;
import com.progtech.progtech2024.helper.DummyAccountCreator;
import com.progtech.progtech2024.helper.TestRepositoriesHelper;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class AccountRepositoryUnitTest {
    private AccountRepository accountRepository;

    @Before
    public void SetUp() throws Exception {
        accountRepository = TestRepositoriesHelper.GetTestAccountRepository();
        TestRepositoriesHelper.DeleteDataFromTestRepositories();
    }

    @Test
    public void testRegister() throws Exception {
        Account account = DummyAccountCreator.CreateDummyAccountAndPostItToDB(500, false);

        long newId = accountRepository.Register(account);
        assertEquals(1, newId);
    }

    @Test
    public void testIsUsernameAvailable_UsernameAvailable() throws Exception {
        String availableUsername = "available_username";

        boolean isAvailable = accountRepository.IsUsernameAvailable(availableUsername);
        assertEquals(true, isAvailable);
    }

    @Test
    public void testIsUsernameAvailable_UsernameTaken() throws Exception {
        Account account = DummyAccountCreator.CreateDummyAccountAndPostItToDB(500, false);
        boolean isAvailable = accountRepository.IsUsernameAvailable(account.username);

        assertEquals(false, isAvailable);
    }

    @Test
    public void testLogin_SuccessfulLogin() throws Exception {
        Account expectedAccount = DummyAccountCreator.CreateDummyAccountAndPostItToDB(500, false);
        Account loggedIn = accountRepository.Login(expectedAccount.username, expectedAccount.password);

        assertEquals(expectedAccount, loggedIn);
    }

    @Test
    public void testLogin_InvalidLogin() throws Exception {
        String username = "wrong_username";
        String password = "wrong_password";

        try {
            Account account = accountRepository.Login(username, password);
            assertNull(account);
        } catch (InvalidUsernameOrPasswordException e) {
            assertEquals("Invalid username or password!", e.getMessage());
        }
    }

    @Test
    public void testModifyBalance() throws Exception {
        int newBalance = 1000;

        Account account = DummyAccountCreator.CreateDummyAccountAndPostItToDB(500, false);

        boolean rowsAffected = accountRepository.ModifyBalance(account.id, newBalance);

        assertEquals(true, rowsAffected);
    }
}

package com.progtech.progtech2024.database.daos;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;
import com.progtech.progtech2024.database.models.Account;
import com.progtech.progtech2024.database.repositories.AccountRepository;
import com.progtech.progtech2024.exceptions.database.FailedQueryException;
import com.progtech.progtech2024.exceptions.database.InvalidUsernameOrPasswordException;
import com.progtech.progtech2024.exceptions.database.UserAlreadyTakenException;
import com.progtech.progtech2024.manager.DatabaseManager;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.ExecutionException;

@RunWith(AndroidJUnit4.class)
public class AccountRepositoryUnitTest {
    private AccountRepository accountRepository;

    @Before
    public void SetUp() {
        accountRepository = DatabaseManager.getInstance
                        (InstrumentationRegistry.getInstrumentation().getTargetContext())
                .GetAccountRepository();
    }

    @Test
    public void testRegister() throws FailedQueryException, ExecutionException, UserAlreadyTakenException, InterruptedException {
        Account account = new Account(1, "12345", "user1", "pass1", 500, true);

        long newId = accountRepository.Register(account);
        assertEquals(1, newId);
    }

    @Test
    public void testIsUsernameAvailable_UsernameAvailable() throws ExecutionException, InterruptedException {
        String availableUsername = "available_username";

        boolean isAvailable = accountRepository.IsUsernameAvailable(availableUsername);
        assertEquals(true, isAvailable);
    }

    @Test
    public void testIsUsernameAvailable_UsernameTaken() throws FailedQueryException, UserAlreadyTakenException, ExecutionException, InterruptedException {
        Account account = new Account(2, "12345", "taken_username", "pass1", 500, true);

        accountRepository.Register(account);
        boolean isAvailable = accountRepository.IsUsernameAvailable(account.username);

        assertEquals(false, isAvailable);
    }

    @Test
    public void testLogin_SuccessfulLogin() throws Exception {
        String accountNumber = "12345";
        String username = "user3";
        String password = "password3";

        Account expectedAccount = new Account(3, accountNumber, username, password, 0, false);
        accountRepository.Register(expectedAccount);
        Account loggedIn = accountRepository.Login(username, password);

        assertEquals(expectedAccount, loggedIn);
    }

    @Test
    public void testLogin_InvalidLogin() throws ExecutionException, InterruptedException {
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
        int userId = 4;
        int newBalance = 100;

        String accountNumber = "12345";
        String username = "user4";
        String password = "password1";

        Account expectedAccount = new Account(userId, accountNumber, username, password, 0, false);
        accountRepository.Register(expectedAccount);

        boolean rowsAffected = accountRepository.ModifyBalance(userId, newBalance);

        assertEquals(true, rowsAffected);
    }
}

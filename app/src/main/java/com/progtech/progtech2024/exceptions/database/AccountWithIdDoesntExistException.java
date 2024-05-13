package com.progtech.progtech2024.exceptions.database;

public class AccountWithIdDoesntExistException extends Exception {
    public AccountWithIdDoesntExistException()
    {
        super("Account with this account id doesn't exists!");
    }
}

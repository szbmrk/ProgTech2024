package com.progtech.progtech2024.exceptions.database;

public class AccountNumDoesntExistException extends Exception{
    public AccountNumDoesntExistException()
    {
        super("Account with this account number doesn't exists!");
    }
}

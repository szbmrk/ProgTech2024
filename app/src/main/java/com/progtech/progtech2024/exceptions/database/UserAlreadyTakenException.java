package com.progtech.progtech2024.exceptions.database;

public class UserAlreadyTakenException extends Exception {
    public UserAlreadyTakenException() {
        super("Username already taken!");
    }
}

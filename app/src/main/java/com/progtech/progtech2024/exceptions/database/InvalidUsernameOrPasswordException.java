package com.progtech.progtech2024.exceptions.database;

public class InvalidUsernameOrPasswordException extends Exception {
    public InvalidUsernameOrPasswordException() {
        super("Invalid username or password!");
    }
}

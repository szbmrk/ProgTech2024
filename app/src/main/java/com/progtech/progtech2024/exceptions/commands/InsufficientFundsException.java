package com.progtech.progtech2024.exceptions.commands;

public class InsufficientFundsException extends Exception {
    public InsufficientFundsException() {
        super("Insufficient funds!");
    }
}

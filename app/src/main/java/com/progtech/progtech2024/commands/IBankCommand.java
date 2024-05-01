package com.progtech.progtech2024.commands;

import java.util.concurrent.ExecutionException;

public interface IBankCommand {
    void Call() throws ExecutionException, InterruptedException;
    void Undo() throws  ExecutionException, InterruptedException;
    boolean PostTransaction() throws ExecutionException, InterruptedException;
}

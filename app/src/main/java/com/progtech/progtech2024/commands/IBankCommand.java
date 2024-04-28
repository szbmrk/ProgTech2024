package com.progtech.progtech2024.commands;

import java.util.concurrent.ExecutionException;

public interface IBankCommand {
    public void Call() throws ExecutionException, InterruptedException;
    public boolean PostTransaction() throws ExecutionException, InterruptedException;
    public void Undo();
}

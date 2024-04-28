package com.progtech.progtech2024.commands;

public interface IBankCommand {
    public void Call();
    public boolean PostTransaction();
    public void Undo();
}

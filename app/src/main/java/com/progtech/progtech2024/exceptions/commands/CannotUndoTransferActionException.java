package com.progtech.progtech2024.exceptions.commands;

public class CannotUndoTransferActionException extends Exception{
    public CannotUndoTransferActionException()
    {
        super("In order to undo a transaction you have to go to the bank!");
    }
}

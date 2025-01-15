package com.example;

public class TransactionProcessed implements TransactionCommand {
    public final String transactionId;

    public TransactionProcessed(String transactionId) {
        this.transactionId = transactionId;
    }
}

package com.example;

public class TransactionFailed implements TransactionCommand {
    public final String transactionId;
    public final String reason;

    public TransactionFailed(String transactionId, String reason) {
        this.transactionId = transactionId;
        this.reason = reason;
    }
}

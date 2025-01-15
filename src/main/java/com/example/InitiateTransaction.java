package com.example;

public class InitiateTransaction implements TransactionCommand {
    public final String transactionId;
    public final String sourceAccount;
    public final String destinationAccount;
    public final double amount;

    public InitiateTransaction(String transactionId, String sourceAccount, String destinationAccount, double amount) {
        this.transactionId = transactionId;
        this.sourceAccount = sourceAccount;
        this.destinationAccount = destinationAccount;
        this.amount = amount;
    }
}

package com.example;

public class DebitAccount implements AccountCommand {
    public final String accountId;
    public final double amount;

    public DebitAccount(String accountId, double amount) {
        this.accountId = accountId;
        this.amount = amount;
    }
}

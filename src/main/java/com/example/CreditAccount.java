package com.example;

public class CreditAccount implements AccountCommand {
    public final String accountId;
    public final double amount;

    public CreditAccount(String accountId, double amount) {
        this.accountId = accountId;
        this.amount = amount;
    }
}

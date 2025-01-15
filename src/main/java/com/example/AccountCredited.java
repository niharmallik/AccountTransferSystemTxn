package com.example;

public class AccountCredited implements AccountEvent {
    public final String accountId;
    public final double amount;

    public AccountCredited(String accountId, double amount) {
        this.accountId = accountId;
        this.amount = amount;
    }
}

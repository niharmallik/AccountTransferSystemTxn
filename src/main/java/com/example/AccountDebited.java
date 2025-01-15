package com.example;

public class AccountDebited implements AccountEvent {
    public final String accountId;
    public final double amount;

    public AccountDebited(String accountId, double amount) {
        this.accountId = accountId;
        this.amount = amount;
    }
}

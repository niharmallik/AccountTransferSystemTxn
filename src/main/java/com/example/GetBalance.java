package com.example;

public class GetBalance implements AccountCommand {
    public final String accountId;

    public GetBalance(String accountId) {
        this.accountId = accountId;
    }
}

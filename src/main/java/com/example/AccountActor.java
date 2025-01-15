package com.example;

import akka.persistence.AbstractPersistentActor;

public class AccountActor extends AbstractPersistentActor {
    private final String accountId;
    private double balance;

    public AccountActor(String accountId, double initialBalance) {
        this.accountId = accountId;
        this.balance = initialBalance;
    }

    @Override
    public String persistenceId() {
        return accountId;
    }

    @Override
    public Receive createReceiveRecover() {
        return receiveBuilder()
            .match(AccountDebited.class, event -> balance -= event.amount)
            .match(AccountCredited.class, event -> balance += event.amount)
            .build();
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder()
            .match(DebitAccount.class, command -> {
                if (balance >= command.amount) {
                    AccountDebited event = new AccountDebited(command.accountId, command.amount);
                    persist(event, e -> {
                        balance -= e.amount;
                        System.out.println("Debited " + e.amount + " from account: " + accountId);
                        getSender().tell(e, getSelf());
                    });
                } else {
                    getSender().tell(new TransactionFailed(command.accountId, "Insufficient funds"), getSelf());
                }
            })
            .match(CreditAccount.class, command -> {
                AccountCredited event = new AccountCredited(command.accountId, command.amount);
                persist(event, e -> {
                    balance += e.amount;
                    System.out.println("Credited " + e.amount + " to account: " + accountId);
                    getSender().tell(e, getSelf());
                });
            })
            .match(GetBalance.class, command -> getSender().tell(balance, getSelf()))
            .build();
    }
}

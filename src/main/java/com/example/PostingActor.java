package com.example;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;

public class PostingActor extends AbstractActor {
    private final ActorRef sourceAccount;
    private final ActorRef destinationAccount;

    public PostingActor(ActorRef sourceAccount, ActorRef destinationAccount) {
        this.sourceAccount = sourceAccount;
        this.destinationAccount = destinationAccount;
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder()
            .match(InitiateTransaction.class, tx -> {
                sourceAccount.tell(new DebitAccount(tx.sourceAccount, tx.amount), getSelf());
            })
            .match(AccountDebited.class, event -> {
                destinationAccount.tell(new CreditAccount(event.accountId, event.amount), getSelf());
            })
            .match(AccountCredited.class, event -> {
                System.out.println("Transaction completed successfully for: " + event.accountId);
            })
            .match(TransactionFailed.class, failure -> {
                System.out.println("Transaction failed: " + failure.reason);
            })
            .build();
    }
}

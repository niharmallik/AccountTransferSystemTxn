package com.example;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

public class Main {
    public static void main(String[] args) {
        ActorSystem system = ActorSystem.create("AccountTransferSystem");

        // Create account actors
        ActorRef sourceAccount = system.actorOf(Props.create(AccountActor.class, () -> new AccountActor("source1", 1000)), "sourceAccount");
        ActorRef destinationAccount = system.actorOf(Props.create(AccountActor.class, () -> new AccountActor("destination1", 500)), "destinationAccount");

        // Create component actors
        ActorRef validation = system.actorOf(Props.create(ValidationActor.class), "validation");
        ActorRef clearing = system.actorOf(Props.create(ClearingActor.class), "clearing");
        ActorRef liquidity = system.actorOf(Props.create(LiquidityActor.class), "liquidity");
        ActorRef sanction = system.actorOf(Props.create(SanctionActor.class), "sanction");
        ActorRef posting = system.actorOf(Props.create(PostingActor.class, sourceAccount, destinationAccount), "posting");

        // Simulate a transaction
        InitiateTransaction transaction = new InitiateTransaction("txn1", "source1", "destination1", 200);

        // Process the transaction
        validation.tell(transaction, clearing);
       /* clearing.tell(transaction, liquidity);
        liquidity.tell(transaction, sanction);
        sanction.tell(transaction, posting);*/

        // Wait for the system to process
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        system.terminate();
    }
}

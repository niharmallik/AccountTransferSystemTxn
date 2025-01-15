package com.example;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;

import java.time.Duration;

public class SanctionActor extends AbstractActor {
    @Override
    public Receive createReceive() {
        return receiveBuilder()
            .match(InitiateTransaction.class, tx -> {
                System.out.println("Sanction check passed for transaction: " + tx.transactionId);
          //   getSender().tell(tx, getSelf());
                ActorRef nextActor = getContext().actorSelection("../posting").resolveOne(Duration.ofSeconds(1)).toCompletableFuture().join();
                System.out.println("nextActor -san: " + nextActor);
                nextActor.tell(tx, getSelf());
            })
            .build();
    }
}

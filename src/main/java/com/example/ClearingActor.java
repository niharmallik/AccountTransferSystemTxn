package com.example;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;

import java.time.Duration;

public class ClearingActor extends AbstractActor {
    @Override
    public Receive createReceive() {
        return receiveBuilder()
            .match(InitiateTransaction.class, tx -> {
                System.out.println("Clearing successful for transaction: " + tx.transactionId);
                  /* System.out.println("getSender liq: " + getSender());
                System.out.println("getSelf -liq: " + getSelf());*/
             //   getSender().tell(tx, getSelf());
                ActorRef nextActor = getContext().actorSelection("../liquidity").resolveOne(Duration.ofSeconds(1)).toCompletableFuture().join();
                System.out.println("nextActor -liq: " + nextActor);
                nextActor.tell(tx, getSelf());
            })
            .build();
    }
}

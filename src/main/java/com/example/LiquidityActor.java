package com.example;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;

import java.time.Duration;

public class LiquidityActor extends AbstractActor {
    @Override
    public Receive createReceive() {
        return receiveBuilder()
            .match(InitiateTransaction.class, tx -> {
                System.out.println("Liquidity check passed for transaction: " + tx.transactionId);
              /*  System.out.println("getSender liq: " + getSender());
                System.out.println("getSelf -liq: " + getSelf());*/
              //  getSender().tell(tx, getSelf());
                ActorRef nextActor = getContext().actorSelection("../sanction").resolveOne(Duration.ofSeconds(1)).toCompletableFuture().join();
                System.out.println("nextActor -liq: " + nextActor);
                 nextActor.tell(tx, getSelf());
            })
            .build();
    }
}

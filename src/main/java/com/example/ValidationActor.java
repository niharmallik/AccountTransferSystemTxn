package com.example;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;

import java.time.Duration;

public class ValidationActor extends AbstractActor {
    @Override
    public Receive createReceive() {
        return receiveBuilder()
            .match(InitiateTransaction.class, tx -> {
                System.out.println("Validation passed for transaction: " + tx.transactionId);
               /* System.out.println("getSender val: " + getSender());
                System.out.println("getSelf -val: " + getSelf());*/
                //getSender().tell(tx, getSelf());
                ActorRef nextActor = getContext().actorSelection("../clearing").resolveOne(Duration.ofSeconds(1)).toCompletableFuture().join();
                System.out.println("nextActor -val: " + nextActor);
                nextActor.tell(tx, getSelf());
            })
            .build();
    }
}

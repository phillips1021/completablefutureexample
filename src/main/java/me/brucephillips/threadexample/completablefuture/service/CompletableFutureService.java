package me.brucephillips.threadexample.completablefuture.service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CompletableFutureService {

    public Future<String> calculateAsync() throws InterruptedException {
        CompletableFuture<String> completableFuture
                = new CompletableFuture<>();

        Executors.newCachedThreadPool().submit(() -> {
            Thread.sleep(500);
            completableFuture.complete("Hello");
            return null;
        });

        return completableFuture;
    }

    public Future<String> lambdaExample() throws InterruptedException {



        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> "Hello");

        return completableFuture.thenApply(s -> s + " World");


    }
}

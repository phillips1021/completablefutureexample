package me.brucephillips.threadexample.completablefuture.app;

import me.brucephillips.threadexample.completablefuture.service.CompletableFutureService;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class App {


    public static void main(String[] args) {

        CompletableFutureService completableFutureService = new CompletableFutureService();
        String result = null;

        try {

            Future<String> completableFuture = completableFutureService.calculateAsync();

            result = completableFuture.get();

            System.out.printf("Result is %s \n", result);

            Future<String> lambdaFuture = completableFutureService.lambdaExample();

            result = lambdaFuture.get();

            System.out.printf("Result is %s \n", result);

            CompletableFuture<String> future1
                    = CompletableFuture.supplyAsync(() -> "Hello");
            CompletableFuture<String> future2
                    = CompletableFuture.supplyAsync(() -> "Beautiful");
            CompletableFuture<String> future3
                    = CompletableFuture.supplyAsync(() -> "World");

            CompletableFuture<Void> combinedFuture
                    = CompletableFuture.allOf(future1, future2, future3);

            combinedFuture.get();

            String combined = Stream.of(future1, future2, future3)
                    .map(CompletableFuture::join)
                    .collect(Collectors.joining(" "));

            System.out.printf("Result of the combined futures is %s \n", combined);

// Create a CompletableFuture
            CompletableFuture<String> whatsYourNameFuture = CompletableFuture.supplyAsync(() -> {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    throw new IllegalStateException(e);
                }
                return "Rajeev";
            });

// Attach a callback to the Future using thenApply()
            CompletableFuture<String> greetingFuture = whatsYourNameFuture.thenApply(name -> {
                return "Hello " + name;
            });

// Block and get the result of the future.
            System.out.println(greetingFuture.get()); // Hello Rajeev



        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }




    }


}

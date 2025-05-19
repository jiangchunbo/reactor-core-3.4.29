package com.example;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

public class FlatMapDemo {
    public static void main(String[] args) throws InterruptedException {
        Flux<Integer> flux = Flux.just(1, 2, 3)
                .flatMap(i -> Mono.just(i * 10)
                        .subscribeOn(Schedulers.parallel()));

        while (true) {
            flux.subscribe(System.out::print);
            System.out.println();
            Thread.sleep(1);
        }
        // 输出的结果依次可能为: 10, 20, 30，但顺序可能会因为并发执行而有所不同
    }
}

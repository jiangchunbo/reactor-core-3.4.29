package com.example;

import org.reactivestreams.Subscription;
import reactor.core.publisher.Mono;
import reactor.core.publisher.MonoJust;

import java.util.function.Consumer;

public class ReactorDemo {
    public static void main(String[] args) {
        // 使用 just 创建一个 Mono
        Mono<String> mono = Mono.just("Hello, Reactor Mono!")
                .doOnSubscribe(new Consumer<Subscription>() {
                    @Override
                    public void accept(Subscription subscription) {
                        System.out.println("订阅发生, 当前 subscription" + subscription);
                    }
                })
                .doOnNext(System.out::println);

        // 订阅并触发执行
        mono.subscribeOn(reactor.core.scheduler.Schedulers.elastic());

        mono.subscribe();
    }
}

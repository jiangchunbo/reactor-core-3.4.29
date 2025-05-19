package com.example;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Hooks;

public class HookDemo {
    public static void main(String[] args) {
        // 设置 onEachOperator 钩子，在每个操作符创建时打印日志信息
        Hooks.onEachOperator("logHook", publisher -> {
            System.out.println("拦截到操作符创建： " + publisher);
            return publisher;
        });

        // 创建 Flux 数据流并使用 map 操作符
        Flux.just(1, 2, 3)
                .map(i -> i * 2)
                .subscribe(
                        data -> System.out.println("Received: " + data),
                        err -> System.err.println("Error: " + err),
                        () -> System.out.println("Completed")
                );

        // 根据 key 重置该钩子（可选）
        Hooks.resetOnEachOperator("logHook");
    }

}

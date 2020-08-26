package com.reactive.app;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import lombok.SneakyThrows;

/**
 * @author shenxie
 * @date 2020/8/26
 */
public class OrderObserver implements Observer<OrderInfo> {
    @Override
    public void onSubscribe(@NonNull Disposable d) {
        System.out.println("触发了onSubscribe");
    }

    @SneakyThrows
    @Override
    public void onNext(@NonNull OrderInfo info) {
        System.out.println(String.format("触发了onNext , o = %s" , info));
    }

    @Override
    public void onError(@NonNull Throwable e) {
        System.out.println(String.format("触发了onError , e = %s" , e));
    }

    @Override
    public void onComplete() {
        System.out.println("触发了onComplete");
    }
}

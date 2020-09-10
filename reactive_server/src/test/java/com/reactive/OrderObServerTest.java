package com.reactive;

import com.reactive.app.OrderInfo;
import com.reactive.app.OrderObserver;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author shenxie
 * @date 2020/8/26
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {OrderObServerTest.class})
public class OrderObServerTest {

    @Test
    public void onSubscribe(){
        Observable.just(OrderInfo.builder().orderNo("12345678").detail("这是一个订单").build())
                .observeOn(Schedulers.io())
                .subscribe(new OrderObserver());
    }
}


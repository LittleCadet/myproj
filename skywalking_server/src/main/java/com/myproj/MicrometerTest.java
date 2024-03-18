package com.myproj;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.composite.CompositeMeterRegistry;
import io.micrometer.core.instrument.simple.SimpleMeterRegistry;

/**
 * @author shenxie
 * @date 2024/2/28
 */
public class MicrometerTest {

    public static void main(String[] args) {
        showInfo();
    }

    private static void showInfo(){
        CompositeMeterRegistry composite = new CompositeMeterRegistry();

        Counter compositeCounter = composite.counter("counter");
        compositeCounter.increment();

        SimpleMeterRegistry simple = new SimpleMeterRegistry();
        composite.add(simple);

        compositeCounter.increment();

        simple.counter("database.calls", "db", "users");
        simple.counter("calls","class", "database","db", "users");


    }
}

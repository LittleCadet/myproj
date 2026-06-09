package com.metric;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.Tag;
import io.micrometer.core.instrument.Tags;
import io.micrometer.core.instrument.binder.jvm.*;
import io.micrometer.core.instrument.composite.CompositeMeterRegistry;
import io.micrometer.core.instrument.simple.SimpleMeterRegistry;

import java.util.ArrayList;
import java.util.concurrent.Executors;

/**
 * @author shenxie
 **/
public class MetricTest {
    public static void main(String[] args) {
//        ArrayList<Tag> tags = new ArrayList<>();
//        tags.add(new Tag() {
//            @Override
//            public String getKey() {
//                return "key";
//            }
//
//            @Override
//            public String getValue() {
//                return "value";
//            }
//        });
//        CustomRegistry customRegistry = new CustomRegistry(config -> null);
//        new JvmGcMetrics().bindTo(customRegistry);
//        new JvmCompilationMetrics().bindTo(customRegistry);
//        new JvmInfoMetrics().bindTo(customRegistry);
//        new JvmMemoryMetrics().bindTo(customRegistry);
//        new JvmThreadMetrics().bindTo(customRegistry);
//        new JvmHeapPressureMetrics().bindTo(customRegistry);
//        new ClassLoaderMetrics().bindTo(customRegistry);
//        new ExecutorServiceMetrics(Executors.newSingleThreadExecutor(), "metric-excutor", tags).bindTo(customRegistry);

        CompositeMeterRegistry composite = new CompositeMeterRegistry();

        SimpleMeterRegistry simple = new SimpleMeterRegistry();
        composite.add(simple);
        Counter compositeCounter = composite.counter("counter");
        compositeCounter.increment();

        System.out.println(compositeCounter.getId());


        compositeCounter.increment();
        System.out.println(compositeCounter.getId());

    }
}

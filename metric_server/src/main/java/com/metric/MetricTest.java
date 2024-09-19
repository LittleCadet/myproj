package com.metric;

import io.micrometer.core.instrument.Tag;
import io.micrometer.core.instrument.Tags;
import io.micrometer.core.instrument.binder.jvm.*;
import sun.management.snmp.jvminstr.JvmClassLoadingImpl;

import java.util.ArrayList;
import java.util.concurrent.Executors;

/**
 * @author shenxie
 **/
public class MetricTest {
    public static void main(String[] args) {
        ArrayList<Tag> tags = new ArrayList<>();
        tags.add(new Tag() {
            @Override
            public String getKey() {
                return "key";
            }

            @Override
            public String getValue() {
                return "value";
            }
        });
        CustomRegistry customRegistry = new CustomRegistry(config -> null);
        new JvmGcMetrics().bindTo(customRegistry);
        new JvmCompilationMetrics().bindTo(customRegistry);
        new JvmInfoMetrics().bindTo(customRegistry);
        new JvmMemoryMetrics().bindTo(customRegistry);
        new JvmThreadMetrics().bindTo(customRegistry);
        new JvmHeapPressureMetrics().bindTo(customRegistry);
        new ClassLoaderMetrics().bindTo(customRegistry);
        new ExecutorServiceMetrics(Executors.newSingleThreadExecutor(), "metric-excutor", tags).bindTo(customRegistry);

    }
}

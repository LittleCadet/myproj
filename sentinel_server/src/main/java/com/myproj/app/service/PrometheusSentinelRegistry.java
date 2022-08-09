package com.myproj.app.service;

import io.prometheus.client.CollectorRegistry;
import io.prometheus.client.Counter;
import io.prometheus.client.Gauge;
import io.prometheus.client.Histogram;
import org.springframework.stereotype.Service;

@Service
public class PrometheusSentinelRegistry {

    private static Counter passRequests;
    private static Counter blockRequests;
    private static Counter successRequests;
    private static Counter exceptionRequests;
    private static Histogram rtHist;
    private static Gauge currentThreads;

    public PrometheusSentinelRegistry(CollectorRegistry registry) {
        passRequests = Counter.build()
                .name("sentinel_pass_requests_total")
                .help("total pass requests.")
                .labelNames("resource")
                .register(registry);
        blockRequests = Counter.build()
                .name("sentinel_block_requests_total")
                .help("total block requests.")
                .labelNames("resource", "type", "ruleLimitApp", "limitApp")
                .register(registry);
        successRequests = Counter.build()
                .name("sentinel_success_requests_total")
                .help("total success requests.")
                .labelNames("resource")
                .register(registry);
        exceptionRequests = Counter.build()
                .name("sentinel_exception_requests_total")
                .help("total exception requests.")
                .labelNames("resource")
                .register(registry);
        currentThreads = Gauge.build()
                .name("sentinel_current_threads")
                .help("current thread count.")
                .labelNames("resource")
                .register(registry);
        rtHist = Histogram.build()
                .name("sentinel_requests_latency_seconds")
                .help("request latency in seconds.")
                .labelNames("resource")
                .register(registry);
    }

    public static Counter getPassRequests() {
        return passRequests;
    }

    public static Counter getBlockRequests() {
        return blockRequests;
    }

    public static Counter getSuccessRequests() {
        return successRequests;
    }

    public static Counter getExceptionRequests() {
        return exceptionRequests;
    }

    public static Histogram getRtHist() {
        return rtHist;
    }

    public static Gauge getCurrentThreads() {
        return currentThreads;
    }
}

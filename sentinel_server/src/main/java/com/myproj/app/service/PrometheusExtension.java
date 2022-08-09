package com.myproj.app.service;

import com.alibaba.csp.sentinel.metric.extension.MetricExtension;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

/**
 * 使用sentinel的SPI: com.alibaba.csp.sentinel.metric.extension.MetricExtension 来实现 metrics的接口扩展。
 *
 * @author shenxie
 * @date 2022/8/9
 */
public class PrometheusExtension implements MetricExtension {

    private PrometheusSentinelRegistry prometheusSenRegistry;

    private ApplicationContext applicationContext;

    public PrometheusExtension() {
    }

    public PrometheusExtension(PrometheusSentinelRegistry prometheusSenRegistry) {
        this.prometheusSenRegistry = prometheusSenRegistry;
    }

    @Override
    public void addPass(String resource, int n, Object... args) {
        PrometheusSentinelRegistry.getPassRequests().labels(resource).inc();
    }

    @Override
    public void addBlock(String resource, int n, String origin, BlockException blockException, Object... args) {
        PrometheusSentinelRegistry.getBlockRequests().labels(resource, blockException.getClass().getSimpleName(), blockException.getRuleLimitApp(), origin).inc(n);
    }

    @Override
    public void addSuccess(String resource, int n, Object... args) {
        PrometheusSentinelRegistry.getSuccessRequests().labels(resource).inc(n);
    }

    @Override
    public void addException(String resource, int n, Throwable throwable) {
        PrometheusSentinelRegistry.getExceptionRequests().labels(resource).inc(n);
    }

    @Override
    public void addRt(String resource, long rt, Object... args) {
        PrometheusSentinelRegistry.getRtHist().labels(resource).observe(((double) rt) / 1000);
    }

    @Override
    public void increaseThreadNum(String resource, Object... args) {
        PrometheusSentinelRegistry.getCurrentThreads().labels(resource).inc();
    }

    @Override
    public void decreaseThreadNum(String resource, Object... args) {
        PrometheusSentinelRegistry.getCurrentThreads().labels(resource).dec();
    }

}

package com.myproj.app.service;

import com.alibaba.csp.sentinel.metric.extension.MetricExtension;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.context.ApplicationContext;

/**
 * 使用sentinel的SPI: com.alibaba.csp.sentinel.metric.extension.MetricExtension 来实现 metrics的接口扩展。
 *
 * sentinel的数据 推送到 prometheus ： 方式二： 使用sentinel的SPI 来实现。
 *
 * 优点： 更加优雅
 *
 * @author shenxie
 * @date 2022/8/9
 */
public class PrometheusExtension implements MetricExtension {

    private ApplicationContext applicationContext;

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

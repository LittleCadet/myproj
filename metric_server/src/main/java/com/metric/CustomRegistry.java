package com.metric;

import io.micrometer.core.instrument.Clock;
import io.micrometer.prometheusmetrics.PrometheusConfig;
import io.micrometer.prometheusmetrics.PrometheusMeterRegistry;
import io.prometheus.metrics.model.registry.PrometheusRegistry;
import io.prometheus.metrics.tracer.common.SpanContext;

/**
 * @author shenxie
 **/
public class CustomRegistry extends PrometheusMeterRegistry {
    public CustomRegistry(PrometheusConfig config) {
        super(config);
    }

    public CustomRegistry(PrometheusConfig config, PrometheusRegistry registry, Clock clock) {
        super(config, registry, clock);
    }

    public CustomRegistry(PrometheusConfig config, PrometheusRegistry registry, Clock clock, SpanContext spanContext) {
        super(config, registry, clock, spanContext);
    }
}

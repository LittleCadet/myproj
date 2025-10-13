package com.metric;

import io.micrometer.core.instrument.Clock;
import io.micrometer.core.instrument.Meter;
import io.micrometer.core.instrument.Tag;
import io.micrometer.core.instrument.config.MeterFilter;
import io.micrometer.core.instrument.config.MeterFilterReply;
import io.micrometer.prometheusmetrics.PrometheusConfig;
import io.micrometer.prometheusmetrics.PrometheusMeterRegistry;
import io.prometheus.metrics.model.registry.PrometheusRegistry;
import io.prometheus.metrics.tracer.common.SpanContext;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import org.springframework.util.CollectionUtils;

/**
 * @author shenxie
 **/
public class CustomRegistry extends PrometheusMeterRegistry {
    AtomicInteger counter = new AtomicInteger(0);

    List<Tag> tags = new ArrayList<>();
    {
        tags.add(Tag.of("normal-tag1", "1"));
        tags.add(Tag.of("normal-tag2", "2"));
    }

    public CustomRegistry(PrometheusConfig config) {
        super(config);

        config().meterFilter(new MeterFilter() {
            @Override
            public MeterFilterReply accept(Meter.Id id) {
                if(id.getName().equals("test.counter")){
                    return MeterFilterReply.DENY;
                }
                return MeterFilterReply.ACCEPT;
            }
        })
                .meterFilter(new MeterFilter() {
                    @Override
                    public Meter.Id map(Meter.Id id) {
                        List<Tag> tags = id.getTags().stream().filter(tag -> ! tag.getKey().contains("c")).collect(Collectors.toList());
                        return id.replaceTags(tags);
                    }
                })
                .meterFilter(new MeterFilter() {
                    @Override
                    public MeterFilterReply accept(Meter.Id id) {
                        if(counter.incrementAndGet() > 4) {
                            return MeterFilterReply.DENY;
                        }
                        return MeterFilterReply.ACCEPT;
                    }
                })
//                .meterFilter(MeterFilter.denyNameStartsWith("test_timer"))
                .meterFilter(MeterFilter.commonTags(tags))
                .meterFilter(MeterFilter.maximumAllowableMetrics(1));
    }



}

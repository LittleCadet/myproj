package com.metric;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.Timer;
import io.micrometer.prometheusmetrics.PrometheusConfig;
import io.micrometer.prometheusmetrics.PrometheusMeterRegistry;
import java.time.Duration;
import java.util.Random;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author shenxie
 * @date 2025/9/24
 */
@RequestMapping("demo")
@RestController
public class Controller {

    Counter counter = Counter.builder("test.counter")
            .tag("a", "1")
            .tag("b", "2")
            .description("这是一个test")
//            .register(HTTPServer.prometheusRegistry);
            .register(HTTPServer.customRegistry);

    Timer timer = Timer.builder("test.timer")
            .tag("a", "1")
            .tag("b", "2")
            .tag("c","3")
            .description("这是一个test")
//            .register(HTTPServer.prometheusRegistry);
            .register(HTTPServer.customRegistry);

    Gauge gauge = Gauge.builder("test.guage", this::get)
            .tag("a", "1")
            .tag("b", "2")
            .description("这是一个test")
//            .register(HTTPServer.prometheusRegistry);
            .register(HTTPServer.customRegistry);

    /**
     * 该写法：可以， 但是不够优雅， 因为代表 同一个指标名的指标， 在每次执行方法调用时， 都会走构建 + 注册 这两步。
     * <p>
     * 应该将指标的构建+注册 与 使用分隔开。
     */
    @GetMapping("metric")
    public String metric() {
        Counter.builder("test.counter")
                .tag("a", "1")
                .tag("b", "2")
                .description("这是一个test")
//                .register(HTTPServer.prometheusRegistry)
                .register(HTTPServer.customRegistry)
                .increment();

        Timer.builder("test.timer")
                .tag("a", "1")
                .tag("b", "2")
                .description("这是一个test")
//                .register(HTTPServer.prometheusRegistry)
                .register(HTTPServer.customRegistry)
                .record(Duration.ofMillis(new Random().nextInt(100)));

        Gauge.builder("test.guage", this::get)
                .tag("a", "1")
                .tag("b", "2")
                .description("这是一个test")
//                .register(HTTPServer.prometheusRegistry);
                .register(HTTPServer.customRegistry);

        return "OK";
    }

    @GetMapping("metric/v2")
    public String metricV2() {
        counter.increment();

        timer.record(Duration.ofMillis(new Random().nextInt(100)));

        return "OK";
    }

    private Integer get() {
        return new Random().nextInt(100);
    }
}

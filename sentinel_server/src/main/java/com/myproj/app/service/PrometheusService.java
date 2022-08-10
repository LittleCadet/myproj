package com.myproj.app.service;

import io.prometheus.client.Counter;
import io.prometheus.client.exporter.PushGateway;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * sentinel的数据： 推送到prometheus的方式一：  手动实现PushGateway 并 推送。
 *
 * 缺点： 并不能优雅的解决问题。
 *
 * @author shenxie
 * @date 2022/8/8
 */
@Slf4j
@Service
public class PrometheusService {

    private static final Counter REQUEST = Counter.build().name("request_total").help("Total requests").register();

    private static final PushGateway PUSH_GATEWAY = new PushGateway("121.43.160.21:31091");

    public void processRequest() throws IOException {
        REQUEST.inc();

        PUSH_GATEWAY.push(REQUEST, "count");
    }
}

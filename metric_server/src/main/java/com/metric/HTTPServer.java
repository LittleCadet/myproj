package com.metric;

import com.sun.net.httpserver.HttpServer;
import io.micrometer.prometheusmetrics.PrometheusConfig;
import io.micrometer.prometheusmetrics.PrometheusMeterRegistry;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

/**
 * @author shenxie
 * @date 2025/9/24
 */
public class HTTPServer {

    public static PrometheusMeterRegistry prometheusRegistry = new PrometheusMeterRegistry(PrometheusConfig.DEFAULT);

    public static CustomRegistry customRegistry = new CustomRegistry(PrometheusConfig.DEFAULT);

    public void Start() {


        try {
            HttpServer server = HttpServer.create(new InetSocketAddress(8090), 0);
            server.createContext("/prometheus", httpExchange -> {
//                String response = prometheusRegistry.scrape();
                String response = customRegistry.scrape();
                httpExchange.sendResponseHeaders(200, response.getBytes().length);
                try (OutputStream os = httpExchange.getResponseBody()) {
                    os.write(response.getBytes());
                }
            });

            new Thread(server::start).start();
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

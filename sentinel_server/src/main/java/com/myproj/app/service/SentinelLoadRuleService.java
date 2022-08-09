package com.myproj.app.service;

import com.alibaba.cloud.nacos.NacosConfigManager;
import com.alibaba.csp.sentinel.datasource.ReadableDataSource;
import com.alibaba.csp.sentinel.datasource.nacos.NacosDataSource;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.nacos.api.config.listener.Listener;
import com.alibaba.nacos.api.exception.NacosException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.Executor;

/**
 * @author shenxie
 * @date 2022/8/8
 */
@Service
@Slf4j
public class SentinelLoadRuleService implements InitializingBean {

    @Value("${spring.cloud.nacos.config.server-addr}")
    private String remoteAddress;

    private final String dataId = "sentinel.rule";

    private final String groupId = "sentinel";
    private final NacosConfigManager nacosConfigManager;

    public SentinelLoadRuleService(NacosConfigManager nacosConfigManager) {
        this.nacosConfigManager = nacosConfigManager;
    }

    private void registerProperty() {
        ReadableDataSource<String, List<FlowRule>> flowRuleDataSource = new NacosDataSource<List<FlowRule>>(remoteAddress, groupId, dataId,
                source -> JSON.parseObject(source, new TypeReference<List<FlowRule>>() {
                }));
        FlowRuleManager.register2Property(flowRuleDataSource.getProperty());
        log.info("====nacos: sentinel:rules:{}" , FlowRuleManager.getRules());
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        new Thread(() -> {
            try {
                log.info("====nacos 监听开始");
                registerProperty();
                nacosConfigManager.getConfigService().getConfigAndSignListener(dataId, groupId, 1000, new Listener() {
                    @Override
                    public Executor getExecutor() {
                        return null;
                    }

                    @Override
                    public void receiveConfigInfo(String configInfo) {
                        registerProperty();
                    }
                });
            } catch (NacosException e) {
                throw new RuntimeException(e);
            }
        }).start();
    }
}

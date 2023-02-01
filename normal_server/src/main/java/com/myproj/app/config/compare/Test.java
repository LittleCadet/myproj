package com.myproj.app.config.compare;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author shenxie
 * @date 2022/11/3
 */
public class Test {

    @SneakyThrows
    public static void main(String[] args) {
        String sourceGateway = ".cps";
        String toGateway = ".admin";
        processConfig(sourceGateway, toGateway);
    }

    /**
     * 配置合并： eg: cps-gateway 合并到 admin-gateway中
     * @param sourceGateway  cps-gateway
     * @param toGateway admin-gateway
     */
    @SneakyThrows
    public static void processConfig(String sourceGateway, String toGateway) {
        String content = "";
        List<String> configs1 = Lists.newArrayList();
        Map<String, String> configMap1 = Maps.newHashMap();

        List<String> configs2 = Lists.newArrayList();

        HashMap<Object, Object> finalConfig = Maps.newLinkedHashMap();

        BufferedReader reader1 = new BufferedReader(
                new InputStreamReader(
                        Files.newInputStream(
                                new File("/Users/littlecadet/workspace/self/myproj/normal_server/src/main/resources/admin-gateway.properties").toPath()), StandardCharsets.UTF_8));


        BufferedReader reader2 = new BufferedReader(
                new InputStreamReader(
                        Files.newInputStream(
                                new File("/Users/littlecadet/workspace/self/myproj/normal_server/src/main/resources/cps-gateway.properties").toPath()), StandardCharsets.UTF_8));

        while ((content = reader1.readLine()) != null) {
            if (StringUtils.isNotEmpty(content) && !(content.contains("demo") || content.contains("test"))) {
                content = content.replaceAll(" = ", "=");
                configs1.add(content);
                String[] split = content.split("=");
                configMap1.put(split[0], split[1]);
                if (split[0].startsWith("ewt360.gateway")) {
                    String gatewayKey = split[0].replace("ewt360.gateway.", "");
                    finalConfig.put(String.format("ewt360.gateway%s.%s", toGateway, gatewayKey), split[1]);
                }else{
                    finalConfig.put(split[0], split[1]);
                }
            }
        }

        while ((content = reader2.readLine()) != null) {
            if (StringUtils.isNotEmpty(content) && !(content.contains("demo") || content.contains("test"))) {
                content = content.replaceAll(" = ", "=");
                String[] split = content.split("=");
                if(split.length == 2) {
                    configs2.add(content);
                }else{
                    configs2.add(String.format("%s%s", content, ""));
                }
            }
        }


        for (String c2 : configs2) {
            if (configs1.stream().noneMatch(c1 -> c1.equals(c2))) {
                String[] split = c2.split("=");
                if (configMap1.keySet().stream().noneMatch(c1 -> c1.contains(split[0]))) {
//                    System.out.println("cps-gateway与admin-gateway不一样的配置：额外的key:" + c2);
//                    System.out.println(c2);
                    if (split[0].startsWith("ewt360.gateway")) {
                        String gatewayKey = split[0].replace("ewt360.gateway.", "");
                        finalConfig.put(String.format("ewt360.gateway%s.%s", sourceGateway, gatewayKey), split.length == 2? split[1] : "");
                    } else {
                        finalConfig.put(split[0], split[1]);
                    }
                    continue;
                }

//                System.out.println("cps-gateway与admin-gateway不一样的配置：key相同value不同:" + "key:" + split[0] + ", value:" + split[1]
//                        + "    【api-gateway:" + configMap1.get(split[0]) + "】");

                if (split[0].startsWith("ewt360.gateway")) {
                    String gatewayKey = split[0].replace("ewt360.gateway.", "");
                    finalConfig.put(String.format("ewt360.gateway%s.%s", sourceGateway, gatewayKey), split[1]);
                    finalConfig.put(String.format("ewt360.gateway%s.%s", toGateway, gatewayKey), configMap1.get(split[0]));
                } else {
                    finalConfig.put(split[0], configMap1.get(split[0]));
                }
            }
        }

        // 写入文件：
        if (finalConfig.size() > 0) {
            BufferedOutputStream out = new BufferedOutputStream(
                    Files.newOutputStream(Paths.get("/Users/littlecadet/workspace/self/myproj/normal_server/src/main/resources/final-gateway.properties")));

            try {
                finalConfig.forEach((k, v) -> {
                    try {
                        out.write(String.format("%s=%s", k, v).getBytes(StandardCharsets.UTF_8));
                        out.write('\n');
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
            } finally {
                try {
                    out.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        System.out.println("文件写入成功： final-gateway.properties");
    }

}

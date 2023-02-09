package com.myproj.app.config.compare;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.PageReadListener;
import com.alibaba.excel.read.listener.ReadListener;
import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.SneakyThrows;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

/**
 * @author shenxie
 * @date 2023/2/6
 */
public class ExcelProcess {

    @SneakyThrows
    public static void main(String[] args) {

        // k-v: 路由-请求地址
        HashMap<String, String> requests = Maps.newHashMap();

        String fileName ="/Users/littlecadet/Downloads/firefox/" + "cps-gateway-sample-request-uri.csv";


        // 读取excel
        EasyExcel.read(fileName, ExcelData.class, new PageReadListener<ExcelData>(dataList -> {
            for (ExcelData demoData : dataList) {
                int index = demoData.getRequestUri().replaceFirst("/api/", "").indexOf("/");
                if(index > 0) {
                    // 数据处理： 取出所有route信息 唯一的数据。
                    try {
                        requests.putIfAbsent(demoData.getRequestUri().substring(0, 4 + index + 1), demoData.getRequestUri());
                    }catch (StringIndexOutOfBoundsException e) {
                        System.out.printf("处理异常：demoData:" + demoData);
                    }
                }
            }
        })).sheet().doRead();


        // 写到txt文本中。
        if (requests.size() > 0) {
            BufferedOutputStream out = new BufferedOutputStream(
                    Files.newOutputStream(Paths.get("/Users/littlecadet/tools/jmeter/replay/replay_cps_gateway.txt")));

            try {
                requests.forEach((k, v) -> {
                    try {
                        out.write(v.getBytes(StandardCharsets.UTF_8));
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

        System.out.println("文件写入成功：/Users/littlecadet/tools/jmeter/replay/replay_cps_gateway.txt");
    }

//    @Data
//    public class ExcelData {
//        private String requestUri;
//    }

}

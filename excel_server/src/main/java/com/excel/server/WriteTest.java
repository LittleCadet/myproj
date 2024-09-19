package com.excel.server;

import com.alibaba.excel.EasyExcel;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * @author shenxie
 **/
public class WriteTest {
    public static void main(String[] args) {
        String outputPath = "C:\\Users\\sx255\\Desktop\\样表20240914 - 输出.xlsx";
        List<LinkedHashMap<Integer, String>> finalDatas = new LinkedList<>();
        for (int i = 0; i<10000; i++) {
            int index = 0 ;
            LinkedHashMap<Integer, String> params = new LinkedHashMap<>();

            params.put(index++, i +"");
            params.put(index++, "国城文华里建设工程工程总承包项目经理部" + i );
            params.put(index++, "施工承包");
            params.put(index++, "执行中");
            params.put(index++, i +"");
            params.put(index++, i +"");
            params.put(index++, i +"");
            params.put(index++, i +"");
            params.put(index++, i +"");
            finalDatas.add(params);
        }
        EasyExcel.write(outputPath, LinkedHashMap.class).sheet("模板").head(Head.class).doWrite(finalDatas);
        System.out.println("写入完成");
    }
}

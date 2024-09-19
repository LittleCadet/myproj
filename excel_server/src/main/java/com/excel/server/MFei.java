package com.excel.server;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.ReadListener;
import org.apache.commons.compress.utils.Lists;
import org.apache.commons.compress.utils.Sets;

import java.util.*;

public class MFei {
    public static void main(String[] args) {
        String filePath = "C:\\Users\\sx255\\Desktop\\样表20240914 - 副本.xlsx";
        String outputPath = "C:\\Users\\sx255\\Desktop\\样表20240914 - 输出.xlsx";
//        if(args.length != 2) {
//            System.out.println("参数不是2个, 请检查参数:" + Arrays.toString(args));
//            return;
//        }
//        String filePath = args[0];
//        String outputPath = args[1];
        // sheet页 => sheet页中的所有行记录
        List<List<LinkedHashMap<Integer, String>>> totalDatas = Lists.newArrayList();
        // 所有的sheet页中的项目名
        Set<String> totalProjects = Sets.newHashSet();
        List<LinkedHashMap<Integer, String>> finalDatas = new LinkedList<>();
        long start = System.currentTimeMillis();
        try {
            // 读取第一个 sheet 的数据，这里假设你的数据在第一个 sheet
            int sheetIndex = 0;
            // 自动读取所有sheet页。
            while (true) {
                List<LinkedHashMap<Integer, String>> datas = new LinkedList<>();
                EasyExcel.read(filePath)
                        .registerReadListener(new ReadListener<LinkedHashMap<Integer, String>>() {
                            @Override
                            public void invoke(LinkedHashMap<Integer, String> data, AnalysisContext context) {
                                // 从序号是数字的行开始记录
                                if (null != data.get(0) && isNumeric(data.get(0))) {
//                                    System.out.println(data);
                                    LinkedHashMap<Integer, String> rowData = new LinkedHashMap<>(data);
                                    int columns = rowData.size();
                                    // 只保留9列数据
                                    while (columns >= 9) {
                                        rowData.remove(columns--);
                                    }
                                    datas.add(rowData);
                                    totalProjects.add(rowData.get(1));
                                }
                            }

                            @Override
                            public void doAfterAllAnalysed(AnalysisContext context) {

                            }
                        })
                        .sheet(sheetIndex++)
                        .doRead();

                if (datas.isEmpty()) {
                    break;
                }
                totalDatas.add(datas);
                System.out.println(">>>>>>>>>>>>>>>第" + sheetIndex + "页");
            }

            System.out.println(">>>>>>>>>>>>>>数据读取完成");


            // 将第一个sheet页中的数据按照项目名 填充。
            final int[] count = {0};
            if (totalDatas.size() > 1) {
                totalProjects.forEach(project -> {
                    for (int i = 0; i < totalDatas.size(); i++) {
                        // 该项目在该年份中是否存在
                        final boolean[] isExistInYear = {false};
                        // 该项目在该年份中存在，则用该年份中的项目数据来填充finalDatas.
                        existInYear(isExistInYear, project, totalDatas, finalDatas, i);

                        // 该项目在该年份中不存在， 则用null来填充finalDatas.
                        nonExistInYear(isExistInYear, project, finalDatas);


                    }
                    System.out.println(">>>>>>数据处理[项目]：" + ++count[0] + "完成");
                });

            } else {
                System.out.println("只包含一个sheet页的数据， 不做处理");
            }

            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>数据处理完成");

        } catch (Exception e) {
            e.printStackTrace();
        }

        // 输出到一个全新的excel中。
        EasyExcel.write(outputPath, LinkedHashMap.class).sheet("模板").head(Head.class).doWrite(finalDatas);
        System.out.println("写入完成: cost:" + (System.currentTimeMillis() - start) / 1000 + "s");
    }

    public static boolean isNumeric(String str) {
        if (str == null) {
            return false;
        }
        return str.matches("-?\\d+(\\.\\d+)?");
    }

    private static void existInYear(boolean[] isExistInYear, String project, List<List<LinkedHashMap<Integer, String>>> totalDatas, List<LinkedHashMap<Integer, String>> finalDatas, int index ){
        totalDatas.get(index).parallelStream().filter(data -> data.containsValue(project)).forEach(data -> {
            // 该项目在finalDatas中是否存在
            final boolean[] flag = {false};
            isExistInYear[0] = true;
            finalDatas.parallelStream().filter(finalData -> finalData.containsValue(project)).forEach(finalData -> {
                // 将非第一页的序号 + 项目名： 放入finalData中。
                for (int j = 2; j < data.size(); j++) {
                    finalData.put(finalData.size(), data.get(j));
                }
                flag[0] = true;
            });
            if (!flag[0]) {
                finalDatas.add(new LinkedHashMap<>(data));
            }
        });
    }

    private static void nonExistInYear(boolean[] isExistInYear, String project, List<LinkedHashMap<Integer, String>> finalDatas){
        if( ! isExistInYear[0]) {
            final boolean[] flag = {false};
            finalDatas.parallelStream().filter(finalData -> finalData.containsValue(project)).forEach(finalData -> {
                flag[0] = true;
                // 将非第一页的序号 + 项目名： 放入finalData中。
                for (int j = 2; j < 9; j++) {
                    finalData.put(finalData.size(), null);
                }
            });
            if(! flag[0]){
                LinkedHashMap<Integer, String> nullData = new LinkedHashMap<>();
                for (int j = 0; j < 9; j++) {
                    if(j == 1) {
                        // 保存项目名
                        nullData.put(j, project);
                    }else{
                        nullData.put(j, null);
                    }
                }
                finalDatas.add(nullData);
            }
        }
    }
}
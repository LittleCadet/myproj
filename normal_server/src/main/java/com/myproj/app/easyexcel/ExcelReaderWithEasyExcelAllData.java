package com.myproj.app.easyexcel;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.ReadListener;
import org.apache.commons.compress.utils.Lists;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

public class ExcelReaderWithEasyExcelAllData {
    public static void main(String[] args) {
        String filePath = "C:\\Users\\sx255\\Desktop\\样表20240914 - 副本.xlsx";
        String outputPath = "C:\\Users\\sx255\\Desktop\\样表20240914 - 输出.xlsx";
        // sheet页 => sheet页中的所有行记录
        List<List<LinkedHashMap<Integer, String>>> totalDatas = Lists.newArrayList();
        List<LinkedHashMap<Integer, String>> finalDatas = new LinkedList<>();
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
            }


            // 将第一个sheet页中的数据按照项目名 填充。
            if (totalDatas.size() > 1) {
                totalDatas.get(0).forEach(sheet -> {
                    System.out.println(">>>>sheet:" +sheet);
                    sheet.forEach((k, v) -> {
                        // 根据项目名匹配
                        if (k == 1) {
                            for (int i = 0; i < totalDatas.size(); i++) {
                                totalDatas.get(i).stream().filter(data -> data.containsValue(v)).forEach(data -> {
                                    if (finalDatas.stream().anyMatch(da -> da.containsValue(v))) {
                                        finalDatas.stream().filter(finalData -> finalData.containsValue(v)).forEach(finalData -> {
                                            // 将非第一页的序号 + 项目名： 放入finalData中。
                                            for (int j = 2; j < data.size(); j++) {
                                                finalData.put(finalData.size(), data.get(j));
                                            }
                                        });
                                    } else {
                                        finalDatas.add(new LinkedHashMap<>(data));
                                    }
                                });
                            }
                        }
                    });
                });
            } else {
                System.out.println("只包含一个sheet页的数据， 不做处理");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        // 输出到一个全新的excel中。
        EasyExcel.write(outputPath, LinkedHashMap.class).sheet("模板").head(Head.class).doWrite(finalDatas);
        System.out.println("写入完成");
    }

    public static boolean isNumeric(String str) {
        if (str == null) {
            return false;
        }
        return str.matches("-?\\d+(\\.\\d+)?");
    }
}
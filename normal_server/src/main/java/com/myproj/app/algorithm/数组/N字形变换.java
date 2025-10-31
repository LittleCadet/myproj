package com.myproj.app.algorithm.数组;

import java.util.ArrayList;
import java.util.List;

/**
 * 将一个给定字符串 s 根据给定的行数 numRows ，以从上往下、从左到右进行 Z 字形排列。
 * 比如输入字符串为 "PAYPALISHIRING" 行数为 3 时，排列如下：
 * P   A   H   N
 * A P L S I I G
 * Y   I   R
 * 之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："PAHNAPLSIIGYIR"。
 * 请你实现这个将字符串进行指定行数变换的函数：
 * string convert(string s, int numRows);
 *
 * 示例 1：
 * 输入：s = "PAYPALISHIRING", numRows = 3
 * 输出："PAHNAPLSIIGYIR"
 *
 * 示例 2：
 * 输入：s = "PAYPALISHIRING", numRows = 4
 * 输出："PINALSIGYAHRPI"
 * 解释：
 * P     I    N
 * A   L S  I G
 * Y A   H R
 * P     I
 *
 * 示例 3：
 * 输入：s = "A", numRows = 1
 * 输出："A"
 *
 *      解法：
 *          - 用flag完成 “Z字形”变换， flag的起始值 是 -1.
 *
 * @author shenxie
 * @date 2025/10/31
 */
public class N字形变换 {

    public static void main(String[] args) {
        System.out.println(convert("PAYPALISHIRING", 3));
    }

    public static String convert(String s, int numRows) {
        if(numRows < 2) {
            return s;
        }

        // 为每一行准备一个StringBuilder
        List<StringBuilder> rows = new ArrayList<StringBuilder>();
        for(int i = 0; i < numRows; i++) {
            rows.add(new StringBuilder());
        }
        // i代表当前行数， flag:用来控制当前行数，实现 Z 字形变换
        // 注意： flag只能从-1开始。在第一行 和 最后一行实现flag的变更即可
        int i = 0, flag = -1;
        for(char c : s.toCharArray()) {
            rows.get(i).append(c);
            if(i == 0 || i == numRows -1) {
                flag = - flag;
            }
            i += flag;
        }

        // 拼接每一行的StringBuilder，即可
        StringBuilder res = new StringBuilder();
        for(StringBuilder row : rows) {
            res.append(row);
        }
        return res.toString();

    }
}

package com.myproj.app.algorithm.字符串;

import java.util.HashMap;
import java.util.Map;

/**
 * 题目：
 * 罗马数字包含以下七种字符： I， V， X， L，C，D 和 M。
 * 字符          数值
 * I             1
 * V             5
 * X             10
 * L             50
 * C             100
 * D             500
 * M             1000
 * 例如， 罗马数字 2 写做 II ，即为两个并列的 1。12 写做 XII ，即为 X + II 。 27 写做  XXVII, 即为 XX + V + II 。
 * 通常情况下，罗马数字中小的数字在大的数字的右边。但也存在特例，例如 4 不写做 IIII，而是 IV。数字 1 在数字 5 的左边，所表示的数等于大数 5 减小数 1 得到的数值 4 。同样地，数字 9 表示为 IX。这个特殊的规则只适用于以下六种情况：
 *     I 可以放在 V (5) 和 X (10) 的左边，来表示 4 和 9。
 *     X 可以放在 L (50) 和 C (100) 的左边，来表示 40 和 90。
 *     C 可以放在 D (500) 和 M (1000) 的左边，来表示 400 和 900。
 * 给你一个整数，将其转为罗马数字。
 *
 * 思路：
 * 1. 2个数组: 原因： 与《罗马数字转整数》不同的是： 该题不能用map, 因为map在循环的时候是无序的， 即为： 不能保证一定按照从大到小的顺序来。
 *      能保证顺序的简单数据结构： 数组 + list + 双向链表。
 * 2. 2个数组中：需要录入所有的情况，且两个数组的元素一一对应。
 * 3. 为了确保：num 远大于 value的情况， 所以需要while循环。且每循环一次， num累减一次， buffer拼接一次。
 *
 * @author shenxie
 * @date 2023/12/10
 */
public class 整数转罗马数字 {

    public static void main(String[] args) {
        System.out.println(intToRoman(999));
    }

    public static String intToRoman(int num) {
        int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] symbols = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

        StringBuffer buffer = new StringBuffer();
        for(int i=0; i< values.length; i++) {
            // 为了确保：num 远大于 value的情况， 所以需要while循环。
            while(num >= values[i]) {
                // 每循环一次， num累减一次， buffer拼接一次。
                num -= values[i];
                buffer.append(symbols[i]);
            }
        }

        return buffer.toString();
    }

}

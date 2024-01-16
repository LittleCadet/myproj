package com.myproj.app.algorithm.字符串;

import java.util.HashMap;
import java.util.Map;

/**
 * 题目：
 * 罗马数字包含以下七种字符: I， V， X， L，C，D 和 M。
 * 字符          数值
 * I             1
 * V             5
 * X             10
 * L             50
 * C             100
 * D             500
 * M             1000
 * 例如， 罗马数字 2 写做 II ，即为两个并列的 1 。12 写做 XII ，即为 X + II 。 27 写做  XXVII, 即为 XX + V + II 。
 * 通常情况下，罗马数字中小的数字在大的数字的右边。但也存在特例，例如 4 不写做 IIII，而是 IV。数字 1 在数字 5 的左边，
 * 所表示的数等于大数 5 减小数 1 得到的数值 4 。同样地，数字 9 表示为 IX。这个特殊的规则只适用于以下六种情况：
 *     I 可以放在 V (5) 和 X (10) 的左边，来表示 4 和 9。
 *     X 可以放在 L (50) 和 C (100) 的左边，来表示 40 和 90。
 *     C 可以放在 D (500) 和 M (1000) 的左边，来表示 400 和 900。
 * 给定一个罗马数字，将其转换成整数。
 * 示例 1:
 * 输入: s = "III"
 * 输出: 3
 * 示例 5:
 * 输入: s = "MCMXCIV"
 * 输出: 1994
 * 解释: M = 1000, CM = 900, XC = 90, IV = 4.
 *
 * 思路：
 * 1. map ： 录入所有罗马数组对应的值。
 * 2. 罗马数字中小的数字在大的数字的右边， 代表： 累加。
 * 3. 罗马数字中小的数字在大的数字的左边， 代表： 累减。
 * 4. 综合第2，3点，所以： 左边字符 比 右边字符 大时， 累加， 反之累减
 *
 *
 * @author shenxie
 * @date 2023/12/10
 */
public class 罗马数字转整数 {

    public int romanToInt(String s) {
        int sum = 0;
        Map<Character, Integer> map = new HashMap<>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);
        char[] strs = s.toCharArray();
        for(int i=0; i<strs.length; i++) {
            int value = map.get(strs[i]);
            // 左边字符 比 右边字符 大时， 累加， 反之累减
            if(i + 1 <= strs.length - 1 && value < map.get(strs[i + 1])) {
                sum -= value;
            }else{
                sum += value;
            }
        }
        return sum;
    }
}

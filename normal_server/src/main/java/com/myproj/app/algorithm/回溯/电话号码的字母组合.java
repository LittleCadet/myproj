package com.myproj.app.algorithm.回溯;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 题目：
 * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。答案可以按 任意顺序 返回。
 * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
 * 示例 1：
 * 输入：digits = "23"
 * 输出：["ad","ae","af","bd","be","bf","cd","ce","cf"]
 *
 * 思路：
 * 1. 回溯法： 即为dfs 【深度优先算法】
 *      核心难点：
 *          1.1 如何在一个循环体内： 执行 对应电话号码内容的 循环。
 *              解法： 控制给定数字digits的字符索引。
 *          1.2 字符的拼接： StringBuffer.append().
 *          1.3 在已有一个完成的拼接字符串后， 如何执行下一个字符的拼接。
 *              解法： dfs执行完成后， 执行StringBuffer.deleteCharAt(index)， 其中index为当前电话号码的下标 即可。
 *          1.4 字符拼接完成的标志：
 *              解法： 当给定数字digits的下标 == 数字的长度时， 一个字符串拼接完成。
 *          1.5 何时执行回溯算法？
 *              解法： 在1.2 和 1.3之间。
 *
 *
 *
 * @author shenxie
 * @date 2023/12/10
 */
public class 电话号码的字母组合 {

    public static void main(String[] args) {
        System.out.println(letterCombinations("23"));
    }

    public static List<String> letterCombinations(String digits) {
        List<String> combinations = new ArrayList<String>();
        if (digits.length() == 0) {
            return combinations;
        }
        Map<Character, String> phoneMap = new HashMap<Character, String>() {{
            put('2', "abc");
            put('3', "def");
            put('4', "ghi");
            put('5', "jkl");
            put('6', "mno");
            put('7', "pqrs");
            put('8', "tuv");
            put('9', "wxyz");
        }};
        backtrack(combinations, phoneMap, digits, 0, new StringBuffer());
        return combinations;
    }

    public static void backtrack(List<String> combinations, Map<Character, String> phoneMap, String digits, int index, StringBuffer combination) {
        // 当index满足指定数字的长度后， 将组合好的字符串放入combinations中。
        if (index == digits.length()) {
            combinations.add(combination.toString());
        } else {
            char digit = digits.charAt(index);
            String letters = phoneMap.get(digit);
            int lettersCount = letters.length();
            for (int i = 0; i < lettersCount; i++) {
                // 在combination中放入字符的组合。
                combination.append(letters.charAt(i));
                // 通过index + 1 控制： 当前要循环哪个 电话号码的内容。
                backtrack(combinations, phoneMap, digits, index + 1, combination);
                // 为了让放入combination的首个字符 重新组合。
                combination.deleteCharAt(index);
            }
        }
    }
}

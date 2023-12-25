package com.myproj.app.algorithm.字符串;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 题目：
 * 给你一个字符串 s ，请你反转字符串中 单词 的顺序。
 * 单词 是由非空格字符组成的字符串。s 中使用至少一个空格将字符串中的 单词 分隔开。
 * 返回 单词 顺序颠倒且 单词 之间用单个空格连接的结果字符串。
 * 注意：输入字符串 s中可能会存在前导空格、尾随空格或者单词间的多个空格。返回的结果字符串中，单词间应当仅用单个空格分隔，且不包含任何额外的空格。
 * 示例 1：
 * 输入：s = "the sky is blue"
 * 输出："blue is sky the"
 * 示例 2：
 * 输入：s = "  hello world  "
 * 输出："world hello"
 * 解释：反转后的字符串中不能存在前导空格和尾随空格。
 *
 * 思路：
 *      反转列表： 用Collections.reverse();
 *      列表中添加分隔符，变为String:  String.join(" ", list);
 *
 * @author shenxie
 * @date 2023/12/25
 */
public class 反转字符串中的单词 {

    public static void main(String[] args) {
        System.out.println(reverseWords("  hello world  "));
    }

    public static String reverseWords(String s) {
        s = s.trim();
        List<String> strs = Arrays.asList(s.split("\\s+"));
        Collections.reverse(strs);
        return String.join(" ", strs);
    }
}

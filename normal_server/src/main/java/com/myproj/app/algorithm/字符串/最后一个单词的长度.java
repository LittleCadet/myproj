package com.myproj.app.algorithm.字符串;

/**
 * 题目：
 * 给你一个字符串 s，由若干单词组成，单词前后用一些空格字符隔开。返回字符串中 最后一个 单词的长度。
 * 单词 是指仅由字母组成、不包含任何空格字符的最大子字符串。
 *
 * 示例 1：
 * 输入：s = "Hello World"
 * 输出：5
 * 解释：最后一个单词是“World”，长度为5。
 *
 * 思路：
 *      1. 难点： 在于如何将字符串按照N个空格分割为数组。
 *              表达方式： s.split("\\s+");
 *
 * @author shenxie
 * @date 2023/12/26
 */
public class 最后一个单词的长度 {

    public static void main(String[] args) {
        System.out.println(lengthOfLastWord("   fly me   to   the moon  "));
    }

    public static int lengthOfLastWord(String s) {
        s = s.trim();
        String[] strs = s.split("\\s+");
        return strs[strs.length-1].length();
    }

}

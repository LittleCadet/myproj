package com.myproj.app.algorithm.动态规划.多维动态规划;

import com.myproj.app.algorithm.动态规划.多维动态规划.最长回文子串;

/**
 * 给定一个字符串 s ，请计算这个字符串中有多少个回文子字符串。
 * 具有不同开始位置或结束位置的子串，即使是由相同的字符组成，也会被视作不同的子串。
 *
 * 示例 1：
 * 输入：s = "abc"
 * 输出：3
 * 解释：三个回文子串: "a", "b", "c"
 *
 * 示例 2：
 * 输入：s = "aaa"
 * 输出：6
 * 解释：6个回文子串: "a", "a", "a", "aa", "aa", "aaa"
 *
 *      思路：
 *      1. 方法1：倒排  +  动态规划： 与 {@link 最长回文子串} 几乎相同
 *             核心思想： 首尾字符 一致，且首尾中间如果已经是回文字符串了(dp[i + 1][j - 1])， 那么该首尾字符串 是回文字符串。
 *                      s.charAt(i) == s.charAt(j) && (j - i <= 1 || dp[i + 1][j - 1])
 *
 * @author shenxie
 * @date 2025/11/3
 */
public class 回文子串 {

    public static void main(String[] args) {
        System.out.println(countSubstrings("aaa"));
    }

    public static int countSubstrings(String s) {
        boolean[][] dp = new boolean[s.length()][s.length()];
        int count = 0;
        // 如果正着排序： 会产生各种数组下标越界。
        // 所以只能倒着排序
        for (int i = s.length() - 1; i >= 0; i--) {
            for (int j = i; j < s.length(); j++) {
                // 原因： 字符串是回文字符串的条件： 正着 = 倒着。
                // j - i <=1的原因： 单个字符 是 回文字符串！！！！
                // 该解法通过首尾字符判定： 所以：要求首尾中间的符合动态规划即可。
                // 即为首尾中间如果已经是回文字符串了(dp[i + 1][j - 1])， 那么首尾字符 也一致， 则该首尾字符串 是回文字符串。
                // 举例：iaaaj: 如果i和j在该字符串中相等， 且 i+1【即为 第一个a的位置】 和 j-1 【最后一个a的位置】: 已经是回文字符串的话，
                //              那么该字符串就是回文字符串
                if (s.charAt(i) == s.charAt(j) && (j - i <= 1 || dp[i + 1][j - 1])) {
                    dp[i][j] = true;
                    count++;
                }
            }
        }
        return count;
    }
}

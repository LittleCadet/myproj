package com.myproj.app.algorithm.动态规划;

/**
 * 题目：
 * 给你一个字符串 s，找到 s 中最长的回文子串。
 * 如果字符串的反序与原始字符串相同，则该字符串称为回文字符串。
 * 示例 1：
 * 输入：s = "babad"
 * 输出："bab"
 * 解释："aba" 同样是符合题意的答案。
 *
 *
 * @author shenxie
 * @date 2023/12/17
 */
public class 最长回文子串 {
    public static void main(String[] args) {
        System.out.println(longestPalindrome("babad"));
    }

    public static String longestPalindrome(String s) {
        boolean[][] dp = new boolean[s.length()][s.length()];
        int res = 1;
        String resStr = s.charAt(0) + "";
        for (int i = s.length() - 1; i >= 0; i--) {
            for (int j = i; j < s.length(); j++) {
                if (s.charAt(i) == s.charAt(j) && (j - i <= 1 || dp[i + 1][j - 1])) {
                    dp[i][j] = true;
                    if (j - i + 1 > res) {
                        res = j - i + 1;
                        resStr = s.substring(i, j + 1);
                    }
                }
            }
        }
        return resStr;
    }
}

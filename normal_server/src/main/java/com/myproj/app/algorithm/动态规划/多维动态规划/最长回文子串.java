package com.myproj.app.algorithm.动态规划.多维动态规划;

/**
 * 题目：
 * 给你一个字符串 s，找到 s 中最长的回文子串。
 * 如果字符串的反序与原始字符串相同，则该字符串称为回文字符串。
 * 示例 1：
 * 输入：s = "babad"
 * 输出："bab"
 * 解释："aba" 同样是符合题意的答案。
 *
 * 思路：
 *      1. 方法1：倒排  +  动态规划：
 *             核心思想： 首尾字符 一致，且首尾中间如果已经是回文字符串了， 那么该首尾字符串 是回文字符串。
 *                      s.charAt(i) == s.charAt(j) && (j - i <= 1 || dp[i + 1][j - 1])
 *      2. 方法2：快慢双指针： 不行： 太慢。
 *
 * @author shenxie
 * @date 2023/12/17
 */
public class 最长回文子串 {
    public static void main(String[] args) {
        System.out.println(longestPalindrome("babad"));
//        System.out.println(longestPalindromeV2("cbbd"));
    }

    public static String longestPalindrome(String s) {
        boolean[][] dp = new boolean[s.length()][s.length()];
        int res = 1;
        String resStr = s.charAt(0) + "";
        // 如果正着排序： 会产生各种数组下标越界。
        // 所以只能倒着排序
        for (int i = s.length() - 1; i >= 0; i--) {
            for (int j = i; j < s.length(); j++) {
                // 原因： 字符串是回文字符串的条件： 正着 = 倒着。
                // j - i <=1的原因： 单个字符 是 回文字符串！！！！
                // 该解法通过首尾字符判定： 所以：要求首尾中间的符合动态规划即可。
                // 即为首尾中间如果已经是回文字符串了， 那么首尾字符 也一致， 则该首尾字符串 是回文字符串。
                // 举例：iaaaj: 如果i和j在该字符串中相等， 且 i+1【即为 第一个a的位置】 和 j-1 【最后一个a的位置】: 已经是回文字符串的话，
                //              那么该字符串就是回文字符串
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

    /**
     * 方法2： 快慢双指针法； 超出时间限制：
     *      核心思想： 右指针一直领先左指针， 右指针每次+1， 左指针都从头再来。
     */
    public static String longestPalindromeV2(String s) {
        if(s.length() == 1) {
            return s;
        }
        String ans = "";
        int length = 0;
        int l = 0;
        int r = 0;
        String tmp = "";
        StringBuffer buffer = null;
        while(r < s.length()){
            while(l <= r) {
                buffer = new StringBuffer();
                tmp = s.substring(l, r + 1);
                buffer.append(tmp);
                if( tmp.equals(buffer.reverse().toString())){
                    ans = ans.length() > tmp.length() ? ans : tmp;
                }
                l++;
            }
            r++;
            l = 0;
        }
        return ans;
    }

}

package com.myproj.app.algorithm_二刷.双指针;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给定两个字符串 s 和 p，找到 s 中所有 p 的异位词的子串，返回这些子串的起始索引。不考虑答案输出的顺序。
 *
 *
 *
 * 示例 1:
 *
 * 输入: s = "cbaebabacd", p = "abc"
 * 输出: [0,6]
 * 解释:
 * 起始索引等于 0 的子串是 "cba", 它是 "abc" 的异位词。
 * 起始索引等于 6 的子串是 "bac", 它是 "abc" 的异位词。
 *
 *  示例 2:
 *
 * 输入: s = "abab", p = "ab"
 * 输出: [0,1,2]
 * 解释:
 * 起始索引等于 0 的子串是 "ab", 它是 "ab" 的异位词。
 * 起始索引等于 1 的子串是 "ba", 它是 "ab" 的异位词。
 * 起始索引等于 2 的子串是 "ab", 它是 "ab" 的异位词。
 *
 *
 *  思路：
 *      1. 理解异位词：字符串变数组  => 数组排序 => 两数组相等： 则为异位词。
 *          - 快慢双指针【s.substring()】 + 数组排序 + 数组相等
 *          - 异位词的标准解法： 数组排序 + 数组相等
 *
 * @author shenxie
 **/
public class 找到字符串中所有字母异位词 {

    public static void main(String[] args) {
        System.out.println(findAnagramsCopy("cbaebabacd", "abc"));
    }


    public static List<Integer> findAnagramsCopy(String s, String p) {
        List<Integer> result = new ArrayList<>();
        // 排序
        char[] pArray = p.toCharArray();
        Arrays.sort(pArray);


        for(int left = 0 ; left < s.length(); left ++){
            // s的剩余长度 必须比 p 的大， 不然subString会数组下标越界。
            if(s.length() - left >= p.length()) {
                // 右指针：是left + p.length(), 而不是p.length();
                String tmp = s.substring(left, left + p.length());
                char[] tmpArray = tmp.toCharArray();
                // 异位词的标准解法： 排序 + 数组相等
                Arrays.sort(tmpArray);

                if(Arrays.equals(pArray, tmpArray)) {
                    result.add(left);
                }

            }
        }
        return result;
    }
}

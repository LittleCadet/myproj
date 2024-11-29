package com.myproj.app.algorithm_二刷.双指针;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给定两个字符串 s 和 p，找到 s 中所有 p 的
 * 异位词
 *  的子串，返回这些子串的起始索引。不考虑答案输出的顺序。
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
 *          - 双指针【s.substring()】 + 排序 + 数组相等
 *
 * @author shenxie
 **/
public class 找到字符串中所有字母异位词 {

    public static void main(String[] args) {
        System.out.println(findAnagrams("cbaebabacd", "abc"));
    }

    public static List<Integer> findAnagrams(String s, String p) {
        List<Integer> results = new ArrayList<>();
        int sl = s.length(), pl = p.length();
        if(pl > sl) {
            return results;
        }
        char[] pp = p.toCharArray();
        Arrays.sort(pp);
        for(int i = 0 ; i<= sl - pl; i++) {
            String tmp = s.substring(i, i + pl);
            char[] tt = tmp.toCharArray();
            // 异位词的标准解法： 排序 + 数组相等
            Arrays.sort(tt);
            if(Arrays.equals(pp, tt)){
                results.add(i);
            }
        }

        return results;
    }
}

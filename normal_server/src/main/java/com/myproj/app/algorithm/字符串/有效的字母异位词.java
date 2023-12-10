package com.myproj.app.algorithm.字符串;

import java.util.Arrays;

/**
 * 题目：
 * 给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。
 * 注意：若 s 和 t 中每个字符出现的次数都相同，则称 s 和 t 互为字母异位词。
 * 示例 1:
 * 输入: s = "anagram", t = "nagaram"
 * 输出: true
 *
 * 思路：
 * 1. 排序： 先将字符串打散成为char数组， 之后排序， 用数组的api： Arrays.equals(array1 , array2)即可
 *
 * @author shenxie
 * @date 2023/12/10
 */
public class 有效的字母异位词 {

    public static void main(String[] args) {
        System.out.println(isAnagram("anagram", "nagaram"));
    }

    public static boolean isAnagram(String s, String t) {
        // 排序
        char[] cs = s.toCharArray();
        char[] ct = t.toCharArray();

        Arrays.sort(cs);
        Arrays.sort(ct);
        return Arrays.equals(cs, ct);
    }
}

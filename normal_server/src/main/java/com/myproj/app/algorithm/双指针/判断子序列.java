package com.myproj.app.algorithm.双指针;

import com.myproj.app.algorithm.数组.颜色分类;

/**
 * 题目：
 * 给定字符串 s 和 t ，判断 s 是否为 t 的子序列。
 * 字符串的一个子序列是原始字符串删除一些（也可以不删除）字符而不改变剩余字符相对位置形成的新字符串。（例如，"ace"是"abcde"的一个子序列，而"aec"不是）。
 * 示例1：
 * 输入：s = "abc", t = "ahbgdc"
 * 输出：true
 *
 * 思路：
 *      1. 快慢双指针：【指的是双指针本身】
 *          {@link 判断子序列}与 {@link 找出字符串的第一个匹配项的下标}相似：
 *          - {@link 判断子序列}：字符串中的字符能匹配到就行， 不要求连续匹配
 *          - {@link 找出字符串的第一个匹配项的下标}： 字符串中字符 要能连续匹配。
 *
 *          快慢双指针：{@link 删除有序数组中的重复项} {@link 删除有序数组中的重复项II} {@link 判断子序列} {@link 找出字符串的第一个匹配项的下标}
 *              {@link 移除元素} {@link 长度最小的子数组}{@link 颜色分类}
 *          首尾双指针：{@link 三数之和} {@link 两数之和II输入有序数组} {@link 盛最多水的容器}
 *
 * @author shenxie
 * @date 2023/12/26
 */
public class 判断子序列 {

    public static void main(String[] args) {
        System.out.println(isSubsequenceCopy("axc", "ahbgdc"));
    }

    public static boolean isSubsequenceCopy(String s, String t) {
        if(s.length() == 0 ) {
            return true;
        }
        int left = 0 ;
        int right = 0 ;
        while(right < t.length()) {
            if(s.charAt(left) == t.charAt(right)) {
                left ++;
                if(left >= s.length()) {
                    break;
                }
            }
            right ++;
        }
        return left == s.length();
    }

}

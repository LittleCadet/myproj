package com.myproj.app.algorithm.双指针;

/**
 * 题目：
 * 给定字符串 s 和 t ，判断 s 是否为 t 的子序列。
 * 字符串的一个子序列是原始字符串删除一些（也可以不删除）字符而不改变剩余字符相对位置形成的新字符串。（例如，"ace"是"abcde"的一个子序列，而"aec"不是）。
 * 示例1：
 * 输入：s = "abc", t = "ahbgdc"
 * 输出：true
 *
 * 思路：
 *      1. 普通双指针：【指的是双指针本身】
 *          {@link 判断子序列}与 {@link 找出字符串的第一个匹配项的下标}相似：
 *          - {@link 判断子序列}：字符串中的字符能匹配到就行， 不要求连续匹配
 *          - {@link 找出字符串的第一个匹配项的下标}： 字符串中字符 要能连续匹配。
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

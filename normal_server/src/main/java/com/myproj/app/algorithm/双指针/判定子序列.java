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
 *
 * @author shenxie
 * @date 2023/12/26
 */
public class 判定子序列 {

    public static void main(String[] args) {
        System.out.println(isSubsequence("axc", "ahbgdc"));
    }

    public static boolean isSubsequence(String s, String t) {
        int sl = s.length();
        int tl = t.length();
        if(sl > tl) {
            return false;
        }
        if(sl == 0) {
            return true;
        }
        // 移动s的指针。
        int p1 = 0;
        // 移动t的指针
        int p2 = 0;
        while(p2 < tl) {
            // 只有在对应指针的字符相同的时候， 才会移动p1
            if(s.charAt(p1) == t.charAt(p2)) {
                p1 ++;

                if(p1 >= sl) {
                    break;
                }
            }
            p2 ++;
        }

        return p1 == sl;
    }
}

package com.myproj.app.algorithm.双指针;

/**
 * 题目：
 * 给你两个字符串 haystack 和 needle ，请你在 haystack 字符串中找出 needle 字符串的第一个匹配项的下标（下标从 0 开始）。如果 needle 不是 haystack 的一部分，则返回  -1 。
 * 示例 1：
 * 输入：haystack = "sadbutsad", needle = "sad"
 * 输出：0
 * 解释："sad" 在下标 0 和 6 处匹配。
 * 第一个匹配项的下标是 0 ，所以返回 0 。
 *
 * 思路：
 *      1. 方法一：string.indexOf();
 *      2. 方法二：普通双指针：
 *          注意：双指针： 分为：快慢指针 + 首尾双指针+ 普通双指针。
 *          本题使用：双指针本身来解答：
 *              2.1 p1指针：用于移动字符串1， p2指针： 用于移动字符串2；
 *
 * @author shenxie
 * @date 2023/12/26
 */
public class 找到字符串的第一个匹配项的下标 {

    public static void main(String[] args) {
//        System.out.println(strStr("sadbutsad", "sad"));
//        System.out.println(strStr("leetcode", "leeto"));
        System.out.println(strStrV2("leetcode", "leeto"));
    }

    public static int strStrV2(String haystack, String needle){
        return haystack.indexOf(needle);
    }

    /*
     * 双指针办法
     * p1遍历haystack, p2遍历needle
     * 只要haystack.charAt(p1) == needle.charAt(p2),
     *      1. 指针都往后移动
     * 不相等的时候:
     *      1. 只移动p1, p2赋值0
     *      2. p2如果移动过, 需要将p1回退： 即为 p1-p2 + 1, 防止漏掉字符,
     *      这个特殊处理可以对照测试用例haystack="mississippi", needle="issip" 来看
     */
    public static int strStr(String haystack, String needle) {
        int len1 = haystack.length();
        int len2 = needle.length();
        if (len2 > len1) {
            return -1;
        }
        int p1 = 0;
        int p2 = 0;

        while (p1 < len1) {
            if (haystack.charAt(p1) == needle.charAt(p2)) {
                p1++;
                p2++;
            } else {
                // 回退, 防止漏掉字符
                p1 = p1 - p2 + 1;
                p2 = 0;
            }

            if (p2 == len2) {
                break;
            }
        }

        return p2 == len2 ? p1 - p2 : -1;
    }
}

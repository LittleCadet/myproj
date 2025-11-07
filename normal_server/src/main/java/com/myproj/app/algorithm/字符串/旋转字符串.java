package com.myproj.app.algorithm.字符串;

/**
 * 给定两个字符串, s 和 goal。如果在若干次旋转操作之后，s 能变成 goal ，那么返回 true 。
 * s 的 旋转操作 就是将 s 最左边的字符移动到最右边。
 *     例如, 若 s = 'abcde'，在旋转一次之后结果就是'bcdea' 。
 *
 * 示例 1:
 * 输入: s = "abcde", goal = "cdeab"
 * 输出: true
 *
 * 示例 2:
 * 输入: s = "abcde", goal = "abced"
 * 输出: false
 *
 *      思路:
 *          - 注意：String.contains(str);
 *
 * @author shenxie
 * @date 2025/11/4
 */
public class 旋转字符串 {

    public static void main(String[] args) {
        System.out.println(rotateString("abcde", "cdeab"));
    }

    public static boolean rotateString(String s, String goal) {
        return s.length() == goal.length() && (goal + goal).contains(s);
    }
}

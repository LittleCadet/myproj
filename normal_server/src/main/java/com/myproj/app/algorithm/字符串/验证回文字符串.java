package com.myproj.app.algorithm.字符串;

/**
 * 题目：
 * 如果在将所有大写字符转换为小写字符、并移除所有非字母数字字符之后，短语正着读和反着读都一样。则可以认为该短语是一个 回文串 。
 * 字母和数字都属于字母数字字符。
 * 给你一个字符串 s，如果它是 回文串 ，返回 true ；否则，返回 false 。
 * 示例 1：
 * 输入: s = "A man, a plan, a canal: Panama"
 * 输出：true
 * 解释："amanaplanacanalpanama" 是回文串。
 *
 * 思路：
 *      1. 用stringBuffer来判定是否是回文字符串。 因为stringBuffer有stringBuffer.reverse();
 *      2. 字符判定是否是 数字、字母。:
 *          Character.isLetterOrDigit(ch)
 *      3. stringBuffer.reverse的用法： 要注意：
 *          StringBuffer reverse = new StringBuffer(buffer).reverse();
 *      4. 2个stringBuffer比较是否相同：
 *          StringBuffer比较是否相同， 用StringBuffer.toString()
 *
 * @author shenxie
 * @date 2023/12/26
 */
public class 验证回文字符串 {

    public static void main(String[] args) {
        System.out.println(isPalindrome("race a car"));
    }

    public static boolean isPalindrome(String s) {
        // 难点： 如何移除非字母数字的字符。
        s = s.trim().toLowerCase();
        StringBuffer buffer = new StringBuffer();
        for(int i = 0; i<s.length(); i++) {
            char ch = s.charAt(i);
            // 字符判定是否是 数字、字母。
            if(Character.isLetterOrDigit(ch)) {
                buffer.append(ch);
            }
        }
        // 注意： stringBuffer.reverse(), 会直接将stringBuffer本身修改。
        // 因为stringBuffer是final修饰的， 所以需要新创建一个stringBuffer对象。
        StringBuffer reverse = new StringBuffer(buffer).reverse();
        // 注意： StringBuffer比较是否相同， 用StringBuffer.toString();
        return buffer.toString().equals(reverse.toString());
    }
}

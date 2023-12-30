package com.myproj.app.algorithm.数学;

/**
 * 题目：
 * 给你一个整数 x ，如果 x 是一个回文整数，返回 true ；否则，返回 false 。
 * 回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。
 *     例如，121 是回文，而 123 不是。
 * 示例 1：
 * 输入：x = 121
 * 输出：true
 *
 * 思路：
 *      1. 方法1： 首尾双指针： 太麻烦。
 *      2. 方法2： 反转一半数字。
 *              while(x > reverseNumber){
     *             reverseNumber = reverseNumber * 10 + x % 10;
     *             x = x / 10;
     *         }
 *      3. 方法3【推荐】： 回文字符串的思想： 用StringBuffer求解。
 *
 *
 * @author shenxie
 * @date 2023/12/29
 */
public class 回文数 {

    public static void main(String[] args) {
//        System.out.println(isPalindrome(121));
        System.out.println(isPalindromeV2(121));
        System.out.println(isPalindromeV3(121));
    }

    /**
     * 方法3： 回文字符串的思想： 用StringBuffer 求解。
     */
    public static boolean isPalindromeV3(int x) {
        String str1 = String.valueOf(x);
        StringBuffer buffer = new StringBuffer();
        buffer.append(str1);
        return str1.equals(buffer.reverse().toString());
    }

    /**
     * 方法2： 反转一半数字法。
     */
    public static boolean isPalindromeV2(int x) {
        // 负数 || 10的倍数 但不是0
        // 直接返回false.
        if(x < 0 || (x % 10 == 0 && x != 0) ) {
            return false;
        }
        int reverseNumber = 0;
        while(x > reverseNumber){
            reverseNumber = reverseNumber * 10 + x % 10;
            x = x / 10;
        }
        // 如果数字个数是偶数时， 判定 x == reverseNumber;
        // 如果数字个数是奇数时， 去除原来中间的数： 即为x == reverseNumber / 10
        return x == reverseNumber || x == reverseNumber / 10;
    }

    /**
     * 方法1： 首尾双指针法：
     */
    public static boolean isPalindrome(int x) {
        if(x < 0) {
            return false;
        }

        String string = String.valueOf(x);
        int l = 0;
        int r = string.length() - 1;
        // 数字个数是偶数时， l == r时， 跳出。
        while(l != r ) {
            if(  string.charAt(l) != string.charAt(r)){
                return false;
            }

            l++;
            r--;
            // 数字个数是奇数时， 满足r + 1 ==l ， 则跳出。
            if( r + 1 == l) {
                break;
            }
        }
        return true;
    }
}

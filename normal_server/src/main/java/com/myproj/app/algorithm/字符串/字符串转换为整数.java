package com.myproj.app.algorithm.字符串;

import com.myproj.app.algorithm.数学.整数反转;

/**
 *请你来实现一个 myAtoi(string s) 函数，使其能将字符串转换成一个 32 位有符号整数。
 * 函数 myAtoi(string s) 的算法如下：
 *     空格：读入字符串并丢弃无用的前导空格（" "）
 *     符号：检查下一个字符（假设还未到字符末尾）为 '-' 还是 '+'。如果两者都不存在，则假定结果为正。
 *     转换：通过跳过前置零来读取该整数，直到遇到非数字字符或到达字符串的结尾。如果没有读取数字，则结果为0。
 *     舍入：如果整数数超过 32 位有符号整数范围 [−231,  231 − 1] ，需要截断这个整数，使其保持在这个范围内。具体来说，小于 −231 的整数应该被舍入为 −231 ，大于 231 − 1 的整数应该被舍入为 231 − 1 。
 * 返回整数作为最终结果。
 *
 * 示例 1：
 * 输入：s = "42"
 *
 * 输出：42
 * 解释：加粗的字符串为已经读入的字符，插入符号是当前读取的字符。
 * 带下划线线的字符是所读的内容，插入符号是当前读入位置。
 * 第 1 步："42"（当前没有读入字符，因为没有前导空格）
 * 第 2 步："42"（当前没有读入字符，因为这里不存在 '-' 或者 '+'）
 * 第 3 步："42"（读入 "42"）
 *
 *  示例 2：
 * 输入：s = " -042"
 * 输出：-42
 *
 * 解释：
 * 第 1 步："   -042"（读入前导空格，但忽视掉）
 * 第 2 步："   -042"（读入 '-' 字符，所以结果应该是负数）
 * 第 3 步："   -042"（读入 "042"，在结果中忽略前导零）
 *                ^
 * 示例 3：
 * 输入：s = "1337c0d3"
 * 输出：1337
 * 解释：
 * 第 1 步："1337c0d3"（当前没有读入字符，因为没有前导空格）
 * 第 2 步："1337c0d3"（当前没有读入字符，因为这里不存在 '-' 或者 '+'）
 * 第 3 步："1337c0d3"（读入 "1337"；由于下一个字符不是一个数字，所以读入停止）
 *
 * 示例 4：
 * 输入：s = "0-1"
 * 输出：0
 * 解释：
 * 第 1 步："0-1" (当前没有读入字符，因为没有前导空格)
 * 第 2 步："0-1" (当前没有读入字符，因为这里不存在 '-' 或者 '+')
 * 第 3 步："0-1" (读入 "0"；由于下一个字符不是一个数字，所以读入停止)
 *
 * 示例 5：
 * 输入：s = "words and 987"
 * 输出：0
 * 解释：
 * 读取在第一个非数字字符“w”处停止。
 *
 *
 *      思路：
 *          - 字符的数字拼接：
 *              num = num * 10 + Integer.parseInt(s.charAt(i) + "") * flag;
 *          - 前导0的处理方式：
 *              s.charAt(i) == '0' && num ==0
 *          - int超限的处理方式：
 *  *              - 本题：用 long 来比较大小
 *  *              - {@link 整数反转}: try-catch
 *              long num = 0 ;
 *              if(num > Integer.MAX_VALUE) {
 *                 return Integer.MAX_VALUE;
 *             }else if(num < Integer.MIN_VALUE) {
 *                 return Integer.MIN_VALUE;
 *             }
 *
 *
 * @author shenxie
 * @date 2025/11/10
 */
public class 字符串转换为整数 {

    public static void main(String[] args) {
        System.out.println(myAtoiV2("21474836460"));
    }

    public static int myAtoiV2(String s) {
        s= s.trim();
        int flag = 1;
        long num = 0;
        for(int i = 0 ; i<s.length(); i++) {
            if(i == 0) {
                if(s.charAt(i) == '-') {
                    flag = -1;
                    continue;
                }else if(s.charAt(i) == '+') {
                    continue;
                }
            }

            // 前导0 ， 则跳过
            if(s.charAt(i) == '0' && num ==0) {
                continue;
            }

            // 是数字再拼接， 不然跳出
            if(Character.isDigit(s.charAt(i)) ) {
                num = num * 10 + Integer.parseInt(s.charAt(i) + "") * flag;
            }else{
                break;
            }

            // Integer界限值判定的标准答案
            if(num > Integer.MAX_VALUE) {
                return Integer.MAX_VALUE;
            }else if(num < Integer.MIN_VALUE) {
                return Integer.MIN_VALUE;
            }
        }

        // long => int
        return (int) num;

    }
}

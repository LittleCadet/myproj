package com.myproj.app.algorithm.数学;

/**
 * 题目：
 * 给定一个由 整数 组成的 非空 数组所表示的非负整数，在该数的基础上加一。
 * 最高位数字存放在数组的首位， 数组中每个元素只存储单个数字。
 * 你可以假设除了整数 0 之外，这个整数不会以零开头。
 * 示例 1：
 * 输入：digits = [1,2,3]
 * 输出：[1,2,4]
 * 解释：输入数组表示数字 123。
 *
 * 思路：
 *      1. 方法1： 不行：
 *          核心思想： 数组 转换为 整数 => +1 => 字符串 => 将字符串重新演变为数组。
 *      2. 方法2：
 *          3种情况：
 *              2.1 数字中某一个元素 + 1后， 没有进位， 则直接返回。
 *              2.2 数字中某一个元素 + 1后， 进位了， 则继续倒序循环。
 *              2.3 特殊情况： 全是9， 则：新建数组： 除了首位是1， 其余全是0；
 * @author shenxie
 * @date 2023/12/29
 */
public class 加一 {

    public static void main(String[] args) {
        // 方法一：不行。
//        int[] ans = plusOne(new int[]{9,8,7,6,5,4,3,2,1,0});
        // 方法二： 可以
        int[] ans = plusOneV2(new int[]{9,8,7,6,5,4,3,2,1,0});
        int[] tmp = new int[]{9,9,9};
        tmp = new int[tmp.length + 1];
        tmp[0] = 1;
        System.out.println(ans);;
    }

    /**
     * 方法2：
     */
    public static int[] plusOneV2(int[] digits) {
        for(int i = digits.length-1 ; i>=0; i--) {
            // 题意： 每个元素都是单个数字的表达方式：
            digits[i] = ++digits[i] % 10;
            // 意味着当前元素+1后， 没有进位。
            if(digits[i] != 0) {
                return digits;
            }
        }
        // 全是9的情况： 意味着： 除了首位是1， 其余全是0；
        digits = new int[digits.length + 1];
        digits[0] = 1;
        return digits;
    }

    /**
     * 以下做法不行：方法1：
     * 以下方法核心思想： 将数组中各个数字 转换为一个整数， 之后 + 1， 再将整数 变更为String, 最后将String的各个字符 赋值给一个全新数组。
     * 不行的原因： tmp即使用long 、 int修饰， 也会有最大值， 一旦入参超过最大值， 就会失去精度。
     */
    public static int[] plusOne(int[] digits) {
        int length = digits.length;
        long tmp = 0;

        for(int i = 0; i< length ; i++) {
            tmp = tmp * 10 + digits[i];
        }
        String str = String.valueOf(++tmp);
        int[] nums = new int[str.length()];
        for(int i = 0; i<str.length(); i++) {
            nums[i] = Integer.parseInt(String.valueOf(str.charAt(i)));
        }

        return nums;
    }
}

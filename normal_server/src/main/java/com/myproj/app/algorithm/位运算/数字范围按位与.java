package com.myproj.app.algorithm.位运算;

/**
 * 题目：
 * 给你两个整数 left 和 right ，表示区间 [left, right] ，返回此区间内所有数字 按位与 的结果（包含 left 、right 端点）。
 * 示例 1：
 * 输入：left = 5, right = 7
 * 输出：4
 *
 * 思路：
 *      1. 没看懂： 硬记：Brian Kernighan 算法
 * @author shenxie
 * @date 2023/12/30
 */
public class 数字范围按位与 {

    public static void main(String[] args) {
        System.out.println(rangeBitwiseAnd(5,7));
    }

    public static int rangeBitwiseAnd(int left, int right) {
        while(left < right) {
            // 抹去最右边的 1
            right = right & (right - 1);
        }
        return right;
    }
}

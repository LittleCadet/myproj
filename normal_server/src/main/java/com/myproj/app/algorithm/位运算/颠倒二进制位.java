package com.myproj.app.algorithm.位运算;

/**
 * 题目：
 * 颠倒给定的 32 位无符号整数的二进制位。
 * 提示：
 *     请注意，在某些语言（如 Java）中，没有无符号整数类型。在这种情况下，输入和输出都将被指定为有符号整数类型，并且不应影响您的实现，因为无论整数是有符号的还是无符号的，其内部的二进制表示形式都是相同的。
 *     在 Java 中，编译器使用二进制补码记法来表示有符号整数。因此，在 示例 2 中，输入表示有符号整数 -3，输出表示有符号整数 -1073741825。
 * 示例 1：
 * 输入：n = 00000010100101000001111010011100
 * 输出：964176192 (00111001011110000010100101000000)
 * 解释：输入的二进制串 00000010100101000001111010011100 表示无符号整数 43261596，
 *      因此返回 964176192，其二进制表示形式为 00111001011110000010100101000000。
 *
 * 思路：
 *      1. Integer.reverse(n): 将二进制位颠倒, 之后重新形成一个整数。
 * @author shenxie
 * @date 2023/12/30
 */
public class 颠倒二进制位 {

    public static void main(String[] args) {
        // 正确解法：
        System.out.println(reverseBitsV1(43261596));
        // 错误解法：
//        System.out.println(reverseBitsV2(43261596));
    }

    /**
     * 题目要求：
     * 将输入的整数的二进制位颠倒， 形成的新的整数。
     * 所以这题通过： StringBuffer.reverse(n)不行。
     * @param n 输入一个整数
     * @return 返回一个整数
     */
    public static int reverseBitsV1(int n) {
        return Integer.reverse(n);
    }

    /**
     * 错误解法
     */
    public static int reverseBitsV2(int n) {
         String str = String.valueOf(n);
         StringBuffer buffer = new StringBuffer();
         buffer.append(str);
         str = buffer.reverse().toString();
         return Integer.parseInt(str);
    }


}

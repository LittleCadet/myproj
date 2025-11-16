package com.myproj.app.algorithm.数学;

import com.myproj.app.algorithm.字符串.字符串转换为整数;

/**
 * 给你一个 32 位的有符号整数 x ，返回将 x 中的数字部分反转后的结果。
 *
 * 如果反转后整数超过 32 位的有符号整数的范围 [−231,  231 − 1] ，就返回 0。
 * 假设环境不允许存储 64 位整数（有符号或无符号）。
 *
 * 示例 1：
 * 输入：x = 123
 * 输出：321
 *
 * 示例 2：
 * 输入：x = -123
 * 输出：-321
 *
 * 示例 3：
 * 输入：x = 120
 * 输出：21
 *
 * 示例 4：
 * 输入：x = 0
 * 输出：0
 *
 *      思路：
 *          - StringBuilder.reverse() + 异常捕获
 *          - int超限的处理方式：
 *              - 本题：try-catch
 *              - {@link 字符串转换为整数}: 用 long 来比较大小
 *
 * @author shenxie
 * @date 2025/11/3
 */
public class 整数反转 {

    public static void main(String[] args) {
        System.out.println(reverse(120));
    }

    public static int reverse(int x) {
        StringBuilder sb = new StringBuilder();
        sb.append(Math.abs(x));

        try{
            int num = Integer.parseInt(sb.reverse().toString());
            return x > 0 ? num : - num;
            // 实际是 NumberFormatException ， 太长了， 不容易记住， 所以直接用Exception
        }catch(Exception e) {
            return 0;
        }
    }
}

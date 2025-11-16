package com.myproj.app.algorithm.字符串;

/**
 * 给定两个以字符串形式表示的非负整数 num1 和 num2，返回 num1 和 num2 的乘积，它们的乘积也表示为字符串形式。
 * 注意：不能使用任何内置的 BigInteger 库或直接将输入转换为整数。
 *
 * 示例 1:
 * 输入: num1 = "2", num2 = "3"
 * 输出: "6"
 *
 * 示例 2:
 * 输入: num1 = "123", num2 = "456"
 * 输出: "56088"
 *
 *      思路：
 *          - 两数 从后往前 逐个相乘并累加。用数组存储相乘后的结果。
 *          - 将数组中每个元素， 逐个演化为 个位数。
 *          - 最终通过stringBuilder将数组元素连接起来， 即可
 *
 * @author shenxie
 * @date 2025/11/10
 */
public class 字符串相乘 {

    public static void main(String[] args) {
        System.out.println(multiply("123","456"));
    }

    public static String multiply(String num1, String num2) {
        if (num1.equals("0") || num2.equals("0")) {
            return "0";
        }
        int m = num1.length(), n = num2.length();
        int[] ansArr = new int[m + n];

        // 从最后一位开始相乘
        for (int i = m - 1; i >= 0; i--) {
            // 字符 转换为数字
            int x = num1.charAt(i) - '0';
            for (int j = n - 1; j >= 0; j--) {
                int y = num2.charAt(j) - '0';
                // 可能会进位：所以 i+j+1
                // 乘法之后， 需要累加
                ansArr[i + j + 1] += x * y;
            }
        }

        // 将数组元素逐个转化为个位数
        for (int i = m + n - 1; i > 0; i--) {
            // 向前进位
            ansArr[i - 1] += ansArr[i] / 10;
            // 数组元素转换为个位数
            ansArr[i] %= 10;
        }
        // 下标：从哪开始。ansArr[0] = 0 代表： 最高位没有元素， 所以从1开始， 反之，从0开始
        int index = ansArr[0] == 0 ? 1 : 0;
        // 将数组的元素 转换为 字符串
        StringBuffer ans = new StringBuffer();
        while (index < m + n) {
            ans.append(ansArr[index]);
            index++;
        }
        return ans.toString();
    }
}

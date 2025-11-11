package com.myproj.app.algorithm.字符串;

/**
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
            ansArr[i - 1] += ansArr[i] / 10;
            ansArr[i] %= 10;
        }
        // 将数组的元素 转换为 字符串
        int index = ansArr[0] == 0 ? 1 : 0;
        StringBuffer ans = new StringBuffer();
        while (index < m + n) {
            ans.append(ansArr[index]);
            index++;
        }
        return ans.toString();
    }
}

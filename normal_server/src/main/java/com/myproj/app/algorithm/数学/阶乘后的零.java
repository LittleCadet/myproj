package com.myproj.app.algorithm.数学;

/**
 * 给定一个整数 n ，返回 n! 结果中尾随零的数量。
 * 提示 n! = n * (n - 1) * (n - 2) * ... * 3 * 2 * 1
 * 示例 1：
 * 输入：n = 3
 * 输出：0
 * 解释：3! = 6 ，不含尾随 0
 *
 * 思路：
 *      1. 方法1【不行】：直接阶乘， 结果对 10 取余数： 不行： 原因： 阶乘导致精度丢失。
 *      2. 方法2【推荐】： 思维很独特： 计算最后有几个0： 那么该数阶乘的数字， 需要是5的倍数才行。
 * @author shenxie
 * @date 2023/12/30
 */
public class 阶乘后的零 {

    public static void main(String[] args) {
        // 方法1： 不行： 原因：精度丢失
//        System.out.println(trailingZeroes(13));
        // 方法2： 可以
        System.out.println(trailingZeroesV2(13));
    }

    /**
     * 方法2：
     * 计算最后有几个0： 那么该数阶乘的数字， 需要是5的倍数才行。
     */
    public static int trailingZeroesV2(int n) {
        int ans = 0;
        // 只遍历5的倍数。
        for (int i = 5; i <= n; i += 5) {
            // 当前阶乘的数字：最后会产生0的可能性：
            // 1. 5的倍数一定可以。
            // 2. 当前数 对 5 取整数， 再看是否是5的倍数： 如果是： 那么依旧可以。
            // 循环2.
            for (int x = i; x % 5 == 0; x /= 5) {
                ++ans;
            }
        }
        return ans;
    }

    /**
     * 方法1： 不能用阶乘。
     * 原因： int / long修饰的变量， 阶乘后的答案会超过 int/long的最大值。 导致精度丢失。
     */
    public static int trailingZeroes(int n) {
        if(n == 0 ) {
            return 0;
        }
        long tmp = 1;
        int ans = 0;
        while(n >0) {
            tmp *= n;
            n--;
        }
        while(tmp > 0){
            if(tmp % 10 == 0) {
                ans ++;
            }else{
                break;
            }
            tmp = tmp / 10;
        }
        return ans;
    }
}

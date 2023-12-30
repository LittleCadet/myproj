package com.myproj.app.algorithm.数学;

/**
 * 题目：
 * 给你一个非负整数 x ，计算并返回 x 的 算术平方根 。
 * 由于返回类型是整数，结果只保留 整数部分 ，小数部分将被 舍去 。
 * 注意：不允许使用任何内置指数函数和算符，例如 pow(x, 0.5) 或者 x ** 0.5 。
 *
 * 示例 2：
 * 输入：x = 8
 * 输出：2
 * 解释：8 的算术平方根是 2.82842..., 由于返回类型是整数，小数部分将被舍去。
 *
 * 思路：
 *      1. 方法1【推荐】： 二分法： 时间复杂度： 0(log n);
 *      2. 方法2： 两数相乘： 注意for循环的写法。
 *      3. 方法3： 两数相乘。
 *
 *      坑点：
 *      1. 两数相乘的结果：要用long修饰， 而不是int
 *      2. for循环的i， 要用long修饰， 而不是int。
 * @author shenxie
 * @date 2023/12/30
 */
public class x的平方根 {

    public static void main(String[] args) {
        System.out.println(mySqrt(8));
        System.out.println(mySqrtV2(8));
        System.out.println(mySqrtV3(8));
    }

    /**
     * 方法1：二分法：
     * 时间复杂度： 0(log n);
     */
    public static int mySqrt(int x) {
        int l = 0, r = x, mid = 0,ans = 0;
        while(l <= r) {
            mid = l + (r-l)/2;
            if((long)mid * mid <= x) {
                l = mid + 1;
                ans = mid;
            }else {
                r = mid - 1;
            }
        }
        return ans;
    }

    /**
     * 方法2：
     * 核心思想：
     *  1. 两数相乘 <= x时， ans = i；
     *  2. 要注意for循环的写法！！！！！
     */
    public static int mySqrtV2(int x) {
        long ans = 0;
        for(long i =0; i*i <=x ; i++) {
            ans = i;
        }
        return (int)ans;
    }

    /**
     * 方法3：自己想的：
     * 时间复杂度： 0(x/2);
     * 核心思路： 两数相乘， 结果大于x时， 取i-1; 等于x时， 取i;
     *
     * 坑点：
     * 1. 两数相乘的结果：要用long修饰， 而不是int
     * 2. for循环的i， 要用long修饰， 而不是int。
     */
    public static int mySqrtV3(int x) {
        if(x == 0) {
            return 0;
        }
        if(x == 1 ) {
            return 1;
        }
        long tmp = 1;
        int ans = 0;
        for(long i = 1; i<=x/2; i++) {
            tmp = i * i;
            ans ++;
            if(tmp > x) {
                return (int)i-1;
            }else if(tmp == x) {
                return (int)i;
            }
        }
        return ans;
    }
}

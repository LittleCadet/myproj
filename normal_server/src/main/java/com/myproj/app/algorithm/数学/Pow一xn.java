package com.myproj.app.algorithm.数学;

/**
 * 题目：
 * 实现 pow(x, n) ，即计算 x 的整数 n 次幂函数（即，xn ）。
 * 示例 1：
 * 输入：x = 2.00000, n = 10
 * 输出：1024.00000
 *
 * 示例 2：
 * 输入：x = 2.00000, n = -2
 * 输出：0.25000
 * 解释：2-2 = 1/22 = 1/4 = 0.25
 *
 * 思路：
 *      1. 方法1：累乘：条件多 + 容易错 + 超时
 *      2. 方法2【推荐】：递归 + 二分法
 *
 * @author shenxie
 * @date 2023/12/30
 */
public class Pow一xn {
    public static void main(String[] args) {
        // 方法1： 累乘：条件多 + 容易错 + 超时
        System.out.println(myPow(2.00000, 2));
        // 方法2： 递归 + 二分法
        System.out.println(myPowV2(2.00000, 2));
    }

    /**
     * 递归 +二分法：
     *      1. 思路： 2的4次方 = 2的2次方 * 2的2次方
     *              所以对n二分即可。
     *      2. 二分法： 对n二分：
     *          n == 0 时： 任何数的0次方 都是1；
     *          n是奇数时： ans * ans * x
     *          n是偶数时： ans * ans ;
     */
    public static double myPowV2(double x, int n) {
        return n > 0 ? process(x, n) : 1/ process(x,n);
    }

    /**
     * 递归：自底向上
     */
    private static double process(double x, int n){
        if(n == 0) {
            return 1;
        }
        double ans = process(x, n / 2);
        return n % 2 == 0? ans * ans : ans * ans * x;
    }

    /**
     * 方法1： 自己想的：
     *  逐个累乘： 速度太慢。 当n无穷大时： 超时。
     */
    public static double myPow(double x, int n) {
        double ans = 1;
        double pre = 1;
        if(x == 1.00000) {
            return 1;
        }else if(x == -1.00000 && n > 0) {
            return -1;
        }
        if(n > 0) {
            for(long i =1 ; i<=n; i++) {
                ans *= x;
            }
        }else if(n == 0){
            ans = 1;
        }else if(n == 1) {
            ans = x;
        }else{
            x = 1/x;
            for(long i = n ; i< 0; i++) {
                ans *= x;
                if(pre == ans) {
                    return ans;
                }
                pre = ans;
            }
        }
        return ans;
    }
}

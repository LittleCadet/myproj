package com.myproj.app.algorithm.数学;

import com.myproj.app.algorithm.堆.丑数II;

/**
 * 丑数 就是只包含质因数 2、3 和 5 的 正 整数。
 * 给你一个整数 n ，请你判断 n 是否为 丑数 。如果是，返回 true ；否则，返回 false 。
 *
 * 示例 1：
 * 输入：n = 6
 * 输出：true
 * 解释：6 = 2 × 3
 *
 * 示例 2：
 * 输入：n = 1
 * 输出：true
 * 解释：1 没有质因数。
 *
 * 示例 3：
 * 输入：n = 14
 * 输出：false
 * 解释：14 不是丑数，因为它包含了另外一个质因数 7 。
 *
 *
 *      思路：
 *          - 丑数 就是只包含质因数 2、3 和 5 的 正 整数。 即为: 2*number , 3*number , 5*number 都可以：所以逆向判定的过程就是：不断 取余 就行
 *          - 与{@link 丑数II}类似：
 *              - {@link 丑数}： 是判定是否是丑数， 是逆向判定的过程。
 *              - {@link 丑数II}: 是一个生成丑数的正向过程，需要借助 PriorityQueue 和 HashSet来实现
 * @author shenxie
 * @date 2025/11/19
 */
public class 丑数 {

    public static void main(String[] args) {
        System.out.println(isUgly(50));
    }

    public static boolean isUgly(int n) {
        if(n == 0 ){
            return false;
        }
        int[] nums = new int[]{2,3,5};
        for(int num : nums) {
            while(n % num == 0 ){
                n = n / num;
            }
        }

        return n == 1;
    }
}

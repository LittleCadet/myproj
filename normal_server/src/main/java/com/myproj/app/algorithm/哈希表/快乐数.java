package com.myproj.app.algorithm.哈希表;

import java.util.HashSet;
import java.util.Set;

/**
 * 题目：
 * 编写一个算法来判断一个数 n 是不是快乐数。
 * 「快乐数」 定义为：
 *     对于一个正整数，每一次将该数替换为它每个位置上的数字的平方和。
 *     然后重复这个过程直到这个数变为 1，也可能是 无限循环 但始终变不到 1。
 *     如果这个过程 结果为 1，那么这个数就是快乐数。
 * 如果 n 是 快乐数 就返回 true ；不是，则返回 false 。
 * 示例 1：
 * 输入：n = 19
 * 输出：true
 * 解释：
 * 12 + 92 = 82
 * 82 + 22 = 68
 * 62 + 82 = 100
 * 12 + 02 + 02 = 1
 *
 * 思路：
 *      1. hash集合。
 *          核心难点：
 *              1.1 如何将一个数字拆分为一个个子数字， 并且求各个子数字的平方和。
 *                  while(n > 0) {
                      int nums = n % 10;
                      n = n / 10;
                      sum += nums * nums;
                    }
 *              1.2 如何判定一个数字是否会一直计算下去？
 *                  实际上问的是： 该题一共有几种情况：
 *                      情况1： 若干次运算后，平方和 = 1
 *                      情况2： 若干次运算后，n会循环： eg: 116, 若干次运算后：平方和为58， 再经过若干次运算后， 平方和还是58.
 *                      情况3： 一直运算下去， n不会重复。  实际上： 该情况不会存在。
 *                  综上： 最终判定条件为：平方和=1 或者 平方和已经出现在set容器中了， 则跳出循环。
 *
 * @author shenxie
 * @date 2023/12/28
 */
public class 快乐数 {

    public static void main(String[] args) {
        System.out.println(isHappy(19));
    }

    public static boolean isHappy(int n) {
        // 难点1： 如何将一个数字拆分为一个个数字 。
        Set<Integer> set = new HashSet<>();
        while( n != 1 && ! set.contains(n)){
            set.add(n);
            n = processNums(n);
        }
        return n == 1;
    }

    private static int processNums(int n){
        int sum = 0;
        while(n > 0) {
            int nums = n % 10;
            n = n / 10;
            sum += nums * nums;
        }
        return sum;
    }
}

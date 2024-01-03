package com.myproj.app.algorithm.动态规划;

import java.util.Arrays;

/**
 * 题目：
 * 给你一个整数数组 coins ，表示不同面额的硬币；以及一个整数 amount ，表示总金额。
 * 计算并返回可以凑成总金额所需的 最少的硬币个数 。如果没有任何一种硬币组合能组成总金额，返回 -1 。
 * 你可以认为每种硬币的数量是无限的。
 * 示例 1：
 * 输入：coins = [1, 2, 5], amount = 11
 * 输出：3
 * 解释：11 = 5 + 5 + 1
 *
 * 思路：
 * 1. 动态规划：
 *      坑点1： 绝对不能使用贪心算法， 因为： 对于金额 - 最大硬币数 < 所有硬币的数额时， 不生效。
 *      正确做法： 递归 + 动态规划。
 *          1. 递归的原因：
 *              1.1 可以将每种不同数额的硬币 都计算下， 避免漏解；
 *                  既能同一硬币多次使用， 又能每次使用不同数额的硬币
 *              1.2 每次最外面那层递归， 使用的是原来的值。
 *          2. 动态规划在这题中的表现：
 *                  存储剩余amount所需的最少硬币个数。
 *          3. 确定不用执行最优子结构都知道的结果：
 *                  if (rem == 0) return 0;
 *
 * @author shenxie
 * @date 2023/12/16
 */
public class 零钱兑换 {

    public static void main(String[] args) {
        // 贪心算法： 是解决不了的。
//        System.out.println(coinChangeV2(new int[]{5,3,2}, 6));
        System.out.println(coinChangeV3(new int[]{5,3,2}, 6));
    }


    public static int coinChangeV3(int[] coins, int amount) {
        // 初始条件检查
        if (amount < 1) return 0;
        // 动态规划入口
        return coinChange(coins, amount, new int[amount]);
    }

    /**
     * 自上而下的动态规划方法
     * coins:硬币面额
     * rem:余额
     * count:存储中间计算结果，空间换时间
     */
    private static int coinChange(int[] coins, int rem, int[] count) {
        // 结束条件：此路径不通：
        // 返回 -1的原因： 避免： 将错误情况 + 1.
        if (rem < 0) return -1;
        // 结束条件：余额为0，成功结束
        if (rem == 0) return 0;
        // 之前已经计算过这种情况，直接返回结果，避免重复计算
        if (count[rem - 1] != 0) return count[rem - 1];
        // min用于存储锁使用硬币的最少个数。
        int min = Integer.MAX_VALUE;
        int nums = 0;
        // 遍历当前递归子树的每一种情况
        for (int coin : coins) {
            // 用一下coin这个面值的硬币会怎样？res是这个方法的最优情况
            System.out.println("运行：" + ++nums + ",rem:" + rem + ",coin:" + coin);
            int res = coinChange(coins, rem - coin, count);
            // res<0 即为 res=-1,此法失败，res>min不是最优情况，舍去
            if (res >= 0){
                min = Math.min(min, res + 1);
            }
        }
        // count[rem - 1]存储着给定金额amount【即为余额rem】的解
        // 若为Integer.MAX_VALUE则该情况无解
        // 余额rem 确用rem-1的原因： count[] 声明的大小为amount, 而amount在count的最后一个位置【即为amount-1】
        count[rem - 1] = (min == Integer.MAX_VALUE) ? -1 : min;
        System.out.println("count[" + (rem -1) + "]:" + count[rem - 1]);
        return count[rem - 1];
    }

    /**
     * 贪心算法：
     *  可能不是最优解， 或者得到一个错误的解。
     */
    public static int coinChange(int[] coins, int amount) {
        int nums = 0;
        int tmp = 0;
        Arrays.sort(coins);
        for(int i = coins.length -1; i>=0; i-- ) {
            while(amount != 0 ){
                tmp = amount - coins[i];
                if(tmp < 0 || amount < coins[i]) {
                    break;
                }
                amount = tmp;
                nums ++;
            }
            if(amount == 0) {
                break;
            }
        }
        if(amount != 0) {
            return -1;
        }
        return nums;

    }
}

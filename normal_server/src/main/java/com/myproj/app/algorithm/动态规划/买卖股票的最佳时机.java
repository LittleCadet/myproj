package com.myproj.app.algorithm.动态规划;

/**
 * 题目：
 * 给定一个数组 prices ，它的第 i 个元素 prices[i] 表示一支给定股票第 i 天的价格。
 * 你只能选择 某一天 买入这只股票，并选择在 未来的某一个不同的日子 卖出该股票。设计一个算法来计算你所能获取的最大利润。
 * 返回你可以从这笔交易中获取的最大利润。如果你不能获取任何利润，返回 0 。
 *
 * 分析：
 * 1. 一次交易 产生的最大利润。
 *
 * 思路：
 * 1. 动态规划： 用以前问题的解 来解答当前的问题。特殊API: Integer.MAX_VALUE.
 * 2. 最优子结构的确立：
 *         int profit = Math.max(profit, prices[i] - cost);
 * 3. 以前问题的解： 即为： Math.max(profit, prices[i] - cost)中的profit;
 * 4. 当前的问题： 即为： int profit = Math.max(profit, prices[i] - cost)中的等于左边的profit;
 *
 * @author shenxie
 * @date 2023/12/9
 */
public class 买卖股票的最佳时机 {

    public static void main(String[] args) {
        maxProfit(new int[]{7,1,5,3,6,4});
    }

    public static int maxProfit(int[] prices) {
        int cost = Integer.MAX_VALUE, profit=0;
        for(int i = 0; i< prices.length; i++) {
            cost = Math.min(cost, prices[i]);
            profit=Math.max(profit, prices[i] - cost);
        }
        return profit;
    }
}

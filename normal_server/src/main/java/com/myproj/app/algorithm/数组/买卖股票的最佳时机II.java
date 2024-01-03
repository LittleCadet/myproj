package com.myproj.app.algorithm.数组;

/**
 * 题目：
 * 给你一个整数数组 prices ，其中 prices[i] 表示某支股票第 i 天的价格。
 * 在每一天，你可以决定是否购买和/或出售股票。你在任何时候 最多 只能持有 一股 股票。你也可以先购买，然后在 同一天 出售。
 * 返回 你能获得的 最大 利润 。
 *
 * 分析：
 * 1. 可以多次买卖。 获取多次买卖的利益最大值。
 *
 * 思路：
 * 1. 贪心算法：本质： 只考虑现在， 不考虑过去。
 *      方法1：
 *          1. 当前价格 和 上一次价格 之差 和 0 比， 取大；
 *          2. 之后： profit += tmp
 *      方法2【推荐 ：更好理解】：
 *          1. 在当前价格 比 上一次价格大的时候， 获取利润。
 *          2. 之后： profit += tmp
 *
 * @author shenxie
 * @date 2023/12/9
 */
public class 买卖股票的最佳时机II {

    public static void main(String[] args) {
        System.out.println(maxProfit(new int[]{7,1,5,3,6,4}));;
        System.out.println(maxProfitV2(new int[]{7,1,5,3,6,4}));;
    }

    public static int maxProfit(int[] prices) {

        // 贪心：
        int profit = 0;
        for(int i = 1 ; i < prices.length; i++) {
            int tmp = prices[i] - prices[i - 1];
            profit += Math.max(tmp, 0);;
        }
        return profit;
    }
    public static int maxProfitV2(int[] prices) {

        // 贪心：
        int profit = 0;
        for(int i = 1; i< prices.length; i++) {
            int tmp = 0;
            if(prices[i] > prices[i-1]){
                tmp = prices[i] - prices[i-1];
            }
            profit += tmp;
        }
        return profit;
    }

}

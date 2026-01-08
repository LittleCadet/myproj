package com.myproj.app.algorithm.动态规划;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 给你一个整数 n 表示数轴上的房屋数量，编号从 0 到 n - 1 。
 * 另给你一个二维整数数组 offers ，其中 offers[i] = [starti, endi, goldi] 表示第 i 个买家想要以 goldi 枚金币的价格购买从 starti 到 endi 的所有房屋。
 * 作为一名销售，你需要有策略地选择并销售房屋使自己的收入最大化。
 * 返回你可以赚取的金币的最大数目。
 * 注意 同一所房屋不能卖给不同的买家，并且允许保留一些房屋不进行出售。
 *
 * 示例 1：
 * 输入：n = 5, offers = [[0,0,1],[0,2,2],[1,3,2]]
 * 输出：3
 * 解释：
 * 有 5 所房屋，编号从 0 到 4 ，共有 3 个购买要约。
 * 将位于 [0,0] 范围内的房屋以 1 金币的价格出售给第 1 位买家，并将位于 [1,3] 范围内的房屋以 2 金币的价格出售给第 3 位买家。
 * 可以证明我们最多只能获得 3 枚金币。
 *
 * 示例 2：
 * 输入：n = 5, offers = [[0,0,1],[0,2,10],[1,3,2]]
 * 输出：10
 * 解释：有 5 所房屋，编号从 0 到 4 ，共有 3 个购买要约。
 * 将位于 [0,2] 范围内的房屋以 10 金币的价格出售给第 2 位买家。
 * 可以证明我们最多只能获得 10 枚金币。
 *
 *      思路：
 *          - 动态规划：
 *              - 不卖：dp[end + 1] = dp[end]
 *              - 卖： dp[start] + gold
 *              - Math.max(不卖， 卖);
 *
 * @author shenxie
 * @date 2025/11/17
 */
public class 销售利润最大化 {

    public static void main(String[] args) {
        List<List<Integer>> offers = new ArrayList<>();
        List<Integer> offer1 = new ArrayList<>();
        offer1.add(0);
        offer1.add(0);
        offer1.add(1);
        List<Integer> offer2 = new ArrayList<>();
        offer2.add(0);
        offer2.add(2);
        offer2.add(2);
        List<Integer> offer3 = new ArrayList<>();
        offer3.add(1);
        offer3.add(3);
        offer3.add(2);

        offers.add(offer1);
        offers.add(offer2);
        offers.add(offer3);


        System.out.println(maximizeTheProfit(5, offers));
    }

    public static int maximizeTheProfit(int n, List<List<Integer>> offers) {
        //dp[i]表示在当前订单下，出售前i间房时能卖出的最多金币
        int[] dp = new int[n+1];
        Map<Integer,List<int[]>> groups = new HashMap<>();

        //选或不选 => (选的有多个)选哪个
        //按照end分组，因为该房子卖的话，可能有多种方案涉及到，要选最大价值的那套方案
        for(List<Integer> offer: offers){
            List<int[]> group = groups.getOrDefault(offer.get(1),new ArrayList<>());
            group.add(new int[]{offer.get(0),offer.get(2)});
            groups.put(offer.get(1),group);
        }

        for(int end=0;end<n;end++){//不管有没有买end号房的，直接从0遍历到n
            List<int[]> group = groups.getOrDefault(end,new ArrayList<>());
            System.out.println("end:" + end + ", group:" + group);
            //既有不选的作用，又能让dp保持连续
            dp[end+1] = dp[end];
            //选,买end号房的方案可能有多个
            for(int[] endOffer:group){
                System.out.println("dp[end+1]:" + dp[end+1] + ", sum:" + dp[endOffer[0]] + endOffer[1]  );
                // dp[end + 1]: 存量：代表不卖
                // dp[endOffer[0]] + endOffer[1]: 增量：代表卖
                dp[end+1] = Math.max(dp[end+1],dp[endOffer[0]] + endOffer[1]);
                System.out.println("result:" + dp[end+1]);
            }
        }

        return dp[n];

    }


}

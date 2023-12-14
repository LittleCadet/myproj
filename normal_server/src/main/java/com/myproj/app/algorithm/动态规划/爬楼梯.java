package com.myproj.app.algorithm.动态规划;

import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 题目：
 * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
 * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 *
 * 思路：
 * 1. 动态规划。用tmp为变量a,b 完成互相赋值的动作 即可。
 *
 * @author shenxie
 * @date 2023/12/7
 */
public class 爬楼梯 {

    public static void main(String[] args) {

//        System.out.println("共有:" + climbingStairsBacktrack(10) + "种爬楼梯的方法。");

//        System.out.println("共有:" + climbingStairsBacktrackV2(10) + "种爬楼梯的方法。");

        System.out.println(climbStairs(5));
    }


    /**
     * 爬1阶楼梯， 共有1种爬法。
     * 爬2阶楼梯， 共有2种爬法。
     * 爬3阶楼梯， 共有3种爬法。
     * 爬4阶楼梯， 共有5种爬法。
     * 爬5阶楼梯， 共有8种爬法。
     * 爬6阶楼梯， 共有13种爬法。
     *
     * 找规律：
     * 所以最佳解法为： 爬N阶楼梯， 共有chices.get(N-1) + choice.get(n-2)种爬法。
     */
    /* 爬楼梯：回溯 */
    public static int climbingStairsBacktrack(int n) {
        List<Integer> choices = Arrays.asList(1, 2); // 可选择向上爬 1 阶或 2 阶
        int state = 0; // 从第 0 阶开始爬
        List<Integer> res = new ArrayList<>();
        res.add(0); // 使用 res[0] 记录方案数量
        backtrack(choices, state, n, res);
        return res.get(0);
    }

    public static int climbingStairsBacktrackV2(int n) {
        List<Integer> choices = Lists.newArrayList(1, 2); // 可选择向上爬 1 阶或 2 阶
        if(n == 1){
            return 1;
        } else if (n == 2) {
            return 2;
        }else {
            return backtrackV2(choices, 3, n);
        }
    }

    /**
     * 这里不使用nums[n] = nums[n-1] + nums[n-2]的原因：
     * 1. nums[n] = nums[n-1] + nums[n-2]的空间复杂度： 为0(n)
     * 2. num = a + b的空间复杂度: 0(1)
     */
    public static int climbStairs(int n) {
        // 动态规划。
        if(n == 1 || n == 2) {
            return n;
        }
        int nums = 0;
        int a = 1;
        int b = 2;
        for(int i = 2 ; i< n; i ++) {
            // 需要用nums临时存储， 如果直接赋值给b， 则a也是b的值了。
            nums = a + b;
            a = b;
            b = nums;
        }
        return b;

    }

    public static int backtrackV2(List<Integer> choices, int start, int n) {
        int size = choices.size();
        int i = choices.get(size -1) + choices.get(size - 2);
        choices.add(i);
        if(start == n) {
            return i;
        }
        return backtrackV2(choices, start + 1, n );
    }

    /**
     *
     * @param choices 选择： 最初2种解决方案
     * @param state 第M阶楼梯
     * @param n 共N阶楼梯
     * @param res 方案个数
     */
    /* 回溯 */
    public static void backtrack(List<Integer> choices, int state, int n, List<Integer> res) {
        // 当爬到第 n 阶时，方案数量加 1
        if (state == n){
            res.set(0, res.get(0) + 1);
            System.out.println("第" + (res.get(0)) + "种方案");
        }
        // 遍历所有选择
        for (Integer choice : choices) {
            // 剪枝：不允许越过第 n 阶
            if (state + choice > n)
                continue;
            // 尝试：做出选择，更新状态
            System.out.println("choices:" + choices + ", state: " + state + ", choice:" + choice + ", n:" + n + ",res: " + (res.get(0) + 1));
            backtrack(choices, state + choice, n, res);
        }
    }



}

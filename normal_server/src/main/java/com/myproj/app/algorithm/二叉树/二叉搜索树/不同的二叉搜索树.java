package com.myproj.app.algorithm.二叉树.二叉搜索树;

/**
 * 给你一个整数 n ，求恰由 n 个节点组成且节点值从 1 到 n 互不相同的 二叉搜索树 有多少种？返回满足题意的二叉搜索树的种数。
 *
 * 示例 1：
 * 输入：n = 3
 * 输出：5
 *
 * 示例 2：
 * 输入：n = 1
 * 输出：1
 *
 * 提示：
 *     1 <= n <= 19
 *
 *     思路：
 *      - 动态规划：
 *          最优子结构：dp[i] += dp[j-1] * dp[i-j];
 * @author shenxie
 * @date 2025/11/4
 */
public class 不同的二叉搜索树 {

    public static void main(String[] args) {
        System.out.println(numTrees(3));
    }

    /**
     *
     dp[i] 表示节点数为 i 的不同二叉搜索树的数量。

     dp[0] = 1：当没有节点时，只有一种空树。dp[1] = 1：当只有一个节点时，只有一种单节点树。
     对于节点数 i，尝试每一个可能的节点作为根节点。
     设根节点为 j（1 <= j <= i），那么：
     左子树有 j - 1 个节点。 右子树有 i - j 个节点。
     所以，左子树和右子树的组合数是 dp[j - 1] * dp[i - j]。
     不同的根节点会产生不同的树形结构，需要将这些组合数累加到 dp[i]。

     dp[n] 就是节点数为 n 的所有不同二叉搜索树的数量。

     */
    public static int numTrees(int n) {
        int[] dp = new int[n+1];
        dp[0] = 1;
        dp[1] = 1;
        // i为总数n的循环
        for(int i = 2; i<=n; i++) {
            // j 为根节点的循环， j <= i , 而 i从2开始， 所以 j从1开始
            for(int j = 1; j<=i; j++) {
                // dp[j-1]: 代表 左子树的数量
                // dp[i-j]: 代表 总数为i时， 右子树的数量
                // 则 dp[i] += dp[j-1] * dp[i-j];
                dp[i] += dp[j-1] * dp[i-j];
            }
        }
        return dp[n];
    }
}

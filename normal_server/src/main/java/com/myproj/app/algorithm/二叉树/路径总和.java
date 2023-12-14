package com.myproj.app.algorithm.二叉树;

/**
 * 题目：
 * 给你二叉树的根节点 root 和一个表示目标和的整数 targetSum 。判断该树中是否存在 根节点到叶子节点 的路径，这条路径上所有节点值相加等于目标和 targetSum 。如果存在，返回 true ；否则，返回 false 。
 * 思路：
 * 1. 深度优先算法：
 * 2. 相加和 是否等于 给定值的问题： 可以尝试： 用给定值累减的方式来判定。
 *
 * @author shenxie
 * @date 2023/12/13
 */
public class 路径总和 {

    public static void main(String[] args) {
        hasPathSum(null, 10);
    }

    public static boolean hasPathSum(TreeNode root, int targetSum) {
        if(null == root) {
            return false;
        }
        if (root.left == null && root.right == null) {
            return targetSum == root.val;
        }
        return hasPathSum(root.left, targetSum - root.val) || hasPathSum(root.right, targetSum - root.val);
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

}

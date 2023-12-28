package com.myproj.app.algorithm.二叉树.二叉搜索树;

/**
 * 题目：
 * 给你一个二叉搜索树的根节点 root ，返回 树中任意两不同节点值之间的最小差值 。
 * 差值是一个正数，其数值等于两值之差的绝对值。
 * 思路：
 * 1. 中序遍历： 原因：
 *      二叉搜索树的特性： 左子节点 < 当前节点 < 右子节点
 *      所以res = Math.min(res, pre元素 - 当前元素); 即可
 * @author shenxie
 * @date 2023/12/16
 */
public class 二叉搜索树的最小绝对差 {

    public static void main(String[] args) {
        TreeNode node = new TreeNode(1);
        node.left = new TreeNode(2);
        node.right = new TreeNode(3);
        System.out.println(getMinimumDifference(node));
    }


    static int res = Integer.MAX_VALUE;
    static int pre;
    public static int getMinimumDifference(TreeNode root) {
        // 不能直接将root.val直接复制给root, 因为这样： Math.min()的最小值为0.
        pre = -1;
        dfs(root, pre);
        return res;
    }

    public static void dfs(TreeNode root, int pre) {
        if (null == root) {
            return;
        }
        dfs(root.left, pre);
        if (pre == -1) {
            pre = root.val;
        } else {
            res = Math.min(res, root.val - pre);
            pre = root.val;
        }
        dfs(root.right, pre);
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

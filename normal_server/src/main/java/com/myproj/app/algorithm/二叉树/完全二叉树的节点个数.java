package com.myproj.app.algorithm.二叉树;

/**
 * 题目：
 * 给你一棵 完全二叉树 的根节点 root ，求出该树的节点个数。
 * 思路：
 * 深度优先算法：
 *      1. 一定要关注： 递归之后的步骤是什么。
 * 
 * @author shenxie
 * @date 2023/12/13
 */
public class 完全二叉树的节点个数 {

    public static void main(String[] args) {
        countNodes(null);
    }

    public static int countNodes(TreeNode root) {
        if(null == root) {
            return 0;
        }
        int left = countNodes(root.left);
        int right = countNodes(root.right);
        return left + right + 1;
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

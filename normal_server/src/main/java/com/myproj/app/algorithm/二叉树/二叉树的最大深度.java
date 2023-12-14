package com.myproj.app.algorithm.二叉树;

/**
 * 题目：
 * 给定一个二叉树 root ，返回其最大深度。
 * 二叉树的 最大深度 是指从根节点到最远叶子节点的最长路径上的节点数。
 *
 * 思路：
 * 深度优先算法：
 *  实现计数最巧妙的方法： 在maxDepth()的结尾添加Math.max(left, right) + 1;
 *  原因： 每递归一次maxDepth(), 在最后一步：Math.max(left, right) + 1。
 *
 * @author shenxie
 * @date 2023/12/13
 */
public class 二叉树的最大深度 {

    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(1);
        treeNode.left = new TreeNode(3);
        treeNode.right = new TreeNode(4);
        System.out.println(maxDepth(treeNode));
    }

    public static int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        } else {
            // 之所以能实现计数： 是因为：每递归一次maxDepth(), 在最后一步：Math.max(left, right) + 1
            int left = maxDepth(root.left);
            int right = maxDepth(root.right);
            return Math.max(left, right) + 1;
        }

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

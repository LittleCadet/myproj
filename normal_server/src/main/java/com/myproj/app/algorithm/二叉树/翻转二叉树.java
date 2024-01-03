package com.myproj.app.algorithm.二叉树;

/**
 * 题目：
 * 给你一棵二叉树的根节点 root ，翻转这棵二叉树，并返回其根节点。 即为： 镜像这个二叉树。
 * 思路：
 * 深度优先算法： 递归算法的本质： 一定要看递归后面的步骤。
 *
 *
 * @author shenxie
 * @date 2023/12/13
 */
public class 翻转二叉树 {

    public static void main(String[] args) {
        invertTree(null);
    }

    public static TreeNode invertTree(TreeNode root) {
        if(null == root) {
            return null;
        }
        TreeNode left = invertTree(root.left);
        TreeNode right = invertTree(root.right);
        root.left = right;
        root.right = left;
        return root;
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

package com.myproj.app.algorithm.二叉树;

/**
 * 题目：
 * 给你一个二叉树的根节点 root ，判断其是否是一个有效的二叉搜索树。
 * 思路：
 * 1. 中序查找：原因：
 *      二叉搜索树的特性：
 *          一定成立：root.left.val < root < root.right.val
 *          一定不成立：root.val <= pre
 *
 * @author shenxie
 * @date 2023/12/16
 */
public class 判定是否是有效的二叉搜索树 {
    static long pre = Long.MIN_VALUE;

    public static void main(String[] args) {
        System.out.println(isValidBST(null));
    }
    public static boolean isValidBST(TreeNode root) {
        return dfs(root);
    }

    public static boolean dfs(TreeNode root){
        if(null == root) {
            return true;
        }
        boolean left = dfs(root.left);
        if(root.val <= pre) {
            return false;
        }
        pre = root.val;
        boolean right = dfs(root.right);

        return left && right;
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

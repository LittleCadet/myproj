package com.myproj.app.algorithm.二叉树;

/**
 * 题目：
 * 给你一个二叉树的根节点 root ， 检查它是否轴对称。
 * 思路：
 * 深度优先算法：
 *      1. 对称二叉树 与 相同的树， 本质上是一个题目。
 *      2. 一棵树是否是对称二叉树， 该问题转换为两棵树是否是对称二叉树即可
 *
 *
 * @author shenxie
 * @date 2023/12/13
 */
public class 对称二叉树 {

    public static void main(String[] args) {
        isSymmetric(null);
    }

    public static boolean isSymmetric(TreeNode root) {
        return check(root, root);
    }

    public static boolean check(TreeNode p, TreeNode q) {
        if (null == p && null == q) {
            return true;
        } else if (null == p || null == q) {
            return false;
        }
        return p.val == q.val && check(p.left, q.right) && check(p.right, q.left);
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

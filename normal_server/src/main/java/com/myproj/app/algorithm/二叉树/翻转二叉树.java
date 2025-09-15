package com.myproj.app.algorithm.二叉树;

/**
 * 题目：
 * 给你一棵二叉树的根节点 root ，翻转这棵二叉树，并返回其根节点。 即为： 镜像这个二叉树。
 * 思路：
 *      方法1：后序遍历：
 *          - 即为先遍历， 最后交换。
 *      方法2：先序遍历：
 *          - 即为先交换，再遍历
 *
 *
 * @author shenxie
 * @date 2023/12/13
 */
public class 翻转二叉树 {

    public static void main(String[] args) {
        invertTree(null);
    }

    /**
     * 方法1： 后续遍历
     */
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


    /**
     * 方法2： 先序遍历
     */
    public TreeNode invertTreeV2(TreeNode root) {
        if( null == root) {
            return root;
        }
        TreeNode tmp = root.left;
        root.left = root.right;
        root.right = tmp;

        invertTreeV2(root.left);
        invertTreeV2(root.right);

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

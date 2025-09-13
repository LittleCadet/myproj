package com.myproj.app.algorithm_二刷.二叉树;

/**
 * 给定一个二叉树 root ，返回其最大深度。
 * 二叉树的 最大深度 是指从根节点到最远叶子节点的最长路径上的节点数。
 *
 * 示例 1：
 * 输入：root = [3,9,20,null,null,15,7]
 * 输出：3
 *
 * 示例 2：
 * 输入：root = [1,null,2]
 * 输出：2
 *
 *      思路：
 *          -- 本题题意： 求得是 节点的最大深度， 所以节点本身也算深度  , 与 《二叉树的直径》要区分开
 *          -- 深度遍历： 即为前序遍历 / 后序遍历。
 *          -- 最大深度的理解： 可能在左边， 也可能在右边， 所以都要计算深度， 取大
 * @author shenxie
 **/
public class 二叉树的最大深度 {

    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(1);
        treeNode.left = new TreeNode(3);
        treeNode.right = new TreeNode(4);
        System.out.println(maxDepth(treeNode));
    }

    public static int maxDepth(TreeNode root) {
        return depth(root);
    }

    public static int depth(TreeNode root) {
        if(null == root) {
            return 0;
        }
        int leftHight = depth(root.left) + 1;
        int rightHight = depth(root.right) + 1;
        return Math.max(leftHight, rightHight);
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

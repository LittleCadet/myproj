package com.myproj.app.algorithm.二叉树;

import java.util.ArrayList;
import java.util.List;

/**
 * 题目：
 * 给定一个二叉搜索树的根节点 root ，和一个整数 k ，请你设计一个算法查找其中第 k 个最小元素（从 1 开始计数）。
 * 思路：
 * 1. 中序遍历：原因：
 *      二叉搜索树的特性： 左子节点 < 当前节点 < 右子节点
 *
 * @author shenxie
 * @date 2023/12/16
 */
public class 二叉搜索树中第K小的元素 {

    public static void main(String[] args) {
        kthSmallest(null, 1);
    }

    public static int kthSmallest(TreeNode root, int k) {
        List<Integer> treeNodes = new ArrayList<>();
        dfs(root, treeNodes);
        return treeNodes.get(k-1);
    }

    public static void dfs(TreeNode root, List<Integer> treeNodes) {
        if(null == root) {
            return ;
        }

        dfs(root.left,treeNodes);
        treeNodes.add(root.val);
        dfs(root.right,treeNodes);
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

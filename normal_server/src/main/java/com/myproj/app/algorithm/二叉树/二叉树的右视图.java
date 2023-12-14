package com.myproj.app.algorithm.二叉树;

import java.util.ArrayList;
import java.util.List;

/**
 * 题目：
 * 给定一个二叉树的 根节点 root，想象自己站在它的右侧，按照从顶部到底部的顺序，返回从右侧所能看到的节点值。
 * 思路：
 * 1. 深度优先算法。
 *      1. 因为是右视图： 代表： 每一层只取最右边节点的值
 *      2. 在当前高度 > 最大高度时， 才塞值。
 *      3. 所以先递归遍历右子节点， 之后再递归遍历左子节点
 *
 * @author shenxie
 * @date 2023/12/13
 */
public class 二叉树的右视图 {


    static int maxHigh = 0;
    static List<Integer> res = new ArrayList<Integer>();

    public static void main(String[] args) {
        rightSideView(null);
    }
    public static List<Integer> rightSideView(TreeNode root) {
        dfs(root,1);
        return res;
    }
    public static void dfs(TreeNode root,int high){
        if(root == null) return;
        if(high > maxHigh){
            res.add(root.val);
            maxHigh = high;
        }
        dfs(root.right,high+1);
        dfs(root.left,high+1);
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

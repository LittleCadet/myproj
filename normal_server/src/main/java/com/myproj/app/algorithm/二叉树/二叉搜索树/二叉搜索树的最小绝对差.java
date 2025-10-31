package com.myproj.app.algorithm.二叉树.二叉搜索树;

import java.util.ArrayList;
import java.util.List;

/**
 * 题目：
 * 给你一个二叉搜索树的根节点 root ，返回 树中任意两不同节点值之间的最小差值 。
 * 差值是一个正数，其数值等于两值之差的绝对值。
 * 思路：
 * 1. 中序遍历： 原因：
 *      二叉搜索树的特性： 左子节点 < 当前节点 < 右子节点
 *      所以res = Math.min(res, pre元素 - 当前元素); 即可
 * 2. 中序遍历： 将搜索树 => list，  则list两个相邻元素差的最小值 即为 搜索树的最小绝对差
 * @author shenxie
 * @date 2023/12/16
 */
public class 二叉搜索树的最小绝对差 {

    public static void main(String[] args) {
        TreeNode node = new TreeNode(100000);
        node.left = new TreeNode(0);
//        node.right = new TreeNode(3);
        System.out.println(getMinimumDifference(node));
    }


    static int res = Integer.MAX_VALUE;
    static int pre;
    public static int getMinimumDifference(TreeNode root) {
        // 不能直接将root.val直接复制给root, 因为这样： Math.min()的最小值为0.
        pre = -1;
        dfsCopy(root);
//        dfsCopyV2(root, new ArrayList<>());
        return res;
    }

    /**
     * 方法1： 中序查找
     */
    public static void dfsCopy(TreeNode root) {
        if (null == root) {
            return;
        }
        dfsCopy(root.left);
        if (pre == -1) {
            pre = root.val;
        } else {
            res = Math.min(res, root.val - pre);
            pre = root.val;
        }
        dfsCopy(root.right);
    }

    /**
     * 方法2： 中序查找：将搜索树 转换为 list, 则list两个相邻元素差的最小值 即为 搜索树的最小绝对差
     */
    public static Integer dfsCopyV2(TreeNode root, List<Integer> nums) {
        dfsCopyV2(root, nums);

        int result = Integer.MAX_VALUE;
        for(int i = 1 ; i < nums.size() - 1; i++) {
            result = Math.min(result, nums.get(i) - nums.get(i-1));
        }

        return result;
    }

    public static void dfsV2(TreeNode root, List<Integer> nums) {
        if(null == root) {
            return ;
        }
        dfsV2(root.left, nums);
        nums.add(root.val);
        dfsV2(root.right, nums);
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

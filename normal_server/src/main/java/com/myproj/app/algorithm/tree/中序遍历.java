package com.myproj.app.algorithm.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * @author shenxie
 * @date 2023/12/8
 */
public class 中序遍历 {

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

    public static List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        order(root, list);
        return list;
    }

    public static void order(TreeNode root, List<Integer> list) {
        if (null == root) {
            return;
        }

        order(root.left, list);
        list.add(root.val);
        order(root.right, list);
    }

}

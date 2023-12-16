package com.myproj.app.algorithm.二叉树;

import java.util.ArrayList;
import java.util.List;

/**
 * 题目：
 * 给你二叉树的根结点 root ，请你将它展开为一个单链表：
 * 展开后的单链表应该同样使用 TreeNode ，其中 right 子指针指向链表中下一个结点，而左子指针始终为 null 。
 * 展开后的单链表应该与二叉树 先序遍历 顺序相同。
 * 思路：
 * 先先序遍历， 将元素放入集合中， 再遍历集合，
 * 将pre.left = null; pre.right = cur;
 *
 *
 * @author shenxie
 * @date 2023/12/16
 */
public class 二叉树展开为链表 {

    public static void main(String[] args) {
        flatten(null);
    }

    public static void flatten(TreeNode root) {
        List<TreeNode> treeNodes = new ArrayList<>();
        test(root, treeNodes);
        for(int i = 1; i<treeNodes.size(); i++) {
            TreeNode pre = treeNodes.get(i-1);
            TreeNode cur = treeNodes.get(i);
            pre.left = null;
            pre.right = cur;
        }
    }

    public static void test(TreeNode root, List<TreeNode> treeNodes){
        if(null == root) {
            return ;
        }
        treeNodes.add(root);
        test(root.left, treeNodes);
        test(root.right, treeNodes);
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

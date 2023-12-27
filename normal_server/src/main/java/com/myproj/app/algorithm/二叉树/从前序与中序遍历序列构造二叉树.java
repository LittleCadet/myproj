package com.myproj.app.algorithm.二叉树;

import java.util.HashMap;
import java.util.Map;

/**
 * 题目：
 * 给定两个整数数组 preorder 和 inorder ，其中 preorder 是二叉树的先序遍历， inorder 是同一棵树的中序遍历，请构造二叉树并返回其根节点。
 * 示例 1:
 * 输入: preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
 * 输出: [3,9,20,null,null,15,7]
 *
 * 思路：
 *  1. 递归：
 *      核心思想：   1.1 构建根节点。
 *                 1.2 构建左子树
 *                 1.3 构建右子树
 *                 1.4 递归返回的标志：前序遍历的左边界 > 前序遍历的右边界, 返回null
 *                 1.5 注意：先序遍历：在左右子树的起始点。
 *                                  左子树： 从 左边界+1 开始的 preorder_left+ size_left_subtree
 *                                          先序遍历的起点：”左边界 + 1“的原因： 先序遍历的第一个节点是根节点。
 *                                  右子树： 从 左边界+1+左子树节点数目 开始到 右边界
 * @author shenxie
 * @date 2023/12/26
 */
public class 从前序与中序遍历序列构造二叉树 {

    public static void main(String[] args) {
        TreeNode treeNode = buildTree(new int[]{3, 9, 20, 15, 7}, new int[]{9, 3, 15, 20, 7});
        System.out.println(treeNode);
    }

    static Map<Integer, Integer> map = new HashMap<>();
    public static TreeNode buildTree(int[] preorder, int[] inorder) {
        for(int i =0; i< inorder.length; i++) {
            // 用于快速找到当前节点的位置，从而定位到左子树的元素个数
            map.put(inorder[i], i);
        }
        return process(preorder, inorder, 0, inorder.length -1 , 0, inorder.length -1);
    }

    /**
     *
     * @param preorder 前序遍历
     * @param inorder   中序遍历
     * @param preorder_left 前序遍历的左边界
     * @param preorder_right  前序遍历的右边界
     * @param inorder_left  中序遍历的左边界
     * @param inorder_right 中序遍历的右边界
     * @return
     */
    public static TreeNode process(int[] preorder , int[] inorder, int preorder_left, int preorder_right, int inorder_left, int inorder_right){
        if(preorder_left > preorder_right) {
            return null;
        }
        // 构建根节点。
        int val = preorder[preorder_left];
        int root_number = map.get(val);
        TreeNode root = new TreeNode(val);
        // 找到剩余的左子树的元素个数=============重要
        int size_left_subtree = root_number - inorder_left;

        // 构建左子树
        // 先序遍历中「从 左边界+1 开始的 size_left_subtree」个元素就对应了中序遍历中「从 左边界 开始到 根节点定位-1」的元素
        // 先序遍历的起点：”左边界 + 1“的原因： 先序遍历的第一个节点是根节点。
        root.left = process(preorder, inorder,  preorder_left + 1,
                preorder_left + size_left_subtree, inorder_left, root_number -1);
        // 构建右子树
        // 先序遍历中「从 左边界+1+左子树节点数目 开始到 右边界」的元素就对应了中序遍历中「从 根节点定位+1 到 右边界」的元素
        root.right = process(preorder, inorder,  preorder_left + size_left_subtree + 1 ,
                preorder_right, root_number + 1, inorder_right);
        return root;
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

}

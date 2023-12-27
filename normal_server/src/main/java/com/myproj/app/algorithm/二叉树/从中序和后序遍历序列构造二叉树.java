package com.myproj.app.algorithm.二叉树;

import java.util.HashMap;
import java.util.Map;

/**
 * 题目：
 * 给定两个整数数组 inorder 和 postorder ，其中 inorder 是二叉树的中序遍历， postorder 是同一棵树的后序遍历，请你构造并返回这颗 二叉树 。
 * 示例 1:
 * 输入：inorder = [9,3,15,20,7], postorder = [9,15,7,20,3]
 * 输出：[3,9,20,null,null,15,7]
 *
 * 思路：
 * 1. 递归：
 *  *      核心思想：   1.1 构建根节点。
 *  *                 1.2 构建左子树
 *  *                 1.3 构建右子树
 *  *                 1.4 递归返回的标志：中序遍历的左边界 > 中序遍历的右边界, 返回null
 *                    1.5 注意：后续遍历：在左右子树的起始点
 *                                      左子树： 左子树的起点 到 左子树的起点 + size_left_subtree -1
 *                                      右子树： 左子树的起点+ size_left_subtree 到 右子树的终点 -1
 *                                              后序遍历的终点是 ： ”右子树的终点 - 1“的原因： 后续遍历， 最后一个节点是根节点。 所以 -1
 *
 * @author shenxie
 * @date 2023/12/26
 */
public class 从中序和后序遍历序列构造二叉树 {

    public static void main(String[] args) {
        TreeNode treeNode = buildTree(new int[]{9,3,15,20,7}, new int[]{9,15,7,20,3});
        System.out.println(treeNode);
    }

    static Map<Integer, Integer> map = new HashMap<>();
    public static TreeNode buildTree(int[] inorder, int[] postorder) {
        int n = inorder.length;
        for(int i = 0; i<n; i++) {
            map.put(inorder[i], i );
        }
        return process(inorder, postorder, 0, n -1 , 0, n - 1);
    }

    public static TreeNode process(int[] inorder, int[] postorder, int inorder_left, int inorder_right, int postorder_left, int postorder_right){
        if(inorder_left > inorder_right){
            return null;
        }


        int val = postorder[postorder_right];
        int root_number = map.get(val);
        // 剩余左子树的个数
        int size_left_subtree = root_number - inorder_left ;

        // 确定根节点。
        TreeNode root = new TreeNode(val);

        // 确定左子树。
        // 后序遍历的【左子树的起点 到 左子树的起点 + size_left_subtree -1】 对应中序遍历的【左子树起点 到 root_number - 1】
        root.left = process(inorder, postorder, inorder_left, root_number - 1, postorder_left ,
                postorder_left + size_left_subtree -1);

        // 确定右子树。
        // 后续遍历的【左子树的起点+ size_left_subtree 到 右子树的终点 -1】对应中序遍历的【root_number + 1 到 右子树的终点】
        // 后序遍历的终点是 ： 右子树的终点 - 1的原因： 后续遍历， 最后一个节点是根节点。 所以 -1
        root.right = process(inorder, postorder, root_number + 1, inorder_right,
                postorder_left + size_left_subtree, postorder_right -1 );

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

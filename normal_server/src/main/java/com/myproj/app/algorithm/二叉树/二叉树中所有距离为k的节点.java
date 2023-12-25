package com.myproj.app.algorithm.二叉树;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 题目：
 * 给定一个二叉树（具有根结点 root）， 一个目标结点 target ，和一个整数值 k 。
 * 返回到目标结点 target 距离为 k 的所有结点的值的列表。 答案可以以 任何顺序 返回。
 * 输入：root = [3,5,1,6,2,0,8,null,null,7,4], target = 5, k = 2
 * 输出：[7,4,1]
 * 解释：所求结点为与目标结点（值为 5）距离为 2 的结点，值分别为 7，4，以及 1
 * 思路：
 *      深度优先算法 + hash表：原因：
 *          1. 用hashMap记录每个元素的父节点。
 *          2. 深度优先算法：需要考虑的特殊点：
 *              2.1 深度优先算法： 正常情况下属于： 向下遍历。
 *              2.2 该题需要向上遍历： 通过hashMap获取当前节点的parent节点： 向上遍历。
 *              2.3 如何避免：
 *                      在向下遍历时， 不会出现向上遍历。
 *                      在向上遍历的， 不会出现向下遍历。
 *                  解法： 用from来控制即可。 from为父节点时， 代表： 向下递归。
 *                                          from为子节点时， 代表： 向上递归。
 *
 * @author shenxie
 * @date 2023/12/20
 */
public class 二叉树中所有距离为k的节点 {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        TreeNode node5 = root.left = new TreeNode(5);
        TreeNode node1 = root.right = new TreeNode(1);
        node5.left = new TreeNode(6);
        TreeNode node2 = node5.right = new TreeNode(2);
        node2.left = new TreeNode(7);
        node2.right = new TreeNode(4);
        System.out.println(distanceK(root, new TreeNode(5), 2));
        ;
    }

    static Map<Integer, TreeNode> parents = new HashMap<Integer, TreeNode>();
    static List<Integer> ans = new ArrayList<Integer>();

    public static List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        // 从 root 出发 DFS，记录每个结点的父结点
        findParents(root);

        // 从 target 出发 DFS，寻找所有深度为 k 的结点
        findAns(target, null, 0, k);

        return ans;
    }

    public static void findParents(TreeNode node) {
        if (node.left != null) {
            parents.put(node.left.val, node);
            findParents(node.left);
        }
        if (node.right != null) {
            parents.put(node.right.val, node);
            findParents(node.right);
        }
    }

    public static void findAns(TreeNode node, TreeNode from, int depth, int k) {
        if (node == null) {
            return;
        }
        if (depth == k) {
            ans.add(node.val);
            return;
        }
        if (node.left != from) {
            findAns(node.left, node, depth + 1, k);
        }
        if (node.right != from) {
            findAns(node.right, node, depth + 1, k);
        }
        if (parents.get(node.val) != from) {
            findAns(parents.get(node.val), node, depth + 1, k);
        }
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

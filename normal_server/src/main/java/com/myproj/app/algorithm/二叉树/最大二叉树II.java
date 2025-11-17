package com.myproj.app.algorithm.二叉树;

import com.myproj.app.algorithm.二叉树.抽象类.TreeNode;

/**
 * 最大树 定义：一棵树，并满足：其中每个节点的值都大于其子树中的任何其他值。
 * 给你最大树的根节点 root 和一个整数 val 。
 * 就像 之前的问题 那样，给定的树是利用 Construct(a) 例程从列表 a（root = Construct(a)）递归地构建的：
 *     如果 a 为空，返回 null 。
 *     否则，令 a[i] 作为 a 的最大元素。创建一个值为 a[i] 的根节点 root 。
 *     root 的左子树将被构建为 Construct([a[0], a[1], ..., a[i - 1]]) 。
 *     root 的右子树将被构建为 Construct([a[i + 1], a[i + 2], ..., a[a.length - 1]]) 。
 *     返回 root 。
 * 请注意，题目没有直接给出 a ，只是给出一个根节点 root = Construct(a) 。
 * 假设 b 是 a 的副本，并在末尾附加值 val。题目数据保证 b 中的值互不相同。
 * 返回 Construct(b) 。
 *
 * 示例 1：
 * 输入：root = [4,1,3,null,null,2], val = 5
 * 输出：[5,4,null,1,3,null,null,2]
 * 解释：a = [1,4,2,3], b = [1,4,2,3,5]
 *
 * 示例 2：
 * 输入：root = [5,2,4,null,1], val = 3
 * 输出：[5,2,4,null,1,null,3]
 * 解释：a = [2,1,5,4], b = [2,1,5,4,3]
 *
 *      思路：
 *          - 根据题意： 在 “b 是 a 的副本，并在末尾附加值 val”， 所以只要关注右子树即可：即为：
 *              - val > root.val 时： root为左子树
 *              - 反之： 将val插入右子树。
 *          - 与 {@link 最大二叉树} 类似：
 *              - {@link 最大二叉树II}：将入参：val 放入 已有的最大二叉树中， 使其重新变为 最大二叉树
 *              - {@link 最大二叉树}： 将数组 变成 最大二叉树
 *
 * @author shenxie
 * @date 2025/11/17
 */
public class 最大二叉树II extends TreeNode {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(1);
        root.right = new TreeNode(2);
        insertIntoMaxTree(root, 5);
    }

    public static TreeNode insertIntoMaxTree(TreeNode root, int val) {
        if (root == null)
            return new TreeNode(val);

        if (root.val < val) {
            // val > root.val时： root为左子树
            return new TreeNode(val, root, null);
        } else {
            // val < root.val时， val插入右子树： 因为“在末尾附加值val”
            root.right = insertIntoMaxTree(root.right, val);
            return root;
        }
    }
}

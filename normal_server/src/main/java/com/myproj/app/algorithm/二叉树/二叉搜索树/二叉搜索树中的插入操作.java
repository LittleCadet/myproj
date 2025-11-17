package com.myproj.app.algorithm.二叉树.二叉搜索树;

import com.myproj.app.algorithm.二分查找.检索插入位置;
import com.myproj.app.algorithm.二叉树.抽象类.TreeNode;
import com.myproj.app.algorithm.区间.插入区间;
import com.myproj.app.algorithm.链表.对链表进行插入排序;

/**
 * 给定二叉搜索树（BST）的根节点 root 和要插入树中的值 value ，将值插入二叉搜索树。 返回插入后二叉搜索树的根节点。 输入数据 保证 ，新值和原始二叉搜索树中的任意节点值都不同。
 * 注意，可能存在多种有效的插入方式，只要树在插入后仍保持为二叉搜索树即可。 你可以返回 任意有效的结果 。
 *
 *  示例 1：
 * 输入：root = [4,2,7,1,3], val = 5
 * 输出：[4,2,7,1,3,5]
 * 解释：另一个满足题目要求可以通过的树是：
 *
 * 示例 2：
 * 输入：root = [40,20,60,10,30,50,70], val = 25
 * 输出：[40,20,60,10,30,50,70,null,null,25]
 *
 * 示例 3：
 * 输入：root = [4,2,7,1,3,null,null,null,null,null,null], val = 5
 * 输出：[4,2,7,1,3,5]
 *
 *      思路：
 *          - 找到空的节点：才能插入
 *  *          - 与 {@link 检索插入位置}类似：
 *  *              - {@link 对链表进行插入排序}： 链表的节点插入： 变为有序链表
 *  *              - {@link 检索插入位置}： 将target 插入 二叉搜索树中， 返回 插入位置。
 *  *              - {@link 插入区间}: 区间插入： 变为 有序区间
 *  *              - {@link 二叉搜索树中的插入操作}： 将target 插入二叉搜索树中， 并形成节点
 *
 * @author shenxie
 * @date 2025/11/17
 */
public class 二叉搜索树中的插入操作 extends TreeNode {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(1);
        root.right = new TreeNode(4);
        root.left.right = new TreeNode(2);

        insertIntoBST(root, 5);
    }

    public static TreeNode insertIntoBST(TreeNode root, int val) {
        if (root == null) {
            return new TreeNode(val);
        }
        TreeNode pos = root;
        while (pos != null) {
            if (val < pos.val) {
                if (pos.left == null) {
                    pos.left = new TreeNode(val);
                    break;
                } else {
                    pos = pos.left;
                }
            } else {
                if (pos.right == null) {
                    pos.right = new TreeNode(val);
                    break;
                } else {
                    pos = pos.right;
                }
            }
        }
        return root;
    }
}

package com.myproj.app.algorithm.二叉树;

import com.myproj.app.algorithm.二叉树.抽象类.TreeNode;
import java.util.Deque;
import java.util.LinkedList;

/**
 * 给你一棵二叉树的根节点 root ，返回树的 最大宽度 。
 * 树的 最大宽度 是所有层中最大的 宽度 。
 * 每一层的 宽度 被定义为该层最左和最右的非空节点（即，两个端点）之间的长度。将这个二叉树视作与满二叉树结构相同，两端点间会出现一些延伸到这一层的 null 节点，这些 null 节点也计入长度。
 * 题目数据保证答案将会在  32 位 带符号整数范围内。
 *
 * 示例 1：
 * 输入：root = [1,3,2,5,3,null,9]
 * 输出：4
 * 解释：最大宽度出现在树的第 3 层，宽度为 4 (5,3,null,9) 。
 *
 *
 *      思路：
 *          - 层序遍历： 最大宽度 = Math.max(最大宽度, (右节点下标 - 左节点下标) + 1)
 *
 * @author shenxie
 * @date 2025/11/17
 */
public class 二叉树的最大宽度 extends TreeNode {

    public static void main(String[] args) {
        // 第一层
        TreeNode treeNode = new TreeNode(1);

        // 第二层
        treeNode.left = new TreeNode(3);
        treeNode.right = new TreeNode(2);

        // 第三层
        treeNode.left.left = new TreeNode(5);
        treeNode.left.right = new TreeNode(3);

        treeNode.right.right = new TreeNode(9);


        System.out.println(widthOfBinaryTree(treeNode));
    }


    /**
     * 错误做法：
     *  - 每层最大宽度：不能用 width * 2的方式， 因为这样本质是 depth * 2. 但实际上：最大宽度 = Math.max(最大宽度, (右节点下标 - 左节点下标) + 1)
     */
    public static int widthOfBinaryTree(TreeNode root) {
        int result = 1 ;
        Deque<A> stack = new LinkedList<>();
        stack.offer(new A(root, 1));
        while( ! stack.isEmpty()) {
            int size = stack.size();
            for(int i = 0 ; i < size; i++) {
                A a = stack.poll();
                if(a.node.left != null) {
                    stack.offer(new A(a.node.left, a.index * 2));
                }
                if(a.node.right != null) {
                    stack.offer(new A(a.node.right, a.index * 2 + 1));

                }
            }
            if( stack.peekLast() != null) {
                result = Math.max(result, stack.peekLast().index - stack.peekFirst().index + 1);
            }

        }

        return result;
    }

    /**
     * 正确做法： 层序遍历： 最大宽度 = Math.max(最大宽度, (右节点下标 - 左节点下标) + 1)
     */
    public static int widthOfBinaryTreeV2(TreeNode root) {
        int result = 1 ;
        Deque<A> stack = new LinkedList<>();
        stack.offer(new A(root, 1));
        while( ! stack.isEmpty()) {
            int size = stack.size();
            for(int i = 0 ; i < size; i++) {
                A a = stack.poll();
                if(a.node.left != null) {
                    stack.offer(new A(a.node.left, a.index * 2));
                }
                if(a.node.right != null) {
                    stack.offer(new A(a.node.right, a.index * 2 + 1));

                }
            }
            if( stack.peekLast() != null) {
                result = Math.max(result, stack.peekLast().index - stack.peekFirst().index + 1);
            }

        }

        return result;
    }

    public static class A{
        TreeNode node;
        int index;

        public A(TreeNode node, int index) {
            this.node = node;
            this.index = index;
        }
    }
}

package com.myproj.app.algorithm.二叉树;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 题目：
 * 给定一个二叉树：
 * struct Node {
 *   int val;
 *   Node *left;
 *   Node *right;
 *   Node *next;
 * }
 * 填充它的每个 next 指针，让这个指针指向其下一个右侧节点。如果找不到下一个右侧节点，则将 next 指针设置为 NULL 。
 * 初始状态下，所有 next 指针都被设置为 NULL 。
 * 示例 1：
 * 输入：root = [1,2,3,4,5,null,7]
 * 输出：[1,#,2,3,#,4,5,7,#]
 * 解释：给定二叉树如图 A 所示，你的函数应该填充它的每个 next 指针，以指向其下一个右侧节点，如图 B 所示。
 * 序列化输出按层序遍历顺序（由 next 指针连接），'#' 表示每层的末尾。
 *
 * 思路：
 *      1. 层序遍历：
 *          难点： 理解层序遍历的本质： 是当前层的逐个节点的遍历。
 *
 * @author shenxie
 * @date 2023/12/27
 */
public class 填充每个节点的下一个右侧节点指针II {

    public static void main(String[] args) {
        //1,2,3,4,5,null,7
        Node node = new Node(1);
        node.left = new Node(2);
        node.right = new Node(3);
        node.left.left = new Node(4);
        node.left.right = new Node(5);
        node.right.right = new Node(7);
        connect(node);
    }

    public static Node connect(Node root) {
        if(root == null) {
            return root;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        while( ! queue.isEmpty()) {
            int size = queue.size();
            Node last = null;
            boolean firstTime = true;
            while(size != 0 ){
                Node node = queue.poll();

                if(node.left != null) {
                    queue.offer(node.left);
                }
                if(node.right != null) {
                    queue.offer(node.right);
                }
                // 上一个节点的next指针 指向当前节点。
                // 层序遍历的本质： 是当前层：逐个节点的遍历。
                // 所以： 不能让node.left.next = node.right; 【不符合层序遍历的本质】
                if( ! firstTime) {
                    last.next = node;
                }
                size --;
                firstTime = false;
                last = node;
            }
        }
        return root;
    }

    static class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    };
}

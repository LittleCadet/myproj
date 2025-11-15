package com.myproj.app.algorithm.二叉树;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 给定一个 完美二叉树 ，其所有叶子节点都在同一层，每个父节点都有两个子节点。二叉树定义如下：
 * struct Node {
 *   int val;
 *   Node *left;
 *   Node *right;
 *   Node *next;
 * }
 *
 * 填充它的每个 next 指针，让这个指针指向其下一个右侧节点。如果找不到下一个右侧节点，则将 next 指针设置为 NULL。
 * 初始状态下，所有 next 指针都被设置为 NULL。
 *
 * 示例 1：
 * 输入：root = [1,2,3,4,5,6,7]
 * 输出：[1,#,2,3,#,4,5,6,7,#]
 * 解释：给定二叉树如图 A 所示，你的函数应该填充它的每个 next 指针，以指向其下一个右侧节点，如图 B 所示。序列化的输出按层序遍历排列，同一层节点由 next 指针连接，'#' 标志着每一层的结束。
 *
 * 示例 2:
 * 输入：root = []
 * 输出：[]
 *
 *      思路：
 *          - 与{@link 填充每个节点的下一个右侧节点指针II}完全相同
 *
 * @author shenxie
 * @date 2025/11/15
 */
public class 填充每个节点的下一个右侧节点指针 {

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
        if(null == root) {
            return null;
        }

        Deque<Node> queue = new LinkedList<>();
        queue.offer(root);
        while( ! queue.isEmpty()) {
            int size = queue.size();
            Node pre = null;
            for(int i = 0 ; i<size; i++) {
                Node node = queue.poll();
                if(null != node.left) {
                    queue.offer(node.left);
                }
                if(null != node.right) {
                    queue.offer(node.right);
                }
                // pre = null, 意味着： 当前node是当前层的第一个节点， 需要有前后两个节点时， 才能形成连接关系。
                if(pre != null) {
                    pre.next = node;
                }
                pre = node;
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

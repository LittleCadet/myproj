package com.myproj.app.algorithm.链表;

import java.util.HashMap;
import java.util.Map;

/**
 * 题目：
 * 给你一个长度为 n 的链表，每个节点包含一个额外增加的随机指针 random ，该指针可以指向链表中的任何节点或空节点。
 * 构造这个链表的 深拷贝。 深拷贝应该正好由 n 个 全新 节点组成，其中每个新节点的值都设为其对应的原节点的值。
 * 新节点的 next 指针和 random 指针也都应指向复制链表中的新节点，并使原链表和复制链表中的这些指针能够表示相同的链表状态。
 * 复制链表中的指针都不应指向原链表中的节点 。
 * 例如，如果原链表中有 X 和 Y 两个节点，其中 X.random --> Y 。那么在复制链表中对应的两个节点 x 和 y ，同样有 x.random --> y 。
 * 返回复制链表的头节点。
 * 用一个由 n 个节点组成的链表来表示输入/输出中的链表。每个节点用一个 [val, random_index] 表示：
 *     val：一个表示 Node.val 的整数。
 *     random_index：随机指针指向的节点索引（范围从 0 到 n-1）；如果不指向任何节点，则为  null 。
 * 你的代码 只 接受原链表的头节点 head 作为传入参数。
 * 示例 1：
 * 输入：head = [[7,null],[13,0],[11,4],[10,2],[1,0]]
 * 输出：[[7,null],[13,0],[11,4],[10,2],[1,0]]
 *
 * 思路：
 *      1. 递归 + hash表：
 *          1.1 递归： 把next.random 和 next.next分别递归 即可。
 *          1.2 hash表： 两个作用：
 *              a. 当复制到重复元素的时候， 直接返回。
 *              b. 用于返回答案： 即为：head节点，
 *
 *      2. 注意： 复制链表中的指针都不应指向原链表中的节点
 *
 * @author shenxie
 * @date 2023/12/27
 */
public class 复制带随机指针的链表 {

    public static void main(String[] args) {
        copyRandomList(null);
    }

    static Map<Node, Node> map = new HashMap<>();
    public static Node copyRandomList(Node head) {
        if(head == null) {
            return null;
        }
        if(! map.containsKey(head)){
            Node copy = new Node(head.val);
            map.put(head, copy);
            copy.next = copyRandomList(head.next);
            copy.random = copyRandomList(head.random);
        }
        return map.get(head);
    }

    /**
     * 以下方法是错误的：
     * 原因： 题目要求： 复制链表中的指针都不应指向原链表中的节点
     */
    public static Node copyRandomListV2(Node head) {
        Node node = null;
        while( head != null){
            if(null == node){
                node = new Node(head.val);
                if(null != head.random){
                    node.random = new Node(head.random.val);
                }
            }else{
                node.next = new Node(head.val);
                if(null != head.random){
                    node.random = new Node(head.random.val);
                }
            }
            head = head.next;
        }
        return node;
    }

    static class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }

}

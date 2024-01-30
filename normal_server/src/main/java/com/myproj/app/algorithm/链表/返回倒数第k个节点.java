package com.myproj.app.algorithm.链表;

/**
 * 题目：
 * 实现一种算法，找出单向链表中倒数第 k 个节点。返回该节点的值。
 * 注意：本题相对原题稍作改动
 * 示例：
 * 输入： 1->2->3->4->5 和 k = 2
 * 输出： 4
 *
 * 思路：
 *      1. dummy + 求总数
 *
 * @author shenxie
 * @date 2024/1/29
 */
public class 返回倒数第k个节点 {

    public static void main(String[] args) {

        ListNode node =  new ListNode(1);
        node.next = new ListNode(2);
        node.next.next = new ListNode(3);
        node.next.next.next = new ListNode(4);
        node.next.next.next.next = new ListNode(5);

        System.out.println(kthToLast(node, 2));
    }

    public static int kthToLast(ListNode head, int k) {
        ListNode dummy = head;
        int count = 0;
        // 对链表计数。
        while( dummy != null){
            count++;
            dummy = dummy.next;
        }
        count -=k;
        while(count-- >0){
            head = head.next;
        }
        return head.val;
    }


    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

    }
}

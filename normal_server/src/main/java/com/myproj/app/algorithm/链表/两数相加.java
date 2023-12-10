package com.myproj.app.algorithm.链表;

/**
 * 题目：
 * 给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。
 * 请你将两个数相加，并以相同形式返回一个表示和的链表。
 * 你可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 * 示例 1：
 * 输入：l1 = [2,4,3], l2 = [5,6,4]
 * 输出：[7,0,8]
 * 解释：342 + 465 = 807.
 * 示例 2：
 * 输入：l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
 * 输出：[8,9,9,9,0,0,0,1]
 *
 * 思路：
 * 1. 两数相加的本质： value = l1.val + l2.val + carry, 则新的ListNode的值为value % 10,
 *                  如果carry >0 ,则需要新的ListNode的尾部追加一个节点：值为：carry / 10
 * 2. 链表的数据结构很特殊：需要使用head 和 tail ：
 *    如果没有head / tail , 则会导致：即使链表通过tail.next = new ListNode()的方式添加N次， 最终该链表也最多只会存在2个节点。 即为首尾节点。即为：tail节点和tail.next节点。
 *    链表正确的使用姿势：
 *      第一个节点：head = tail = new ListNode();
 *      第二个节点：tail = tail.next = new ListNode();
 *      使用的时候， 从head开始。
 *
 *
 *
 * @author shenxie
 * @date 2023/12/10
 */
public class 两数相加 {
    public static void main(String[] args) {
        ListNode head , tail;
        head = tail = new ListNode(2);
        tail = tail.next = new ListNode(4);
        tail = tail.next = new ListNode(3);
        tail = tail.next = new ListNode(33);
        System.out.println(head);

        ListNode l2 = new ListNode(5);

        addTwoNumbers(tail,l2);
    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        // 两数相加的本质： l1.val + l2.val + carry, 如果carry >0 ,则需要新的ListNode的尾部追加一个节点：值为：carry / 10
        ListNode head = null, tail = null;
        int carry = 0;
        while( l1 != null || l2 != null) {
            int l1Val = l1 != null ?l1.val : 0;
            int l2Val = l2 != null ?l2.val : 0;
            int value = l1Val + l2Val + carry;
            if(head == null) {
                // 值为value对10取余数
                head = tail = new ListNode(value % 10);
            }else{
                // 值为value对10取余数
                tail = tail.next = new ListNode(value % 10);
            }

            if(l1 != null) {
                l1 = l1.next;
            }
            if(l2 != null) {
                l2 = l2.next;
            }

            // 值为value对10取模
            carry = value / 10;
        }

        // 放在while循环的外层的原因： carry > 0的情况， 需要最后处理， 即为链表的尾节点。
        if(carry > 0) {
            tail = tail.next = new ListNode(carry);
        }
        return head;
    }

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }
}

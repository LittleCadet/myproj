package com.myproj.app.algorithm_二刷.链表;

/**
 * 给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。
 * 请你将两个数相加，并以相同形式返回一个表示和的链表。
 * 你可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 *
 *
 * 示例 1：
 * 输入：l1 = [2,4,3], l2 = [5,6,4]
 * 输出：[7,0,8]
 * 解释：342 + 465 = 807.
 *
 * 示例 2：
 * 输入：l1 = [0], l2 = [0]
 * 输出：[0]
 *
 * 示例 3：
 *  输入：l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
 * 输出：[8,9,9,9,0,0,0,1]
 *
 *      思路：
 *          - 创建pre指针： 【链表问题， 如果需要返回头指针， 则一般都要创建pre指针， 最后返回 pre.next: 代表需要返回的头指针】
 *          - 问题关键是：
 *              - 考虑到节点相加时的进位问题
 *              - 相加后的数字是变更原节点，还是创建新节点：
 *                  - 最好是创建新节点， 避免更改原节点的值后， next指针紊乱。
 *              - 同时移动两个链表的指针： 在他们不为null的情况下。
 *              - 长链表 与 短链表相加的情况：
 *                  - 同时移动链表指针时， 短链表为null，需要给与默认值0.
 *
 * @author shenxie
 **/
public class 两数相加 {

    public static void main(String[] args) {
        ListNode list1 = new ListNode(1);
        list1.next = new ListNode(2);
        list1.next.next = new ListNode(3);

        ListNode list2 = new ListNode(4);
        list2.next = new ListNode(5);
        list2.next.next = new ListNode(6);

        addTwoNumbersCopy(list1, list2);
    }
    public static ListNode addTwoNumbersCopy(ListNode l1, ListNode l2) {
        ListNode pre = new ListNode(0);
        ListNode cur = pre;
        int carry = 0;
        while(l1 != null || l2 != null) {
            int x = l1 == null ? 0 : l1.val;
            int y = l2 == null ? 0 : l2.val;
            // 节点相加 = 节点相加 + 进位
            int sum = x + y + carry;
            carry = sum / 10;
            sum = sum % 10;
            // 创建cur的next节点
            cur.next = new ListNode(sum);
            // 更新cur节点： 用于下次的while循环使用。
            // 此处相当于 head 与 tail指针中： head = tail
            cur = cur.next;

            // 移动两个链表的指针
            if(null != l1) {
                l1 = l1.next;
            }
            if(null != l2) {
                l2 = l2.next;
            }

        }
        if(carry == 1) {
            cur.next = new ListNode(carry);
        }
        return pre.next;
    }


    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
}

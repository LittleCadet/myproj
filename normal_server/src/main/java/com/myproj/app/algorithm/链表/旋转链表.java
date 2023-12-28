package com.myproj.app.algorithm.链表;

/**
 * 题目：
 * 给你一个链表的头节点 head ，旋转链表，将链表每个节点向右移动 k 个位置。
 * 示例 1：
 * 输入：head = [1,2,3,4,5], k = 2
 * 输出：[4,5,1,2,3]
 *
 * 思路：
 *      1. 闭合成环：
 *          核心步骤：
 *              1.1 计算链表元素个数。
 *              1.2 闭合成环。
 *              1.3 找到要断开的元素位置， 并移动。
 *                  int move = total - k % total;
 *              1.4 将node的下一个元素 赋值给新链表。
 *              1.5 断开环。
 *              1.6 返回新链表。
 *
 * @author shenxie
 * @date 2023/12/28
 */
public class 旋转链表 {

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        rotateRight(head, 2);
    }

    public static ListNode rotateRight(ListNode head, int k) {
        if(head == null) {
            return head;
        }
        int total = 1;
        ListNode node = head;
        // 计算链表的元素个数
        while(node.next != null) {
            total ++;
            node = node.next;
        }
        // 链表闭合成环。
        node.next = head;
        // 找到要断开的位置。
        int move = total - k % total;
        // 移动链表move次，且从当前链表的最后一个元素开始移动。
        while(move -- > 0) {
            node = node.next;
        }
        // 断开环。
        // 这里使用：ans作为答案， 而不是head的原因： head自始至终没有移动， 只是成闭环了而已。所以断开时， head断开的是当前节点， 但是由于未移动，所以不行。
        // node不是答案的原因： 作为操作链表， 一直在移动， 所以一旦断开， 代表node此刻在链表尾部。
        // ans能作为答案的原因： ans属于node移动以后的结果， 是一个闭环， 所以一旦断开， ans就是移动后的结果。
        // ans = node.next的原因： node是移动后的节点， 而对于返回的最终答案而言： 首节点是node.next.
        ListNode ans = node.next;
        node.next = null;
        return ans;
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}

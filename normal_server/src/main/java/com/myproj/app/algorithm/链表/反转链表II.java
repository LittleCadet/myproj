package com.myproj.app.algorithm.链表;

/**
 * 题目：
 * 给你单链表的头指针 head 和两个整数 left 和 right ，其中 left <= right 。请你反转从位置 left 到位置 right 的链表节点，返回 反转后的链表 。
 * 示例 1：
 * 输入：head = [1,2,3,4,5], left = 2, right = 4
 * 输出：[1,4,3,2,5]
 *
 * 思路：
 *      1. 穿针引线：
 *          1.1 链表元素的调换： 涉及到三个元素：pre + cur + next.
 *          1.2 pre节点：永远是left的前一个元素。
 *          1.3 cur节点：是left的第一个元素。
 *          1.4 next节点： 永远是cur的下一个元素。
 *          1.5 穿针引线三大步： 记住图：https://leetcode.cn/problems/reverse-linked-list-ii/solutions/634701/fan-zhuan-lian-biao-ii-by-leetcode-solut-teyq/?envType=study-plan-v2&envId=top-interview-150
 *              a. 执行操作 ①：把 curr 的下一个节点指向 next 的下一个节点；
 *              b. 执行操作 ②：把 next 的下一个节点指向 pre 的下一个节点；
 *              c. 执行操作 ③：把 pre 的下一个节点指向 next。
 *
 * @author shenxie
 * @date 2023/12/27
 */
public class 反转链表II {

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        reverseBetween(head, 2,4);
    }

    public static ListNode reverseBetween(ListNode head, int left, int right) {
        // 设置 dummyNode 是这一类问题的一般做法
        ListNode dummyNode = new ListNode(-1);
        dummyNode.next = head;
        ListNode pre = dummyNode;
        // 找到pre的节点： 逐个next的原因： 链表找元素只能逐个next.
        for (int i = 0; i < left - 1; i++) {
            pre = pre.next;
        }
        // 找到当前节点
        ListNode cur = pre.next;
        ListNode next;
        for (int i = 0; i < right - left; i++) {
            // 找到next节点。
            next = cur.next;
            // 穿针引线三大步：记住图
            cur.next = next.next;
            next.next = pre.next;
            // pre.next 永远指向next
            pre.next = next;
        }
        return dummyNode.next;
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

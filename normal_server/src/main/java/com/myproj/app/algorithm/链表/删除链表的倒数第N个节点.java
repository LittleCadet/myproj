package com.myproj.app.algorithm.链表;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 题目：
 * 给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。
 * 示例 1：
 * 输入：head = [1,2,3,4,5], n = 2
 * 输出：[1,2,3,5]
 * <p>
 * 思路：
 * 1. 方法1：dummy节点 + 计算链表长度：
 *      1.1 需要dummy节点的原因： 链表很特殊， 遍历只能通过cur = cur.next的方式， 所以如果需要返回完整的head节点， 需要用dummy节点。
 *          即为ListNode cur = dummy； 让cur和dummy共享同一个heap位置， 修改节点时， 会同时生效，
 *          但唯一不同的是：cur只是用来遍历或者查找节点的， 而真正返回的是dummy.next节点。
 *  2. 方法2：dummy节点 + 栈：
 *          栈原理： 将dummy的每个节点推入stack中， 则倒数第N个节点， 即为出栈的第N个节点。
 *                  之后再取出栈顶节点：prev.next = prev.next.next; 即可。
 *  3. 方法3：dummy节点 + 双指针：
 *          双指针原理： 让first指针领先second指针n个位置， 这样first指到null时【即为链表最后一个节点的下一个节点】，
 *                   second刚好在倒数n个节点的前一个节点。 再通过删除第n个节点即可。 second.next = second.next.next;
 *
 * @author shenxie
 * @date 2023/12/28
 */
public class 删除链表的倒数第N个节点 {

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        // 方法一：
//        ListNode listNode = removeNthFromEnd(head, 2);
        // 方法2：
        ListNode listNode = removeNthFromEndV2(head, 2);
        // 方法3
//        ListNode listNode = removeNthFromEndV3(head, 2);
    }

    /**
     * dummy节点 + 计算链表长度
     */
    public static ListNode removeNthFromEnd(ListNode head, int n) {
        // 难点： 不知道链表：共有多少个节点。
        // 不能声明数组： 因为不知道数组的大小。
        int count = 1;
        int position = count(head) - n;
        ListNode dummy = new ListNode(0, head);
        ListNode cur = dummy;
        // 如何确保head向前移动，且能存储原有节点。
        // 找到当前节点
        for (int i = 0; i <= position; i++) {
            cur = cur.next;
        }
        // 移除倒数n个的节点
        cur.next = cur.next.next;
        // 返回dummy.next 而不是cur.next的原因：
        // 虽然dummy 与 cur是在heap的同一位置，修改数据对于cur和dummy都会生效，
        // 但是不同的是：cur移动过【cur.next】，所以cur为下一个节点值， 但是dummy没有移动过。
        return dummy.next;
    }

    /**
     * dummy节点 + 栈：
     * deque很特殊： 既可以作为FIFO的链表， 也可以作为FILO的栈。 元素的具体放入与取出的行为， 看对应的方法。 eg:
     * FIFO:
     *  offer() + poll() + offerLast() ;
     * FILO：
     *  push() + pop() + peek();
     *
     */
    public static ListNode removeNthFromEndV2(ListNode head, int n) {
        ListNode dummy = new ListNode(0, head);
        Deque<ListNode> stack = new LinkedList<ListNode>();
        ListNode cur = dummy;
        while (cur != null) {
            // 逐个将cur的节点推入栈中， 但是：cur的每一个节点都是包含当前节点及以后的所有节点。
            // 所以对于栈而言， 每个节点， 都包含当前节点和以后的所有节点。
            stack.push(cur);
            cur = cur.next;
        }
        for (int i = 0; i < n; ++i) {
            stack.pop();
        }
        // 取出栈顶的第一个元素。
        ListNode prev = stack.peek();
        // 因为栈的每个节点， 都包含当前解和以后的所有节点， 所以可以这么做：prev.next = prev.next.next;
        // 虽然是栈中的元素， 但是这些节点在heap中的位置没有发生变更， 所以对于cur, dummy, stack都是同时生效的。
        // 所以dummy.next就是答案。
        prev.next = prev.next.next;
        return dummy.next;
    }

    /**
     * dummy节点 + 双指针。
     * 双指针原理： 让first指针领先second指针n个位置， 这样first指到null时【即为链表最后一个节点的下一个节点】，
     *              second刚好在倒数n个节点的前一个节点。 再通过删除第n个节点即可。 second.next = second.next.next;
     */
    public static ListNode removeNthFromEndV3(ListNode head, int n) {
        ListNode dummy = new ListNode(0, head);
        ListNode first = head;
        // 原因： first指针：需要从第1个节点， 移动到第null个节点【链表最后一个节点的下一个节点】。
        // 所以second指针： 需要从第0个节点， 移动到 链表的最后一个节点。
        ListNode second = dummy;
        // 让first领先second指针n个位置。
        for(int i = 0; i< n ; i++) {
            first = first.next;
        }
        // 当first指针移动到null时， 代表： second已经移动到n-1的位置。
        // 因为first领先second指针n个位置。
        while(first != null) {
            first = first.next;
            second = second.next;
        }
        // 移除倒数第n个节点。
        second.next = second.next.next;
        return dummy.next;
    }


    public static int count(ListNode head) {
        int count = 0;
        while (head.next != null) {
            count++;
            head = head.next;
        }
        return count;
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

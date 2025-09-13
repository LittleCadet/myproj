package com.myproj.app.algorithm_二刷.链表;

/**
 * 给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。
 * 示例 1：
 * 输入：head = [1,2,3,4,5], n = 2
 * 输出：[1,2,3,5]
 *
 * 示例 2：
 * 输入：head = [1], n = 1
 * 输出：[]
 *
 * 示例 3：
 * 输入：head = [1,2], n = 1
 * 输出：[1]
 *
 *      思路：
 *          - 创建dummy节点 【即为 pre 节点】： 注意dummy节点的创建方式。
 *          - 变倒数第N个节点，变成整数 count - N + 1个节点。
 *
 * @author shenxie
 **/
public class 删除链表的倒数第N个节点 {

    public static void main(String[] args) {
        ListNode list1 = new ListNode(1);
        list1.next = new ListNode(2);
        list1.next.next = new ListNode(3);
        removeNthFromEndV2(list1, 1);
    }

    /**
     * 这种做法是错误的：
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode pre1 = head;
        ListNode pre2 = head;
        int count = 0;
        while(null != head) {
            count++;
            head = head.next;
        }
        int rest = count - n;
        count = 0 ;
        while(null != pre2) {
            // 错误原因：如果head只有1个节点时， 那么此时 ++ count为1 ， rest为0. 则他们永远不会相等。 永远不能删除那唯一的节点。
            if( ++ count == rest) {
                pre2.next = pre2.next.next;
            }
            pre2 = pre2.next;
        }
        return pre1;
    }

    public static ListNode removeNthFromEndV2(ListNode head, int n) {
        ListNode dummy = new ListNode(0, head);
        ListNode cur = dummy;
        int count = 0;
        while(null != head) {
            count++;
            head = head.next;
        }
        int rest = count - n + 1;
        for(int i = 1; i < rest ; i++) {
            cur = cur.next;
        }
        // 这里不会出现空指针： 原因： cur 是 dummy节点的化身。 第一个元素是伪造的。所以cur.next一定不会空。
        cur.next = cur.next.next;
        return dummy.next;
    }

    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
}

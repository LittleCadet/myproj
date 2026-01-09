package com.myproj.app.algorithm.链表;

import com.myproj.app.algorithm.链表.抽象类.ListNode;

/**
 * 给你单链表的头节点 head ，请你反转链表，并返回反转后的链表。
 *
 * 示例 1：
 * 输入：head = [1,2,3,4,5]
 * 输出：[5,4,3,2,1]
 *
 * 示例 2：
 * 输入：head = [1,2]
 * 输出：[2,1]
 *
 * 示例 3：
 * 输入：head = []
 * 输出：[]
 *
 *      思路：
 *          - 方法1： 迭代：{@link 反转链表II}的反转链表的子区间的做法完全相同。
 *  *              注意： 与 {@link 对链表进行插入排序} 不同：
 *  *                  - 本题： 强调 链表反转： 对于链表而言： 是递归调整
 *  *                  - 后者： 强调 插入顺序的调整: 对于一个插入元素而言： 是一次性调整。
 *          - 方法2： 递归：后续遍历
 *
 *
 * @author shenxie
 * @date 2025/11/11
 */
public class 反转链表 extends ListNode {
    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);

        reverseList(head);
    }

    /**
     * 方法1： 迭代
     */
    public static ListNode reverseList(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;
        while(cur != null) {
            // 本质是pre 和 cur 的两两交换：
            // 1 => 2 => 3 转变为 3 => 2 => 1
            ListNode next = cur.next;
            // >>>>交换开始
            cur.next = pre;
            pre = cur;
            // >>>>交换结束
            cur = next;
        }
        // 不能返回head, 因为在第一次经过：cur.next = pre时， head.next就已经为null 了;
        // 即为head的val 为 1，  next为null;
        return pre;
    }


    /**
     * 方法2： 递归：后续遍历： 即为在已经知道所有节点的前提下， head.next = pre;
     */
    public static ListNode reverseListV2(ListNode head) {

        return reverse(head, null);
    }

    private static ListNode reverse(ListNode head , ListNode pre) {
        if(head == null) {
            return pre;
        }
        ListNode node = reverse(head.next, head);
        head.next = pre;
        return node;
    }
}

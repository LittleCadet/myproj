package com.myproj.app.algorithm.链表;

import java.util.HashSet;
import java.util.Set;

/**
 * 题目：
 * 给定一个链表的头节点  head ，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。
 * 如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，则链表中存在环。 为了表示给定链表中的环，
 * 评测系统内部使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。如果 pos 是 -1，则在该链表中没有环。
 * 注意：pos 不作为参数进行传递，仅仅是为了标识链表的实际情况。
 * 不允许修改 链表。
 * 示例 1：
 * 输入：head = [3,2,0,-4], pos = 1
 * 输出：返回索引为 1 的链表节点
 * 解释：链表中有一个环，其尾部连接到第二个节点。
 *
 * 思路：
 *      1. 方法1： hashSet。
 *      2. 方法2： 快慢指针：
 *          2.1 slow = fast: 代表有环。
 *          2.2 入环节点：在head = slow时， 返回head即可
 *
 * @author shenxie
 * @date 2024/1/23
 */
public class 环形链表II {

    public static void main(String[] args) {
        ListNode node = new ListNode(3);
        node.next = new ListNode(2);
        node.next.next = new ListNode(0);
        node.next.next.next = new ListNode(-4, node.next);

//        System.out.println(detectCycle(node));
        System.out.println(detectCycleV2(node));
    }

    /**
     * 方法1：hashSet
     */
    public static ListNode detectCycle(ListNode head) {
        Set<ListNode> set = new HashSet<>();
        while(head != null) {
            if( ! set.add(head)) {
                return head;
            }
            head = head.next;
        }
        return null;
    }

    /**
     * 方法2：快慢指针
     */
    public static ListNode detectCycleV1(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode slow = head, fast = head;
        while (fast != null) {
            slow = slow.next;
            if (fast.next != null) {
                fast = fast.next.next;
            } else {
                return null;
            }
            // fast == slow 代表： 链表有环
            if (fast == slow) {
                ListNode ptr = head;
                // 从ptr开始重新循环，
                while (ptr != slow) {
                    ptr = ptr.next;
                    slow = slow.next;
                }
                return ptr;
            }
        }
        return null;
    }

    /**
     * 错误写法： 一定不能写成 ：因为： 这样： 代表： fast永远比slow快一个节点。 而实际要达到的效果是： fast的速度是slow的2倍。
     * ListNode slow = head;
     * ListNode fast = head.next;
     * @return
     */
    public static ListNode detectCycleV2(ListNode head) {
        if(null == head) {
            return null;
        }
        // 代表： fast永远比slow快一个节点
        ListNode slow = head;
        ListNode fast = head.next;
        while(fast != null) {
            if(slow.val == fast.val) {
                ListNode A = head;
                while(A.val != slow.val) {
                    A = A.next;
                    slow = slow.next;
                }
                return A;
            }

            slow = slow.next;
            fast = fast.next;

        }
        return null;
    }

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }

        ListNode(int x, ListNode node) {
            val = x;
            next = node;
        }
    }

}

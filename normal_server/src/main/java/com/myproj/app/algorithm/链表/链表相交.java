package com.myproj.app.algorithm.链表;

import java.util.HashSet;

/**
 * 题目：
 * 给你两个单链表的头节点 headA 和 headB ，请你找出并返回两个单链表相交的起始节点。如果两个链表没有交点，返回 null 。
 * 图示两个链表在节点 c1 开始相交：
 * 题目数据 保证 整个链式结构中不存在环。
 * 注意，函数返回结果后，链表必须 保持其原始结构 。
 * 示例 1：
 * 输入：intersectVal = 8, listA = [4,1,8,4,5], listB = [5,0,1,8,4,5], skipA = 2, skipB = 3
 * 输出：Intersected at '8'
 * 解释：相交节点的值为 8 （注意，如果两个链表相交则不能为 0）。
 * 从各自的表头开始算起，链表 A 为 [4,1,8,4,5]，链表 B 为 [5,0,1,8,4,5]。
 * 在 A 中，相交节点前有 2 个节点；在 B 中，相交节点前有 3 个节点。
 *
 * 思路：
 *      方法1： hashset:
 *          1. 注意： 泛型一定是ListNode, 而不是Integer，
 *              因为： 链表的节点相交， 代表： headA = headB 【因为链表含有next指针， 所以泛型一定是ListNode】
 *      方法2： 双指针：
 *          1. 核心思想：【用几个例子在稿纸 尝试下， 就知道了。 】
 *              从数学的角度看：链表 headA 和 headB 的长度分别是 m 和 n。
 *              情况一：两个链表相交
 *                  headA【m + n】时， 一定 会与headB【n + m】时， 相交。
 *
 *              情况二：两个链表不相交
 *                  headA【m + n】时， 一定 不会与headB【n + m】时， 相交。
 *
 * @author shenxie
 * @date 2024/1/30
 */
public class 链表相交 {

    public static void main(String[] args) {
        ListNode headA = new ListNode(4);
        headA.next = new ListNode(1);
        headA.next.next = new ListNode(8);
        headA.next.next.next = new ListNode(4);

        ListNode headB = new ListNode(5);
        headB.next = new ListNode(0);
        headB.next.next = new ListNode(1);
        headB.next.next.next = new ListNode(8);
        headB.next.next.next.next = new ListNode(4);

        System.out.println(getIntersectionNodeV1(headA, headB));
        System.out.println(getIntersectionNodeV2(headA, headB));
    }

    /**
     * 方法1： HashSet
     */
    public static ListNode getIntersectionNodeV1(ListNode headA, ListNode headB) {
        HashSet<ListNode> set = new HashSet<>();
        while(headA != null) {
            set.add(headA);
            headA = headA.next;
        }
        while(headB != null) {
            if(set.contains(headB)){
                return headB;
            }
            headB = headB.next;
        }
        return null;
    }

    /**
     * 方法2： 双指针
     */
    public static ListNode getIntersectionNodeV2(ListNode headA, ListNode headB) {
        if(null == headA || null == headB) {
            return null;
        }
        ListNode pA = headA, pB = headB;
        while(pA != pB) {
            // 注意： 节点为null时， 让它从对方的头结点开始。
            pA = pA == null ? headB : pA.next;
            pB = pB == null ? headA : pB.next;
        }

        return pA;
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
        }

    }
}

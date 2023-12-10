package com.myproj.app.algorithm.链表;

/**
 * 题目：
 * 将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
 * 示例 1：
 * 输入：l1 = [1,2,4], l2 = [1,3,4]
 * 输出：[1,1,2,3,4,4]
 * 示例 2：
 * 输入：l1 = [], l2 = []
 * 输出：[]
 * 示例 3：
 * 输入：l1 = [], l2 = [0]
 * 输出：[0]
 *
 * 思路：
 * 1. 递归：
 *      跳出递归的条件： 两个链表任意一个链表为null 则返回。
 *      递归： 两个链表的值 比较大小的时候， 递归， 并在递归完成时， 赋值即可。
 *
 *
 * @author shenxie
 * @date 2023/12/10
 */
public class 合并两个有序链表 {

    public static void main(String[] args) {
        ListNode head1 , tail1;
        head1 = tail1 = new ListNode(1);
        tail1 = tail1.next = new ListNode(2);
        tail1 = tail1.next = new ListNode(4);

        ListNode head2 , tail2;
        head2 = tail2 = new ListNode(1);
        tail2 = tail2.next = new ListNode(3);
        tail2 = tail2.next = new ListNode(4);

        ListNode node = mergeTwoLists(head1,head2);
        System.out.println(node);
    }

    public static ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        // 2个链表的元素逐一比较， 按照升序形成新链表。 
        // 原链表的元素为空时， val = 0;

        if(list1 == null) {
            return list2;
        }else if(list2 == null) {
            return list1;
        }else if (list1.val > list2.val) {
            // 在这里完成 递归的结果的赋值动作
            list2.next = mergeTwoLists(list1, list2.next);
            return list2;
        }else{
            // 在这里完成 递归的结果的赋值动作
            list1.next = mergeTwoLists(list1.next, list2);
            return list1;
        }
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

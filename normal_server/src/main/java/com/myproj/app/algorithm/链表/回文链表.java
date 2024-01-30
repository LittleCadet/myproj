package com.myproj.app.algorithm.链表;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 题目：
 * 编写一个函数，检查输入的链表是否是回文的。
 * 示例 1：
 * 输入： 1->2->2->1
 * 输出： true
 *
 * 思想：
 *      1. 正确方法： ArrayList, 配合 Collections.reverse()， 最后用list1.equals(list2) 判定是否是回文系列
 *      2. 错误方法： 对于StringBuilder: 不能用于数字， 只能用于字母。
 * @author shenxie
 * @date 2024/1/30
 */
public class 回文链表 {

    public static void main(String[] args) {
        ListNode head = new ListNode(-129);
        head.next = new ListNode(-129);
        System.out.println(isPalindrome(head));
    }

    /**
     * 错误做法： 回文系列： 对于StringBuilder: 不能用于数字， 只能用于字母。
     * 因为： 【129, 129】， reverse()后为[921,921]， 会判定为false, 但是回文链表。
     *
     *
     */
    public static boolean isPalindrome(ListNode head) {
        StringBuilder builder = new StringBuilder();
        while(head != null) {
            builder.append(Math.abs(head.val) );
            head = head.next;
        }
        return builder.toString().equals(builder.reverse().toString());

    }

    /**
     * 方法1： Collections.reverse()
     */
    public static boolean isPalindromeV2(ListNode head) {
        List<Integer> l1 = new ArrayList<>();
        List<Integer> l2 = new ArrayList<>();
        while(head != null) {
            l1.add(head.val);
            l2.add(head.val);
            head = head.next;
        }
        Collections.reverse(l2);
        return l1.equals(l2);
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

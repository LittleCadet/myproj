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
 *      1. 方法1： ArrayList, 配合 Collections.reverse()， 最后用list1.equals(list2) 判定是否是回文系列
 *      2. 方法2： StringBuilder.reverse();
 * @author shenxie
 * @date 2024/1/30
 */
public class 回文链表 {

    public static void main(String[] args) {
        ListNode head = new ListNode(-129);
        head.next = new ListNode(-129);
        System.out.println(isPalindrome(head));
        System.out.println(isPalindromeV2Copy(head));
    }

    /**
     * 方法2： StringBuilder.reverse();
     *
     */
    public static boolean isPalindrome(ListNode head) {
        StringBuilder builder = new StringBuilder();
        while(head != null) {
            builder.append(head.val);
            head = head.next;
        }
        return builder.toString().equals(builder.reverse().toString());

    }

    /**
     * 方法1： Collections.reverse()
     */
    public static boolean isPalindromeV2Copy(ListNode head) {
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

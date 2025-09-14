package com.myproj.app.algorithm_二刷.链表;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 给你一个单链表的头节点 head ，请你判断该链表是否为
 * 。如果是，返回 true ；否则，返回 false 。
 * 示例 1：
 * 输入：head = [1,2,2,1]
 * 输出：true
 *
 * 示例 2：
 * 输入：head = [1,2]
 * 输出：false
 *
 * 思路：
 *      - 回文序列：即为前后相应位置的元素的值相等。
 *      - 方法1：数组 + 首尾双指针
 *          -- 时间复杂度： 0(N)
 *          -- 空间复杂度： 0(N)
 *      - 方法2：数组 + Collections.reverse();
 *      - 方法3：递归 【模拟首尾双指针】
 *          -- 时间复杂度： 0(N)
 *          -- 空间复杂度： 0(N)
 * @author shenxie
 **/
public class 回文链表 {
    static ListNode front;

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(2);
        head.next.next.next = new ListNode(1);
        System.out.println(isPalindromeV1(head));
        System.out.println(isPalindromeV2Copy(head));
        System.out.println(isPalindromeV3(head));
    }

    /**
     * 方法1： 数组 + 双指针
     */
    public static boolean isPalindromeV1(ListNode head) {
        List<Integer> nodes = new ArrayList<>();
        ListNode node = head;
        // 将链表元素放入数组中
        while(node != null) {
            nodes.add(node.val);
            node = node.next;
        }
        int left = 0;
        int right = nodes.size() - 1;

        // 判定是否是回文序列
        while(left < right){
            if(  ! nodes.get(left).equals(nodes.get(right))) {
                return false;
            }
            left ++;
            right --;
        }
        return true;
    }

    /**
     * 方法2： Collections.reverse()
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

    public static boolean isPalindromeV3(ListNode head) {
        front = head;
        return checkCopy(head);
    }

    /**
     * 方法3： 递归
     */
    public static boolean checkCopy(ListNode last) {
        if(last != null) {
            if( ! checkCopy(last.next)) {
                return false;
            }
            // 能够进入这一行， 代表：此时的入参是最后一个节点。
            // 判定是否是回文序列：第一个节点 与 最后一个节点相比
            if(last.val != front.val) {
                return false;
            }

            // 此时的front是第一个节点。
            front = front.next;
        }
        return true;
    }

    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
}

package com.myproj.app.algorithm_二刷.链表;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *给你链表的头结点 head ，请将其按 升序 排列并返回 排序后的链表 。
 * 示例 1：
 * 输入：head = [4,2,1,3]
 * 输出：[1,2,3,4]
 *
 * 示例 2：
 * 输入：head = [-1,5,3,4,0]
 * 输出：[-1,0,3,4,5]
 *
 * 示例 3：
 * 输入：head = []
 * 输出：[]
 *
 *
 *      思路： 数组 + dummy节点：
 *          - 核心思想： 链表跟数组 【List】非常类似： 只不过链表有next指针
 *          - 特殊API的使用：Collections.sort(nums);  【记住不是Collectors】
 *              -- Collectors: 是stream的API
 *              -- Collections: 是集合的API.
 * @author shenxie
 **/
public class 排序链表 {

    public static void main(String[] args) {
        ListNode list1 = new ListNode(1);
        list1.next = new ListNode(2);
        list1.next.next = new ListNode(3);
        list1.next.next.next = new ListNode(4);

        sortList(list1);
    }

    public static ListNode sortList(ListNode head) {
        List<Integer> nums = new ArrayList<>();
        ListNode dummy = new ListNode(0);
        ListNode tmp = dummy;
        // 填充List
        while(null != head) {
            nums.add(head.val);
            head = head.next;
        }
        // 排序
        Collections.sort(nums);
        // 填充dummy节点
        for(int num : nums) {
            tmp.next = new ListNode(num);
            tmp = tmp.next;
        }
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

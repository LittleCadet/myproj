package com.myproj.app.algorithm_二刷.链表;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
 * 示例 1：
 * 输入：l1 = [1,2,4], l2 = [1,3,4]
 * 输出：[1,1,2,3,4,4]
 *
 * 示例 2：
 * 输入：l1 = [], l2 = []
 * 输出：[]
 *
 * 示例 3：
 * 输入：l1 = [], l2 = [0]
 * 输出：[0]
 *
 *      思路：
 *          - 方法1： 递归：两个链表相比较， 值小的链表需要移动到下一个元素， 而值大的链表不动。 直到值小的链表为null时返回， 且更新值小的链表 即可。
 *                  注意：两个链表在不断的比较中： 值小的链表 和 值大的链表， 会不停地切换。【eg: A链表的值， 不可能永远比B链表的值小。】
 *
 *          - 方法2： list 排序， 将list元素放入 dummy节点中。
 *
 * @author shenxie
 **/
public class 合并两个有序链表 {

    public static void main(String[] args) {
        ListNode list1 = new ListNode(1);
        list1.next = new ListNode(2);
        list1.next.next = new ListNode(3);

        ListNode list2 = new ListNode(4);
        list2.next = new ListNode(5);
        list2.next.next = new ListNode(6);

        mergeTwoListsCopy(list1, list2);
    }

    /**
     * 方法1： 递归：
     */
    public static ListNode mergeTwoListsCopy(ListNode list1, ListNode list2) {
        if(null == list1) {
            return list2;
        }
        if(null == list2) {
            return list1;
        }
        if(list1.val < list2.val) {
            // 入参可以是：list1.next + list2
            // 也可以是： list2 + list1.next
            // 不重要， 只需要传入当前两个链表即可， 因为：题目没有要求当值相等时， 必须要用哪个链表的值。
            list1.next = mergeTwoListsCopy(list1.next, list2);
            return list1;
        }else{
            // 入参可以是：list2.next + list1
            // 也可以是： list1 + list2.next
            // 不重要， 只需要传入当前两个链表即可， 因为：题目没有要求当值相等时， 必须要用哪个链表的值。
            list2.next = mergeTwoListsCopy(list2.next, list1);
            return list2;
        }

    }


    /**
     * 方法2： list 排序， 将list元素放入 dummy节点中。
     */
    public ListNode mergeTwoListsV2Copy(ListNode list1, ListNode list2) {
        List<Integer> nums = new ArrayList<>();
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        while(list1 != null) {
            nums.add(list1.val);
            list1 = list1.next;
        }
        while(list2 != null) {
            nums.add(list2.val);
            list2 = list2.next;
        }
        Collections.sort(nums);

        for(Integer num : nums) {
            cur.next = new ListNode(num);
            cur = cur.next;
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

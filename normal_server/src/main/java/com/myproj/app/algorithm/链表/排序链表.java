package com.myproj.app.algorithm.链表;

import com.myproj.app.algorithm.链表.抽象类.ListNode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 给定链表的头结点 head ，请将其按 升序 排列并返回 排序后的链表 。
 *
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
 *  思路：
 *      - ArrayList
 * @author shenxie
 * @date 2025/11/3
 */
public class 排序链表 extends ListNode {

    public static void main(String[] args) {
        ListNode head = new ListNode(3);
        head.next = new ListNode(2);
        head.next.next = new ListNode(1);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        sortList(head);
    }

    public static ListNode sortList(ListNode head) {
        List<Integer> list = new ArrayList<>();
        ListNode dummy = new ListNode();
        ListNode cur = dummy;
        while(head != null) {
            list.add(head.val);
            head = head.next;
        }
        Collections.sort(list);

        for(Integer num:list) {
            cur.next = new ListNode(num);
            cur = cur.next;
        }
        return dummy.next;
    }

}

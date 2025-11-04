package com.myproj.app.algorithm.链表;

import com.myproj.app.algorithm.链表.抽象类.ListNode;
import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个单链表 L 的头节点 head ，单链表 L 表示为：
 *  L0 → L1 → … → Ln-1 → Ln
 * 请将其重新排列后变为：
 * L0 → Ln → L1 → Ln-1 → L2 → Ln-2 → …
 * 不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 *
 * 示例 1：
 * 输入: head = [1,2,3,4]
 * 输出: [1,4,2,3]
 *
 * 示例 2：
 * 输入: head = [1,2,3,4,5]
 * 输出: [1,5,2,4,3]
 *
 *      思路：
 *          - 线性表：
 *              因为链表无法支持下标访问， 所以借助 ArrayList 可以完成。
 *
 *
 *
 * @author shenxie
 * @date 2025/11/3
 */
public class 重排链表 extends ListNode {

    public static void main(String[] args) {

    }

    public void reorderList(ListNode head) {
        ListNode dummy = new ListNode(0,head);
        ListNode cur = dummy.next;
        List<ListNode> list = new ArrayList<>();

        boolean reverse = true;
        while(cur != null) {
            list.add(cur);
            cur = cur.next;
        }

        int i = 0 , j = list.size() -1;

        while(i < j) {
            // 将 L0 => Ln
            list.get(i).next = list.get(j);
            i++;
            if(i == j) {
                break;
            }

            // 将 Ln => L1
            list.get(j).next = list.get(i);
            j--;
        }

        // 必须要有： 不然链表成环。
        list.get(i).next = null;

    }



}

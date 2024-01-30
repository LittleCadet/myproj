package com.myproj.app.algorithm.链表;

import java.util.HashSet;
import java.util.Set;

/**
 * 题目：
 * 编写代码，移除未排序链表中的重复节点。保留最开始出现的节点。
 * 示例1:
 *  输入：[1, 2, 3, 3, 2, 1]
 *  输出：[1, 2, 3]
 *
 * 思路：
 *      1. 方法1： dummy节点 + hashSet：
 *          1.1 核心点： 链表去重： 当前节点： 必须用dummy.next来表示， 而不是dummy.
 *                      因为： 去重必定需要： dummy.next = dummy.next.next
 *                              如果是dummy = dummy.next： 仅仅代表： 正常递归。
 *      2. 方法2： 双重while循环： 类似数组的双重for循环， 且内层循环起点为外层循环的值
 *          2.1 实则： 双指针： 慢指针： 在外， 快指针： 在内， 且： 让快指针的每个值 都与 慢指针比较 即可。
 *          2.2 启发： 如何控制链表：多次从头循环。 用双重for循环来解决 即可！！！
 *
 * @author shenxie
 * @date 2024/1/24
 */
public class 移除重复节点 {

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(3);
        head.next.next.next.next = new ListNode(2);
        head.next.next.next.next.next = new ListNode(1);
//        removeDuplicateNodes(head);
        removeDuplicateNodesV2(head);
//        removeDuplicateNodesV3(head);
    }

    /**
     * 该方法：错误： 原因： 当前节点用dummy来表示。 而不是dummy.next
     */
    public static ListNode removeDuplicateNodes(ListNode head) {
        if(null == head) {
            return head;
        }
        ListNode dummy = head;
        HashSet<Integer> set = new HashSet<>();
        while(dummy != null) {
            if( ! set.add(dummy.val) ) {
                // 不能这么写， 原因： 当前节点是dummy， 且当前节点重复了，要吧当前节点去除。
                // 所以dummy.next = dummy.next.next不对。
                // 如果写成dummy = dummy.next： 则只是正常的递归。
                // 所以想要去重： 只能从上一个节点开始计算。即为： removeDuplicateNodesV2().
                dummy.next = dummy.next.next;
            }else{
                dummy = dummy.next;
            }
        }
        return head;
    }

    /**
     * 方法1： 当前节点用dummy.next来表示。
     */
    public static ListNode removeDuplicateNodesV2(ListNode head) {
        if (head == null) {
            return head;
        }
        // 泛型只能用Integer, 不能用ListNode： 原因： 链表的任何一个节点都包含对下一个节点的引用【即为next指针】，
        // 所以如果泛型是ListNode，则val相同时， 也不会是同一个节点。
        ListNode pre = null, cur = head;
        HashSet<Integer> set = new HashSet<>();
        // 删除节点的2种方式：
        // 方法1： pre.next = cur.next;
        // 方法2： 见《删除中间节点》： 删除当前节点：
        //        node.val = node.next.val;
        //        node.next = node.next.next;
        while (cur != null) {
            if(set.add(cur.val)){
                pre = cur;
            }else{
                pre.next = cur.next;
            }
            cur = cur.next;
        }
        return head;
    }

    /**
     * 方法2； 双重while循环： 类似数组的双重for循环， 且内层循环起点为外层循环的值。即为：
     * for(int i = 0; i < nums.length; i++) {
     *     for(int j = i; j < nums.length; j++) {
     *
     *     }
     * }
     * 实则： 双指针： 慢指针： 在外， 快指针： 在内， 且： 让快指针的每个值 都与 慢指针比较 即可。
     * 启发： 如何控制链表：多次从头循环。 用双重for循环来解决 即可！！！
     *
     */
    public static ListNode removeDuplicateNodesV3(ListNode head) {
        ListNode aa = head;
        while (aa != null) {
            ListNode ac = aa;
            while (ac.next != null) {
                if (ac.next.val == aa.val) {
                    ac.next = ac.next.next;
                } else {
                    ac = ac.next;
                }
            }
            aa = aa.next;
        }
        return head;
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

    }
}

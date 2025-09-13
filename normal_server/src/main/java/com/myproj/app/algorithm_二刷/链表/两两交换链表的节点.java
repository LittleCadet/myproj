package com.myproj.app.algorithm_二刷.链表;

/**
 * 给你一个链表，两两交换其中相邻的节点，并返回交换后链表的头节点。你必须在不修改节点内部的值的情况下完成本题（即，只能进行节点交换）。
 *
 * 示例 1：
 * 输入：head = [1,2,3,4]
 * 输出：[2,1,4,3]
 *
 * 示例 2：
 * 输入：head = []
 * 输出：[]
 *
 * 示例 3：
 * 输入：head = [1]
 * 输出：[1]
 *
 *      思路：
 *          - 方式1：递归：
 *              -- 核心： 递归的顺序：需要按照1-3-5...的顺序递归
 *                  -- 新节点是当前节点的next节点
 *                  -- 递归：入参：是当前节点的next.next节点，出参赋值给 原节点的next节点。
 *                  -- 新节点的next节点是当前节点。
 *          - 方式2：遍历：
 *              -- 核心： tmp -> node1 -> node2 变为： tmp -> node2 -> node1即可
 *              -- 两两交换必定会用到tmp节点。但与数字交换不同的是：
 *                  -- 链表的两两交换 本质 是引用的交换，
 *                      -- tmp.next = node2;
 *                      -- node1.next = node2.next;
 *                      -- node2.next = node1;
 *                  -- 数字的交换是值的交换。eg: x = 1, y = 2的交换：
 *                      -- tmp = y;
 *                      -- y = x;
 *                      -- x = tmp;
 *
 *
 * @author shenxie
 **/
public class 两两交换链表的节点 {

    public static void main(String[] args) {
        ListNode list1 = new ListNode(1);
        list1.next = new ListNode(2);
        list1.next.next = new ListNode(3);
        list1.next.next.next = new ListNode(4);

//        swapPairs(list1);
        swapPairsV2(list1);
    }

    /**
     * 方式1： 递归
     * @param head
     * @return
     */
    public static ListNode swapPairs(ListNode head) {
        // 要完成交换， 至少需要2个节点， 所以需要 head != null && head.next != null
        if(null == head || head.next == null) {
            return head;
        }
        // 将当前节点的next节点 给 新节点  [其中包含原节点的next引用]
        ListNode node = head.next;
        // 递归 完成节点交换
        // - 入参是node.next的原因： 递归需要按照1-3-5-7-9 ...的规律执行，才能完成两两替换
        // -- 所以递归的入参需要是head.next.next： 即为node包含原节点的next引用，所以node.next实质上是head.next.next
        // - 出参是head.next的原因：head最终需要赋值给新节点的next引用，如果出参是head， 则新节点最终是2，4， 其中1,3因为缺少head的next的引用， 所以在新节点上也体现不出来。
        head.next = swapPairs(node.next);
        // 将当前节点给新节点的next节点。
        node.next = head;
        return node;

    }


    /**
     * 方式2： 遍历的方式实现
     */
    public static ListNode swapPairsV2(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode tmp = dummy;
        // tmp一定不为null， 因为tmp是哑巴节点，有值。
        // 节点两两互换的要求是：tmp.next 和 tmp.next.next都要有值才可以
        while(tmp.next != null && tmp.next.next != null) {
            ListNode node1 = tmp.next;
            ListNode node2 = tmp.next.next;
            // 实现： tmp -> node1 -> node2 变为： tmp -> node2 -> node1即可
            // 变更tmp的引用为node2
            tmp.next = node2;
            node1.next = node2.next;
            node2.next = node1;
            tmp = node1;
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

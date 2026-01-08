package com.myproj.app.algorithm.堆;

import com.myproj.app.algorithm.数学.丑数;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * 给你一个整数 n ，请你找出并返回第 n 个 丑数 。
 * 丑数 就是质因子只包含 2、3 和 5 的正整数。
 *
 * 示例 1：
 * 输入：n = 10
 * 输出：12
 * 解释：[1, 2, 3, 4, 5, 6, 8, 9, 10, 12] 是由前 10 个丑数组成的序列。
 *
 * 示例 2：
 * 输入：n = 1
 * 输出：1
 * 解释：1 通常被视为丑数。
 *
 *      思路：
 *  *          - 与{@link 丑数}类似：
 *  *              - {@link 丑数}： 是判定是否是丑数， 是逆向判定的过程。
 *  *              - {@link 丑数II}: 是一个生成丑数的正向过程，需要借助 PriorityQueue 和 HashSet来实现
 *
 * @author shenxie
 * @date 2025/11/19
 */
public class 丑数II {

    public static void main(String[] args) {
        System.out.println(nthUglyNumber(10));
//        System.out.println(nthUglyNumberV2(10));
    }
    public static int nthUglyNumber(int n) {
        int[] nums = {2,3,5};
        Set<Long> set = new HashSet<>();
        // 最小堆： 用堆 而不是 Deque 或者 ArrayList的原因：
        // - Deque / ArrayList:
        // -- 只能保证 LILO 或者 LIFO， 但是题意要求：将丑数按照从小到大 排序， 取第N个丑数。
        // -- 可以每次将Deque排序，再取出最小数， 最后返回第1个元素。但是这样：没有PriorityQueue简单
        PriorityQueue<Long> queue = new PriorityQueue<>();
        // queue中预置数， 只能是1，因为：1 * 任何数 = 任何数
        queue.offer(1L);
        long result = 0 ;
        for(int i = 0 ; i <n ;i++) {
            // 不断取出堆中的最小数
            long cur = queue.poll();
            result = cur;
            for(int num : nums) {
                // 每次取出堆顶元素 cur，则 cur 是堆中最小的丑数，由于 2*cur,3*cur,5*cur 也是丑数，因此将 2*cur,3*cur,5*cur 加入堆。
                long tmp = cur * num;
                // 上述做法会导致堆中出现重复元素的情况。为了避免重复元素，可以使用哈希集合去重，避免相同元素多次加入堆。
                if(set.add(tmp)){
                    queue.offer(tmp);
                }

            }
        }

        System.out.println(queue);

        return (int)result;
    }
}

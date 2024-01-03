package com.myproj.app.algorithm.数组;

import java.util.Arrays;

/**
 * 题目；
 * 给你一个整数数组 citations ，其中 citations[i] 表示研究者的第 i 篇论文被引用的次数。计算并返回该研究者的 h 指数。
 * 根据维基百科上 h 指数的定义：h 代表“高引用次数” ，一名科研人员的 h 指数 是指他（她）至少发表了 h 篇论文，
 * 并且 至少 有 h 篇论文被引用次数大于等于 h 。如果 h 有多种可能的值，h 指数 是其中最大的那个。
 *
 * 分析：
 * 1. h指数 不是指：因为 citations.length - i >= citations[i] 所以H指数为citations[i]。
 *                  如果： citations[i]的值 远大于 citations.length时， 就无法判定了。 所以不对。
 *                        即为： 发表的论文每一篇都是高质量的， 被引用的次数非常多，  但是发表论文的次数很少时， 该判定不成立。
 * 2. 正确的思路： 将元素按照从大到小的顺序执行： 如果citations[i] > h， 则被被引用的论文次数 + 1。
 * 因为： 按照从大到小的顺序执行：意味着被引用0次的论文一定在最后，而最一开始执行的元素的论文的引用次数一定>0，
 * 所以论文被引用的次数肯定 = 当前元素的值， 而这符合H指数的判定要求。
 *                          即为： 被引用的次数 > 论文的篇数时， 那么H指数 + 1；
 *                          题意描述不准确：不是 “有 h 篇论文被引用次数大于等于 h”， 而是“有 h 篇论文被引用次数大于 论文篇数”！！！
 *
 *
 * @author shenxie
 * @date 2023/12/9
 */
public class H指数 {

    public static void main(String[] args) {
//        System.out.println(hIndex(new int[]{3,0,6,1,5}));
        System.out.println(hIndex(new int[]{3,0,6,1,5}));
    }

    public static int hIndex(int[] citations) {
        Arrays.sort(citations);
        int h = 0;
        // 必须倒序： 因为：被引用0次的论文一定在最后，而最一开始执行的元素的论文的引用次数一定>0，
        // 如果正序： h代表的是： 引用次数>0次的篇数。
        //          而这与： 题目要求的： h指数：h篇论文的引用次数 >= h次 不符。
        for(int i = citations.length - 1; i >= 0 ; i--) {
            // 只有在被引用的次数 > 被引用的篇数数时， h指数 + 1；
            if(citations[i] > h) {
                h++;
            }
        }
        return h;
    }

    /**
     * 正序的错误做法。
     * 与题意不符。
     */
    public static int hIndexV2(int[] citations) {
        Arrays.sort(citations);
        int h = 0;
        for(int i = 0; i < citations.length ; i++) {
            if(citations[i] > h) {
                h++;
            }
        }
        return h;
    }
}

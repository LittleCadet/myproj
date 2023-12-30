package com.myproj.app.algorithm.区间;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * 题目：
 * 有一些球形气球贴在一堵用 XY 平面表示的墙面上。墙面上的气球记录在整数数组 points ，其中points[i] = [xstart, xend] 表示水平直径在 xstart 和 xend之间的气球。你不知道气球的确切 y 坐标。
 * 一支弓箭可以沿着 x 轴从不同点 完全垂直 地射出。在坐标 x 处射出一支箭，若有一个气球的直径的开始和结束坐标为 xstart，xend， 且满足  xstart ≤ x ≤ xend，则该气球会被 引爆 。可以射出的弓箭的数量 没有限制 。 弓箭一旦被射出之后，可以无限地前进。
 * 给你一个数组 points ，返回引爆所有气球所必须射出的 最小 弓箭数 。
 * 示例 1：
 * 输入：points = [[10,16],[2,8],[1,6],[7,12]]
 * 输出：2
 * 解释：气球可以用2支箭来爆破:
 * -在x = 6处射出箭，击破气球[2,8]和[1,6]。
 * -在x = 11处发射箭，击破气球[10,16]和[7,12]。
 *
 * 思路：
 *      1. 排序 + 贪心。
 *          1.1 排序： 要注意超过int的最大值的情况。
 *          1.2 贪心： 箭一定是往右边射： 才能命中更多的气球。
 * @author shenxie
 * @date 2023/12/29
 */
public class 用最少数量的箭引爆气球 {

    public static void main(String[] args) {
//        System.out.println(findMinArrowShots(new int[][]{{10,16},{2,8}, {1,6},{7,12}}));
//        System.out.println(findMinArrowShots(new int[][]{{-2147483646,-2147483645}, {2147483646,2147483647}}));
        System.out.println(findMinArrowShots(new int[][]{{3,9}, {7,12},{3,8},{6,8},{9,10},{2,9},{0,9},{3,9},{0,6},{2,8}}));


//        int[] nums = new int[]{1,5,3};
//        Arrays.sort(nums);
//        System.out.println();
    }

    public static int findMinArrowShots(int[][] points) {
        // 错误的排序方式：
//        Arrays.sort(points, new Comparator<int[]>(){
//            @Override
//            public int compare(int[] int1, int[] int2){
//                // 注意： 不能使用这种： 因为：如果(int[0] - int[0] ) > Integer.MAX_VALUE ,则默认按照倒序排序， 而不是升序 ！！！
//                // 这个答案：false:  System.out.println((2147483646 + 2147483646) > Integer.MAX_VALUE);
//                // false的原因： 超过了Integer的最大值， 默认返回false.
//                // 所以： 必须int1[0] >  int2[0]时， 返回1， 而不是return int1[0] - int2[0];
//                return int1[1] - int2[1];
//            }
//        });
        // 正确的排序方式。
        Arrays.sort(points, new Comparator<int[]>(){
            /**
             * 这里必须按照int1[1]的方式比较大小， 不能时int1[0]:
             * 原因： 气球升序排序， 肯定是往右边射箭， 能命中更多的气球。 而不是往左射。
             */
            @Override
            public int compare(int[] int1, int[] int2){
                if(int1[1] >  int2[1]) {
                    return 1;
                }else if(int1[1] < int2[1]) {
                    return -1;
                }else{
                    return 0 ;
                }
            }
        });
        // 第一箭 ： 取在第一个元素的右边
        int position = points[0][1];
        int times = 1;
        // 贪心算法
        for(int i = 0 ; i<points.length; i++) {
            if(position < points[i][0]){
                position = points[i][1];
                times ++;
            }
        }
        return times;
    }
}

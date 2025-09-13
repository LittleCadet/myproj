package com.myproj.app.algorithm_二刷.数组;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * 以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi] 。请你合并所有重叠的区间，并返回 一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：intervals = [[1,3],[2,6],[8,10],[15,18]]
 * 输出：[[1,6],[8,10],[15,18]]
 * 解释：区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
 *
 * 示例 2：
 *
 * 输入：intervals = [[1,4],[4,5]]
 * 输出：[[1,5]]
 * 解释：区间 [1,4] 和 [4,5] 可被视为重叠区间。
 *
 * 思路：
 *      1. 排序 + 比较大小。
 *      2. 注意特殊写法：
 *          - 二维数组的List表示方式： List<int[]> results = new ArrayList<>();
 *          - 二维数组的排序：
 *           Arrays.sort(intervals, new Comparator<int[]>(){
 *            @Override
 *            public int compare(int[] o1, int[] o2) {
 *                return o1[0] - o2[0];
 *            }
 *         });
 *         - list转换为数组： results.toArray(new int[results.size()][]);
 *
 * @author shenxie
 **/
public class 合并区间 {

    public static void main(String[] args) {
        int[][] intervals = new int[][]{{1,3}, {2,6}, {8,10}, {15,18}};
        mergeCopy(intervals);
    }

    public static int[][] mergeCopy(int[][] intervals) {
        List<int[]> results = new ArrayList<>();
        // 将二维数组 按照 左边的值 比较大小 并 排序
        Arrays.sort(intervals, new Comparator<int[]>(){
           @Override
           public int compare(int[] o1, int[] o2) {
               return o1[0] - o2[0];
           }
        });

        // 合并区间
        for(int i = 0 ; i < intervals.length; i++) {
            int L = intervals[i][0]; int R= intervals[i][1];
            if(results.size() == 0  || results.get(results.size() - 1)[1] < L) {
                results.add(new int[]{L,R});
            }else{
                results.get(results.size() - 1)[1] = Math.max(results.get(results.size() - 1)[1], R);
            }
        }


        return results.toArray(new int[results.size()][]);
    }
}

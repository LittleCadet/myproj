package com.myproj.app.algorithm.区间;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * 给你一个 无重叠的 ，按照区间起始端点排序的区间列表。
 * 在列表中插入一个新的区间，你需要确保列表中的区间仍然有序且不重叠（如果有必要的话，可以合并区间）。
 * 示例 1：
 * 输入：intervals = [[1,3],[6,9]], newInterval = [2,5]
 * 输出：[[1,5],[6,9]]
 *
 * 思路：
 *      1. 将newInterval 合并到 intervals中， 后续操作与合并区间 完全一致。
 *
 * @author shenxie
 * @date 2023/12/29
 */
public class 插入区间 {
    public static void main(String[] args) {
        insert(null, null);
    }

    public static int[][] insert(int[][] intervals, int[] newInterval) {
        // 将newInterval合并到intervals中。
        int[][] ints = new int[intervals.length + 1][2];
        for(int i = 0; i<intervals.length; i++) {
            ints[i] = intervals[i];
        }
        ints[intervals.length ] = newInterval;

        return merge(ints);
    }

    private static int[][] merge(int[][] ints){
        List<int[]> nums = new ArrayList<>();
        // 排序
        Arrays.sort(ints, Comparator.comparingInt(anInt -> anInt[0]));

        // 合并
        for(int i =0; i< ints.length; i++) {
            int L = ints[i][0]; int R = ints[i][1];
            if(nums.isEmpty() || nums.get(nums.size() -1)[1] < L) {
                nums.add(new int[]{L, R});
            }else{
                nums.get(nums.size() -1)[1] = Math.max(R, nums.get(nums.size() - 1)[1]);
            }
        }

        return nums.toArray(new int[nums.size()][]);
    }
}

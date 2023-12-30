package com.myproj.app.algorithm.区间;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import org.apache.commons.compress.utils.Lists;

/**
 * 题目：
 * 以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi] 。
 * 请你合并所有重叠的区间，并返回 一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间 。
 * 示例 1：
 * 输入：intervals = [[1,3],[2,6],[8,10],[15,18]]
 * 输出：[[1,6],[8,10],[15,18]]
 * 解释：区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
 *
 * 思路：
 * 1. 合并数组一定是：List<int[]>类型。  绝对不是int[][]类型。
 *      因为 后者需要确定一维数组的大小，但是不知道多大， 只知道二维数组大小。即为 new int[ ? ] [2].
 * 2. 二维排序：
 *      Arrays.sort(intervals, new Comparator<int[]>(){
 *            @Override
*             public int compare(int[] s1, int[] s2){
*                 return s1[0] - s2[0];
*             }
 *      });
 * 3. 放入合并数组merge的2种情况： 【绝对不是：intervals之间的比较！！！】
 *      3.1 merge与intervals没有区间覆盖的情况：
 *      3.2 merge与intervals有区间覆盖的情况：
 * @author shenxie
 * @date 2023/12/29
 */
public class 合并区间 {

    public static void main(String[] args) {
        List<Integer> nums = Lists.newArrayList();
        nums.add(1);
        nums.add(2);
        nums.toArray(new int[nums.size()][]);
    }

    public int[][] mergeV2(int[][] intervals) {
        List<int[]> merge= new ArrayList<int[]>();
        // 二维数组排序。
        Arrays.sort(intervals, new Comparator<int[]>(){
            @Override
            public int compare(int[] s1, int[] s2){
                return s1[0] - s2[0];
            }
        });
        for(int i = 0; i < intervals.length; i++){
            int L = intervals[i][0]; int R = intervals[i][1];
            // 当合并数组为空， 或者 合并数组的元素的第2个元素值 < intervals的元素的第1个元素值时，
            // 代表： 两者没有区间覆盖的情况。
            if(merge.size() == 0 || merge.get(merge.size() -1 )[1] < L) {
                merge.add(new int[]{L, R});
            }else{
                // 代表： 两者有区间覆盖的情况：
                // 这里合并数组元素的第2个元素 与 二维数组的元素的第2个元素 之间 取大。
                // 避免出现： [1,4], [2,3]的结果为： [1,3], 应该为[1,4].
                merge.get(merge.size() - 1)[1] = Math.max(R, merge.get(merge.size() - 1)[1]);
            }
        }
        return merge.toArray(new int[merge.size()][]);
    }

    /**
     * 以下方法是错误的：
     * 原因：
     * 1. 直接构造一个数组需要确定数组大小， 但是： 此时不知道合并后的数组大小。
     * 2. 如果强制设置一个数组大小size， 那么一旦合并后的元素 比 size 小时， 会自动填充数组类型的默认值： int数组就是0. 不符合题意。
     */
    public static int[][] merge(int[][] intervals) {
        // 难点： 新的二维数组的长度是多少？
        int[][] nums = new int[intervals.length][2];
        for(int i = 1 ; i< intervals.length; i++){
            if(intervals[i-1][1] > intervals[i][0]) {
                nums[i-1][0] = intervals[i-1][0];
                nums[i-1][1] = intervals[i][1];
            }else{
                nums[i][0] = intervals[i][0];
                nums[i][1] = intervals[i][1];
            }
        }
        return nums;
    }
}

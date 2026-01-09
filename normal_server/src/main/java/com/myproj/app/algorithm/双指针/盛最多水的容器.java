package com.myproj.app.algorithm.双指针;

import com.myproj.app.algorithm.数组.颜色分类;

/**
 * 题目：
 * 给定一个长度为 n 的整数数组 height 。有 n 条垂线，第 i 条线的两个端点是 (i, 0) 和 (i, height[i]) 。
 * 找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
 * 返回容器可以储存的最大水量。
 * 说明：你不能倾斜容器。
 *
 * 示例1：
 * 输入：[1,8,6,2,5,4,8,3,7]
 * 输出：49
 * 解释：图中垂直线代表输入数组 [1,8,6,2,5,4,8,3,7]。在此情况下，容器能够容纳水（表示为蓝色部分）的最大值为 49。
 *
 * 思路：
 *      1. 首尾双指针：指的是双指针自己。 且是首尾指针。
 *          1.1 指针停止移动的标志： l < r. 即为： 两指针重合时，宽度为0， 那么面积也是0.
 *
 *      快慢双指针：{@link 删除有序数组中的重复项} {@link 删除有序数组中的重复项II} {@link 判断子序列} {@link 找出字符串的第一个匹配项的下标}
 *          {@link 移除元素} {@link 长度最小的子数组}{@link 颜色分类}
 *      首尾双指针：{@link 三数之和} {@link 两数之和II输入有序数组} {@link 盛最多水的容器}
 *
 * @author shenxie
 * @date 2023/12/26
 */
public class 盛最多水的容器 {

    public static void main(String[] args) {
        System.out.println(maxArea(new int[]{1,8,6,2,5,4,8,3,7}));
        System.out.println(maxAreaV2(new int[]{1,8,6,2,5,4,8,3,7}));
    }

    public static int maxArea(int[] height) {
        int sum = 0;
        // 首尾指针
        int l = 0, r = height.length - 1;
        // 注意： 指针停止移动的标志： l < r. 即为： 两指针重合时，宽度为0， 那么面积也是0.
        while(l < r) {
            // 当前面积 = 以高度最小的height值 * 宽度
            int tmp = Math.min(height[l], height[r]) * (r - l);
            // 面积取大。
            sum = Math.max(tmp, sum);
            // 哪边高度小， 移动哪边。 
            if(height[l] > height[r]) {
                r --;
            }else{
                l ++;
            }
        }

        return sum;
    }

    public static int maxAreaV2(int[] height) {
        int result = 0;

        int left = 0;
        int right = height.length - 1;

        while (left < right) {
            int tmp = height[left] > height[right] ?
                    (right - left) * height[right--] :
                    (right - left) * height[left++];
            result = Math.max(tmp, result);
        }

        return result;
    }

}

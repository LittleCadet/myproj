package com.myproj.app.algorithm_二刷.双指针;

/**
 * 给定一个长度为 n 的整数数组 height 。有 n 条垂线，第 i 条线的两个端点是 (i, 0) 和 (i, height[i]) 。
 * 找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
 * 返回容器可以储存的最大水量。
 * 说明：你不能倾斜容器。
 * 示例 1：
 * 输入：[1,8,6,2,5,4,8,3,7]
 * 输出：49
 * 解释：图中垂直线代表输入数组 [1,8,6,2,5,4,8,3,7]。在此情况下，容器能够容纳水（表示为蓝色部分）的最大值为 49。
 *
 * 思路：
 *      1. 想想面积公式：S(i,j)=min(h[i],h[j])×(j−i)
 *
 *
 * 示例 2：
 * 输入：height = [1,1]
 * 输出：1
 * @author shenxie
 **/
public class 盛最多水的容器 {

    public static void main(String[] args) {
        int[] height = {1,8,6,2,5,4,8,3,7};
        System.out.println(maxArea(height));
    }

    public static int maxArea(int[] height) {
        if(height.length == 1) {
            return 0;
        }
        int i = 0;
        int j = height.length -1;
        int result = 0;
        // 面积公式: S(i,j)=min(h[i],h[j])×(j−i)
        // 因为是首尾双指针， 所以只要移动了指针， 那么 j-i的值就会少1
        // 所以此题的关键点是：min(h[i], h[j]):
        // 如果移动长指针， 则min(h[i], h[j])， 一定变小 或 不变。
        // 如果移动短指针， 则min(h[i], h[j])， 一定变大 或 不变。
        while(i < j) {
            result = height[i] < height[j] ?
                    Math.max(result, (j -i) * height[i++]) :
                    Math.max(result, (j -i) * height[j --]);
        }
        return result;


    }
}

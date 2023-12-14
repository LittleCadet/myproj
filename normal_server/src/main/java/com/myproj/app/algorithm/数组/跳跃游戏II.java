package com.myproj.app.algorithm.数组;

/**
 * 题目：
 * 给定一个长度为 n 的 0 索引整数数组 nums。初始位置为 nums[0]。
 * 每个元素 nums[i] 表示从索引 i 向前跳转的最大长度。换句话说，如果你在 nums[i] 处，你可以跳转到任意 nums[i + j] 处:
 *     0 <= j <= nums[i]
 *     i + j < n
 * 返回到达 nums[n - 1] 的最小跳跃次数。生成的测试用例可以到达 nums[n - 1]。
 *
 *  思路：
 *  1. 贪心算法： 以当前元素位置跳跃所能跳到的最大位置，之后再次尝试最远距离的跳跃。
 *      所以： 跳跃的次数增加的时机： 到了目前所能跳跃的最大位置才会继续跳跃。
 *
 * @author shenxie
 * @date 2023/12/9
 */
public class 跳跃游戏II {

    public static void main(String[] args) {
        System.out.println(jump(new int[]{2,3,1,1,4}));
    }

    public static int jump(int[] nums) {
        int times = 0;
        int max = 0 ;
        int tmp = 0;

        // i < nums.length - 1 而不是 nums.length的原因： 题干指出： 生成的测试用例可以到达 nums[n - 1]。
        for(int i=0; i< nums.length -1 ; i++) {
            max = Math.max(max, i + nums[i]);
            // 到达指定的位置后， 再跳
            if(i == tmp) {
                tmp = max;
                times ++;
            }
        }
        return times;
    }
}

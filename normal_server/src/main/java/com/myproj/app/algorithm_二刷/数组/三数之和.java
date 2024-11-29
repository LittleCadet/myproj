package com.myproj.app.algorithm_二刷.数组;

import java.util.*;

/**
 * 给你一个整数数组 nums ，判断是否存在三元组 [nums[i], nums[j], nums[k]] 满足 i != j、i != k 且 j != k ，同时还满足 nums[i] + nums[j] + nums[k] == 0 。请你返回所有和为 0 且不重复的三元组。
 * 注意：答案中不可以包含重复的三元组。
 * <p>
 * 示例 1：
 * 输入：nums = [-1,0,1,2,-1,-4]
 * 输出：[[-1,-1,2],[-1,0,1]]
 * 解释：
 * nums[0] + nums[1] + nums[2] = (-1) + 0 + 1 = 0 。
 * nums[1] + nums[2] + nums[4] = 0 + 1 + (-1) = 0 。
 * nums[0] + nums[3] + nums[4] = (-1) + 2 + (-1) = 0 。
 * 不同的三元组是 [-1,0,1] 和 [-1,-1,2] 。
 * 注意，输出的顺序和三元组的顺序并不重要。
 * <p>
 * 示例 2：
 * 输入：nums = [0,1,1]
 * 输出：[]
 * 解释：唯一可能的三元组和不为 0 。
 * <p>
 * 示例 3：
 * 输入：nums = [0,0,0]
 * 输出：[[0,0,0]]
 * 解释：唯一可能的三元组和为 0
 * <p>
 * 思路：
 * 1. 方式一： 先排序 + 首尾双指针
 *      1). 思考：
 *      - 如果最小的元素 > 0, 则直接返回结果。
 *      - 注意： 要求：三个元素的指针：不能是两两相等的。 所以：
 *      - 假设：第一个元素的位置是k, 则left的位置是 k+ 1, 而right的位置是 right;
 *      - 且 双指针在移动时， 满足 left < right
 *      2). 数组元素中：可能包含重复的元素， 所以三个元素的指针， 在移动时， 都需要去除各自重复的元素， 来加速循环。
 *      - 指针移动时， 去除各自重复的元素：
 *      while(left < right && nums[right] == nums[ -- right]);
 *      3). sum > 0时， right  --
 *      4). sum < 0时， left ++
 *      5). sum =0时， right -- && left ++
 *
 * 2. 方式二： 排序 + 双重for循环 + set容器。【定一， 定二， 找三】
 *      1). 成功： 详见：{@link com.myproj.app.algorithm.双指针.三数之和}
 *          - 核心点：指针移动时， 去重。
 *          - 重置set容器
 *      2). 失败： 详见： 本类方法：threeSumV2
 *
 * @author shenxie
 **/
public class 三数之和 {

    public static void main(String[] args) {
//        int[] nums = {-1, 0, 1, 2, -1, -4};
//        int[] nums = {-1,0,1};
        int[] nums = {1,2,-2,-1};
        System.out.println(threeSum(nums));
//        System.out.println(threeSumV2(nums));
    }

    public static List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        for (int k = 0; k < nums.length; k++) {
            if (nums[k] > 0) {
                return result;
            }
            if (k > 0 && nums[k] == nums[k - 1]) {
                continue;
            }
            int left = k + 1;
            int right = nums.length - 1;
            while (left < right) {
                int sum = nums[k] + nums[left] + nums[right];
                if (sum > 0) {
                    while (left < right && nums[right] == nums[--right]) ;
                } else if (sum < 0) {
                    while (left < right && nums[left] == nums[++left]) ;
                } else {
                    result.add(new ArrayList(Arrays.asList(nums[k], nums[left], nums[right])));
                    while (left < right && nums[right] == nums[--right]) ;
                    while (left < right && nums[left] == nums[++left]) ;
                }
            }
        }
        return result;


    }


    /**
     * 思想：用集合 + 固定首尾两数， 找第三数：
     *  - 不行： 因为：set容器的元素依赖左指针的元素， 没有重置set的动作，
     *      - 所以会导致 类似数组[-2, -1, 1, 2]： -1只出现一次， 但是因为set容器中包含-1，所以产生错误答案： [-1 ， -1， 2]
     *      - 详见{@link  com.myproj.app.algorithm.双指针.三数之和}
     *
     */
    public static List<List<Integer>> threeSumV2(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        Set<Integer> set = new HashSet<>();
        int left = 0;
        int right = nums.length - 1;

        while (left < right) {
            set.add(nums[left]);
            int sum = nums[left] + nums[right];
            if (set.contains(-sum)) {
                result.add(new ArrayList(Arrays.asList(nums[left], nums[right], -sum)));
                while(left < right && nums[left] == nums[ ++ left]);
                while(left < right && nums[right] == nums[-- right]);
            } else if (sum > 0) {
                right--;
            }else{
                left++;
            }
            set.add(nums[left]);
        }
        return result;
    }
}

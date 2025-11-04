package com.myproj.app.algorithm.二分查找;

/**
 * 已知存在一个按非降序排列的整数数组 nums ，数组中的值不必互不相同。
 * 在传递给函数之前，nums 在预先未知的某个下标 k（0 <= k < nums.length）上进行了 旋转 ，使数组变为 [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]]（下标 从 0 开始 计数）。
 * 例如， [0,1,2,4,4,4,5,6,6,7] 在下标 5 处经旋转后可能变为 [4,5,6,6,7,0,1,2,4,4] 。
 * 给你 旋转后 的数组 nums 和一个整数 target ，请你编写一个函数来判断给定的目标值是否存在于数组中。如果 nums 中存在这个目标值 target ，则返回 true ，否则返回 false 。
 * 你必须尽可能减少整个操作步骤。
 *
 * 示例 1：
 * 输入：nums = [2,5,6,0,0,1,2], target = 0
 * 输出：true
 *
 * 示例 2：
 * 输入：nums = [2,5,6,0,0,1,2], target = 3
 * 输出：false
 *
 *      想法：
 *          - 二分法：与{@link 搜索旋转排序数组} 完全相同
 *
 *
 * @author shenxie
 * @date 2025/11/3
 */
public class 搜索旋转排序数组II {

    public static void main(String[] args) {
        System.out.println(search(new int[]{2,5,6,0,0,1,2}, 0));
    }

    public static boolean search(int[] nums, int target) {
        int left = 0 , right = nums.length - 1, mid = 0 ;
        while(left <= right) {
            mid =  left + (right - left) / 2;
            if(nums[mid] == target) {
                return true;
            }

            // 左节点 与 mid节点相等时：
            if(nums[left] == nums[mid]) {
                left ++ ;
            }
            // 左节点 比 mid节点小时：
            else if(nums[left] < nums[mid]) {
                if(nums[left] <= target && target <nums[mid]) {
                    right = mid - 1;
                }else{
                    left = mid + 1;
                }
            }
            // 左节点 比 mid节点大时：
            else{
                if(nums[mid] < target && target <= nums[nums.length - 1]) {
                    left = mid + 1;
                }else{
                    right = mid -1;
                }
            }
        }

        return false;
    }
}

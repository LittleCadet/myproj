package com.myproj.app.algorithm.数组;

import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * @author shenxie
 * @date 2025/10/31
 */
public class 零到一时间插入删除和获取随机元素 {

    public static void main(String[] args) {
        RandomizedSet randomizedSet = new RandomizedSet();
        System.out.println(randomizedSet.insert(1));; // 向集合中插入 1 。返回 true 表示 1 被成功地插入。
        System.out.println(randomizedSet.remove(2));; // 返回 false ，表示集合中不存在 2 。
        System.out.println(randomizedSet.insert(2));; // 向集合中插入 2 。返回 true 。集合现在包含 [1,2] 。
        System.out.println(randomizedSet.getRandom());; // getRandom 应随机返回 1 或 2 。
        System.out.println(randomizedSet.remove(1));; // 从集合中移除 1 ，返回 true 。集合现在包含 [2] 。
        System.out.println(randomizedSet.insert(2));; // 2 已在集合中，所以返回 false 。
        System.out.println(randomizedSet.getRandom());; // 由于 2 是集合中唯一的数字，getRandom 总是返回 2 。

//        List<Integer> list = Lists.newArrayList(1,2,3);
//        list.remove(2);
//        System.out.println(list);
    }

    static class RandomizedSet {
        List<Integer> list;
        Map<Integer, Integer> map;
        Random random;

        public RandomizedSet() {
            list = new ArrayList<Integer>();
            map = new HashMap<Integer, Integer>();
            random = new Random();
        }

        public boolean insert(int val) {
            if (map.containsKey(val)) {
                return false;
            }
            int index = list.size();
            list.add(val);
            // map 的value ， 必须放入index， 为删除元素做准备。
            map.put(val, index);
            return true;
        }

        public boolean remove(int val) {
            if (!map.containsKey(val)) {
                return false;
            }
            int index = map.get(val);
            int last = list.get(list.size() - 1);
            // 把list的最后一个元素 更新 到index上
            list.set(index, last);
            // 更新该元素在map中的value;
            map.put(last, index);

            // 删除list最后一个元素 + map的指定key
            list.remove(list.size() - 1);
            map.remove(val);

            // 以下做法不正确： 因为list.remove(val), 是按照数组下标移除， 而不是移除object.
//            map.remove(val);
//           list.remove(val);
            return true;
        }

        public int getRandom() {
            int randomIndex = random.nextInt(list.size());
            return list.get(randomIndex);
        }
    }
}

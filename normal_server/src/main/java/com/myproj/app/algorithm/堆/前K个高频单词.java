package com.myproj.app.algorithm.堆;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * 给定一个单词列表 words 和一个整数 k ，返回前 k 个出现次数最多的单词。
 * 返回的答案应该按单词出现频率由高到低排序。如果不同的单词有相同出现频率， 按字典顺序 排序。
 *
 * 示例 1：
 * 输入: words = ["i", "love", "leetcode", "i", "love", "coding"], k = 2
 * 输出: ["i", "love"]
 * 解析: "i" 和 "love" 为出现次数最多的两个单词，均为2次。
 *     注意，按字母顺序 "i" 在 "love" 之前。
 *
 * 示例 2：
 * 输入: ["the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"], k = 4
 * 输出: ["the", "is", "sunny", "day"]
 * 解析: "the", "is", "sunny" 和 "day" 是出现次数最多的四个单词，
 *     出现次数依次为 4, 3, 2 和 1 次。
 *
 *
 *     解法：
 *          方法1： HashMap + Collections.sort() + list.subList(0 ,k);
 *
 *          方法2： PriorityQueue + HashMap; 【推荐】
 *
 *
 * @author shenxie
 * @date 2025/9/18
 */
public class 前K个高频单词 {

    public static void main(String[] args) {
        System.out.println(topKFrequent(new String[]{"i", "love", "leetcode", "i", "love", "coding"}, 2));
        System.out.println(topKFrequentV2(new String[]{"i", "love", "leetcode", "i", "love", "coding"}, 2));
    }

    /**
     * 方法1： HashMap + Collections.sort();
     */
    public static List<String> topKFrequent(String[] words, int k) {
        List<String> results = new ArrayList<>();
        Map<String, Integer> wordCountMap = new HashMap<>();
        for(int i = 0 ;i<words.length; i++) {
            int times = wordCountMap.getOrDefault(words[i], 1);
            wordCountMap.put(words[i], ++times);
        }

        for(String key : wordCountMap.keySet()) {
            results.add(key);
        }

        /**
         * 优先按照 单词的频率 排序， 相同时， 再根据 首字母排序。
         */
        Collections.sort(results, new Comparator<String>(){
            @Override
            public int compare(String s1, String s2) {
                return wordCountMap.get(s2) == wordCountMap.get(s1) ?
                        // 注意 api的使用：
                        s1.compareTo(s2) :
                        wordCountMap.get(s2) - wordCountMap.get(s1);
            }
        });

        // 前K个元素： 用排序后的List,  list.subList(0 , K);
        return results.subList(0, k);
    }


    /**
     * 方法2：PriorityQueue + hashMap
     */
    public static List<String> topKFrequentV2(String[] words, int k) {
        List<String> results = new ArrayList<>();
        Map<String, Integer> wordCountMap = new HashMap<>();
        PriorityQueue<String> queue = new PriorityQueue<>(new Comparator<String>(){
            @Override
            public int compare(String s1, String s2) {
                return wordCountMap.get(s1) == wordCountMap.get(s2) ?
                        s1.compareTo(s2) :
                        wordCountMap.get(s2) - wordCountMap.get(s1);
            }
        });
        for(int i = 0 ;i<words.length; i++) {
            int times = wordCountMap.getOrDefault(words[i], 1);
            wordCountMap.put(words[i], ++times);
        }

        for(String key : wordCountMap.keySet()) {
            queue.offer(key);
        }

        for(int i = 0 ; i<k; i++) {
            results.add(queue.poll());
        }

        return results;
    }
}

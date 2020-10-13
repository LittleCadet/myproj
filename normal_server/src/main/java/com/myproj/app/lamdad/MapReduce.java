package com.myproj.app.lamdad;

import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * @author shenxie
 * @date 2020/10/13
 */
public class MapReduce {

    /**
     * map : 对数据归类
     * reduce ： 对数据进行计算。
     * @param args
     */
    public static void main(String[] args) {
        List<Order> orders = Lists.newArrayList(new Order(1,"11") , new Order(2 , "22"));
        Integer max = orders.stream()
                .map(Order::getMoney)
                .reduce(Integer::sum)
                .get();

        System.out.println(max);
    }

    @Data
    @AllArgsConstructor
    static class Order{
        private Integer money;

        private String orderNo;
    }
}

package com.spring.app.stopwatch;

import javafx.scene.paint.Stop;
import org.springframework.util.StopWatch;

/**
 * 用更加优雅的方式实现 毫秒数的计时
 * @author shenxie
 * @date 2020/8/26
 */
public class StopWatchDemo {

    public static void main(String[] args) {
        int sum1 = 0;
        int sum2 = 0;
        StopWatch stopWatch = new StopWatch();
        stopWatch.start("100000以内的累加");
        for (int i = 1 ; i <=100000 ; i++){
            sum1 = sum1 + i;
        }
        System.out.println("和 ： " + sum1);
        stopWatch.stop();
        System.out.println("耗时： " + stopWatch.getTotalTimeMillis());
        stopWatch.start("10000000以内的累加");
        for (int i = 1 ; i <=10000000 ; i++){
            sum2 = sum2 + i;
        }
        System.out.println("和 ： " + sum2);
        stopWatch.stop();
        System.out.println(stopWatch.getTaskCount());

        //输出从第一次start时， 到最后一个stop的总毫秒数
        System.out.println("耗时毫秒数 ： "  + stopWatch.getTotalTimeMillis());
        //输出从第一次start时， 到最后一个stop的总秒数
        System.out.println("耗时秒数 ： " + stopWatch.getTotalTimeSeconds());

        //自带格式化输出 ： 会将每次stop的后的taskName 和 总耗时 放在taskInfo中， 并放在List中，之后从list中取出taskName和耗时 ,并做格式化操作
        System.out.println("耗时秒数 ： " + stopWatch.prettyPrint());
    }
}

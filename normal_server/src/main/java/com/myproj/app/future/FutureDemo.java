package com.myproj.app.future;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author shenxie
 * @date 2020/8/14
 */
public class FutureDemo {
    public static void main(String[] args) {

        showInfoV4();
    }

    /**
     * thenApply : 生产者： 吧执行结果当做入参
     */
    public static void showInfoV1(){
        CompletableFuture.supplyAsync(() -> 1)
                .thenApply(i -> i + 1)
                .thenApply(i -> i * 2)
                .whenComplete((r ,e) -> System.out.println("r: " + r  + "\ne:" + e));
    }

    /**
     * thenCombine : 聚集结果
     * thenAccept: 消费者：无返回值，  接受上一阶段的输出，作为本阶段的输入
     * thenRun : 消费者： 无返回值 ， 不关心也不需要入参
     */
    public static void showInfoV2(){
        CompletableFuture.supplyAsync(() -> "hello")
                .thenApply(s -> s + "world")
                .thenApply(String :: toUpperCase)
                .thenCombine(CompletableFuture.completedFuture("JAVA") , (s1 , s2) -> s1 + s2)
                .thenAccept(System.out::println);
    }

    /**
     * allOf: 所有任务都完成的时候，返回 ， 但是返回值是void
     * anOf:只要有任务完成了，那么就返回 ， 返回值是第一个完成任务的结果
     */
    public static void showInfoV3(){
        String[] list = {"a" , "b" , "c"};
        List<CompletableFuture> resList = new ArrayList<>();
        for(String str : list){
            resList.add(CompletableFuture.supplyAsync(() -> str)
                    .thenApply(e -> e.toUpperCase()));
        }

        //allOf: 所有任务都完成的时候，返回 ， 但是返回值是void
        //anOf:只要有任务完成了，那么就返回 ， 返回值是第一个完成任务的结果
        CompletableFuture.allOf(resList.toArray(new CompletableFuture[resList.size()]))
                .whenComplete((r,e) -> {
                    if(null == e){
                        for(CompletableFuture future : resList) {
                            try {
                                System.out.println(future.get());
                                System.out.println(r);
                                System.out.println(e);
                            } catch (InterruptedException interruptedException) {
                                interruptedException.printStackTrace();
                            } catch (ExecutionException executionException) {
                                executionException.printStackTrace();
                            }
                        }
                    }else{
                        System.out.println("异常");
                    }
                });
    }


    public static void showInfoV4(){
        CompletableFuture.runAsync(()->{
            System.out.println("执行runAsync");
        });
    }
}

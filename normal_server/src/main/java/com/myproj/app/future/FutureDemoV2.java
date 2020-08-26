package com.myproj.app.future;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

/**
 * @author shenxie
 * @date 2020/8/19
 */
public class FutureDemoV2 {

    public static void main(String[] args) throws InterruptedException {
        Paper paper = new Paper();
        for(int i = 0 ; i< 3 ; i ++){
            CompletableFuture<String> future = paper.showInfo();
            future.whenComplete((v,e) -> {
                if(Optional.ofNullable(e).isPresent()){
                    System.out.println(String.format("当前线程 : %s , 执行异常" , Thread.currentThread().getName()));
                }else{
                    System.out.println(v);
                }
            });
        }
        System.out.println("当前线程 ： " + Thread.currentThread().getName());

        Thread.sleep(60* 1000);
    }

    public static class Paper{
        public CompletableFuture<String> showInfo(){
            return CompletableFuture.supplyAsync(()->{
                return String.format("当前线程 : %s , 执行成功" , Thread.currentThread().getName());
            });
        }
    }
    public static class PaperV2{
        public String showInfo(){
            return String.format("当前线程 : %s , 执行成功" , Thread.currentThread().getName());
        }
    }
}

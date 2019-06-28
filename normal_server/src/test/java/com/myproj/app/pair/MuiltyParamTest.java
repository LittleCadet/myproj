package com.myproj.app.pair;

import org.apache.commons.lang3.tuple.Pair;
import org.apache.commons.lang3.tuple.Triple;
import org.junit.Test;

/**
 * java返回多参的测试
 * @author LittleCadet
 */
public class MuiltyParamTest {

    /**
     * 返回2个参数：不可变
     */
    @Test
    public void doubleParam(){

        Pair<Integer,String> pair = MuiltyParam.doubleParam();

        System.out.println("left:" + pair.getLeft());
        System.out.println("key:" + pair.getKey());
        System.out.println("right:" + pair.getRight());
        System.out.println("value:" + pair.getValue());
    }

    /**
     * 返回2个参数：可变
     */
    @Test
    public void doubleParamChange(){
        Pair<Integer,String> pair = MuiltyParam.doubleParamChange();

        System.out.println("left:" + pair.getLeft());
        System.out.println("key:" + pair.getKey());
        System.out.println("right:" + pair.getRight());
        System.out.println("value:" + pair.getValue());
    }

    /**
     * 返回3个参数
     */
    @Test
    public void threeParam(){

        Triple<Integer,Long,String> triple = MuiltyParam.threeParam();

        System.out.println("left:" + triple.getLeft());
        System.out.println("middle:" + triple.getMiddle());
        System.out.println("right:" + triple.getRight());
    }
}

package com.myproj.app.pair;

import org.apache.commons.lang3.tuple.*;

/**
 * java返回多参
 *
 * 注意：Pair不能当作Controller层的返回值，或者入参。会出问题，因为它不是标准的javaBean，序列化和反序列化会出问题。一般用于系统内部，比如Service方法直接、工具方法之间传递数据，是一把利器，多用于RPC方法的调用的返回值
 *
 * @author LittleCadet
 */
public class MuiltyParam {

    /**
     * 返回2个参数：不可变
     * @return
     */
    public static Pair<Integer,String> doubleParam(){

        Pair<Integer,String> pair = ImmutablePair.of(2,"doubleParam");
        return pair;
    }


    /**
     * 返回2个参数：可变
     * @return
     */
    public static Pair<Integer,String> doubleParamChange(){

        Pair<Integer,String> pair = MutablePair.of(22,"doubleParamChange");
        pair.setValue("setValue");

        return pair;
    }




    /**
     * 返回3个参数：不可变
     * @return
     */
    public static Triple<Integer,Long,String> threeParam(){

        Triple<Integer, Long, String> triple = ImmutableTriple.of(3,3000L,"threeParam");

        return triple;
    }


    /**
     * 返回3个参数，貌似依旧不可变
     * @return
     */
    public static Triple<Integer,Long,String> threeParamChange(){

        Triple<Integer,Long,String> triple = MutableTriple.of(33,3333L,"threeParamChange");

        return triple;
    }


}

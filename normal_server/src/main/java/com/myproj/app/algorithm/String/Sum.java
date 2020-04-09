package com.myproj.app.algorithm.String;

/**
 * @Author LittleCadet
 * @Date 2020/4/8
 */
public class Sum
{

    public static String sum(String a, String b){
        return String.valueOf(Integer.valueOf(a) + Integer.valueOf(b));
    }

    public static void main(String[] args)
    {
        System.out.println(sum("-2" ,"100"));
    }
}

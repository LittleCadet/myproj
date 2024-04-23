package com.myproj;

/**
 * @author shenxie
 * @date 2024/4/23
 */
public interface Sql {

    String[] sourceSql();

    String[] transformSql();

    String[] sinkSql();
}

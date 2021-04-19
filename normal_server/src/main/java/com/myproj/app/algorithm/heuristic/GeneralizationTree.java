package com.myproj.app.algorithm.heuristic;

import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * 泛化层次结构的树
 * @author shenxie
 * @date 2021/4/13
 */
@Data
public class GeneralizationTree {
    /**
     * 机器
     */
    private HostTree hosts;

    /**
     * 异常
     */
    private ExceptionTree exceptions;


    /**
     * 应用名
     */
    private AppTree apps;


    @Data
    static class HostTree {
        private String hostName;

        private Integer id;

        private Integer parentId;

        private List<HostTree> hosts;
    }

    @Data
    @AllArgsConstructor
    static class AppTree {
        private String appName;

        private Integer id;

        private Integer parentId;

        private String desc;

        private List<AppTree> apps;
    }

    @Data
    static class ExceptionTree {
        private String exception;

        private Integer id;

        private Integer parentId;

        private List<ExceptionTree> exceptions;

    }

    @Data
    static class MinParentNode {

        private List<ExceptionTree> exceptionChildNodes = Lists.newArrayList();

        /**
         * 原告警记录： 用于聚合告警
         */
        private List<AlertDto> alertDtos = Lists.newArrayList();

        private GeneralizationTree.ExceptionTree parentNode;

        /**
         * 不相似度
         */
        private Integer dissimilarity = 0;
    }

}

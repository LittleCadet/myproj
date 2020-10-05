/*
 * Copyright 2014 NAVER Corp.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.spring.app.springbatch.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.partition.support.Partitioner;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 重写Partitioner ： 自定义分片策略
 * @author shenxie
 */
@Service
public class AlarmPartitionerV2 implements Partitioner {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final String PARTITION_NAME_PREFIX = "alarm_partition_number_v2_";
    private static final String BATCH_NAME = "alarm_batch";


    @Autowired(required = false)
    @Qualifier("divider")
    private Divider divider = new DefaultDivider();

    @Override
    public Map<String, ExecutionContext> partition(int gridSize) {
        System.out.println("Partitioner.partition : gridSize : " + gridSize);
        return divider.divide(PARTITION_NAME_PREFIX, BATCH_NAME);
    }
}

package com.myproj.v2;

import java.util.Base64;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.flink.table.annotation.DataTypeHint;
import org.apache.flink.table.annotation.FunctionHint;
import org.apache.flink.table.functions.TableFunction;
import org.apache.flink.types.Row;
import org.apache.skywalking.apm.dependencies.com.google.protobuf.InvalidProtocolBufferException;
import org.apache.skywalking.apm.network.language.agent.v3.SegmentObject;

/**
 * 作用： 将原来的trace的消费逻辑转移到flink算子中： 好处如下：
 *  - c++ 的trace可采集： protobuf的格式： 支持跨语言， 跨平台。
 *  - 原trace的消费： 变成极速消费： apm的压测结果： 从553us => 68us
 * 用@FunctionHint && @DataTypeHint配合指定输入输出类型，
 * 需要注意的是： 最好将： output && collector && transformSql && sinkSql的字段顺序保持一致， 不然可能会启动报错
 * @author shenxie
 * @date 2024/5/21
 */
@Slf4j
@FunctionHint(input = @DataTypeHint("STRING"), output = @DataTypeHint("ROW<" +
        "   `id`            STRING, \n" +
        "   `trace_id`      STRING, \n" +
        "   `version`       BIGINT, \n" +
        "   `end_time`      BIGINT, \n" +
        "   `start_time`    BIGINT, \n" +
        "   `segment_id`    STRING, \n" +
        "   `data_binary`   STRING, \n" +
        "   `endpoint_id`   STRING, \n" +
        "   `statement`     STRING, \n" +
        "   `latency`       BIGINT, \n" +
        "   `time_bucket`   BIGINT, \n" +
        "   `service_instance_id`   STRING, \n" +
        "   `endpoint_name`         STRING, \n" +
        "   `is_error`              BIGINT, \n" +
        "   `tags`                  ARRAY<STRING>, \n" +
        "   `service_id`            STRING \n" +

">"))
public class SegmentRecordUdf extends TableFunction<Row> {

    public void eval(String message){
        byte[] decode = null;
        SegmentObject segmentObject = null;
        SegmentRecord segmentRecord = null;
        if(StringUtils.isEmpty(message)){
            return;
        }
        try{
            decode = Base64.getDecoder().decode(message);
        }catch (IllegalArgumentException e){
            log.error("base64解码异常：{}", message, e);
        }
        try{
            segmentObject = SegmentObject.parseFrom(decode);
        } catch (InvalidProtocolBufferException e) {
            log.error("protobuf解析异常:{}", e);
        }

        // 将segmentObject => SegmentRecord
            build(segmentObject, segmentRecord);
        // 构建collector
        setCollect(segmentRecord);
    }

    private void build(SegmentObject segmentObject, SegmentRecord segmentRecord){

    }

    private void setCollect(SegmentRecord segmentRecord){
        collect(Row.of(
                segmentRecord.getId(),
                segmentRecord.getTrace_id(),
                segmentRecord.getVersion(),
                segmentRecord.getEnd_time(),
                segmentRecord.getStart_time(),
                segmentRecord.getSegment_id(),
                segmentRecord.getData_binary(),
                segmentRecord.getEndpoint_id(),
                segmentRecord.getStatement(),
                segmentRecord.getLatency(),
                segmentRecord.getTime_bucket(),
                segmentRecord.getService_instance_id(),
                segmentRecord.getEndpoint_name(),
                segmentRecord.getIs_error(),
                segmentRecord.getTags(),
                segmentRecord.getService_id()
        ));
    }

}

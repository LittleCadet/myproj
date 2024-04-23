package com.myproj;

/**
 * @author shenxie
 * @date 2024/4/23
 */
public class AbstractSql implements Sql{

    protected static final String KAFKA_BROKER_ADDR = "http://172.20.2.38:9092";

    protected static final String ES_ADDR = "http://172.20.2.37:9200";

    protected static final String SEPARATOR = "====";

    protected static AbstractSql instance;

    protected static void init(AbstractSql abstractSql){
        instance = abstractSql;
    }

    protected static String[] getSourceSql(){
        return instance.sourceSql();
    }

    protected static String[] getTransformSql(){
        return instance.transformSql();
    }

    protected static String[] getSinkSql(){
        return instance.sinkSql();
    }

    @Override
    public String[] sourceSql() {
        return new String[]{
                "CREATE TABLE trace_source(\n" +
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
                        "   `tags`                  ARRAY<String>, \n" +
                        "   `service_id`            STRING \n" +
                        "   ) WITH ( \n" +
                        "       'connector' = 'kafka', \n" +
                        "       'topic' = 'apm-log-trace', \n" +
                        "       'properties.bootstrap.servers' = '" + KAFKA_BROKER_ADDR + "',\n" +
                        "       'properties.group.id' = 'flink.trace', \n" +
                        "       'scan.startup.mode' = 'latest-offset', \n" +
                        "       'format' = 'json'" +
                        "       )",

                "CREATE TABLE service_source(\n" +
                        "   `id`            STRING, \n" +
                        "   `node_type`     BIGINT, \n" +
                        "   `start_time`    BIGINT, \n" +
                        "   `name`          STRING\n" +
                        "   ) WITH ( \n" +
                        "       'connector' = 'kafka', \n" +
                        "       'topic' = 'apm-log-service-traffic', \n" +
                        "       'properties.bootstrap.servers' = '" + KAFKA_BROKER_ADDR + "',\n" +
                        "       'properties.group.id' = 'flink.service', \n" +
                        "       'scan.startup.mode' = 'latest-offset', \n" +
                        "       'format' = 'json'" +
                        "       )",

                "CREATE TABLE instance_source(\n" +
                        "   `id`            STRING, \n" +
                        "   `properties`    STRING, \n" +
                        "   `start_time`    BIGINT, \n" +
                        "   `name`          STRING, \n" +
                        "   `service_id`    STRING\n" +
                        "   ) WITH ( \n" +
                        "       'connector' = 'kafka', \n" +
                        "       'topic' = 'apm-log-instance-traffic', \n" +
                        "       'properties.bootstrap.servers' = '" + KAFKA_BROKER_ADDR + "',\n" +
                        "       'properties.group.id' = 'flink.instance', \n" +
                        "       'scan.startup.mode' = 'latest-offset', \n" +
                        "       'format' = 'json'" +
                        "       )"

        };
    }

    @Override
    public String[] transformSql() {
        return new String[]{
                "SELECT \n" +
                        "   `id`            , \n" +
                        "   `trace_id`      , \n" +
                        "   `version`       , \n" +
                        "   `end_time`      , \n" +
                        "   `start_time`    , \n" +
                        "   TO_DATE(FROM_UNIX(start_time/1000)) AS `timestamp`    , \n" +
                        "   `segment_id`    , \n" +
                        "   `data_binary`   , \n" +
                        "   `endpoint_id`   , \n" +
                        "   `statement`     , \n" +
                        "   `latency`       , \n" +
                        "   `time_bucket`   , \n" +
                        "   `service_instance_id`   , \n" +
                        "   `endpoint_name`         , \n" +
                        "   `is_error`              , \n" +
                        "   `tags`                  , \n" +
                        "   `service_id`             \n" +
                        "FROM trace_source",
                "SELECT \n" +
                        "   `id`             , \n" +
                        "   `node_type`      , \n" +
                        "   `start_time`     , \n" +
                        "   TO_DATE(FROM_UNIX(start_time/1000)) AS `timestamp`    , \n" +
                        "   `name`           \n" +
                        "FROM service_source",
                "SELECT \n" +
                        "   `id`            , \n" +
                        "   `properties`    , \n" +
                        "   `start_time`    , \n" +
                        "   TO_DATE(FROM_UNIX(start_time/1000)) AS `timestamp`    , \n" +
                        "   `name`          , \n" +
                        "   `service_id`    \n" +
                        "FROM instance_source",
        };
    }

    @Override
    public String[] sinkSql() {
        return new String[]{
                "CREATE TABLE trace_source(\n" +
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
                        "   `tags`                  ARRAY<String>, \n" + // flink支持数组
                        "   `service_id`            STRING, \n" +
                        "   PRIMARY KEY (`id`) NOT ENFORCED \n" + // flink支持用kafka的id 写到 es的metadata的_id中。
                        "   ) WITH ( \n" +
                        "       'connector' = 'elasticsearch-7', \n" +
                        "       'host' = '" + ES_ADDR + "',\n" +
                        "       'index' = 'segment-{timestamp|yyyy-MM-dd}', \n" + // 使用emss的索引命名规范， 用于索引删除。他们根据yyyy-MM-dd匹配删除。
                        "       'format' = 'json'" +
                        "       )",

                "CREATE TABLE service_source(\n" +
                        "   `id`            STRING, \n" +
                        "   `node_type`     BIGINT, \n" +
                        "   `start_time`    BIGINT, \n" +
                        "   `name`          STRING, \n" +
                        "   PRIMARY KEY (`id`) NOT ENFORCED \n" + // flink支持用kafka的id 写到 es的metadata的_id中。 用于： 更新数据
                        "   ) WITH ( \n" +
                        "       'connector' = 'elasticsearch-7', \n" +
                        "       'host' = '" + ES_ADDR + "',\n" +
                        "       'index' = 'segment-{timestamp|yyyy-MM-dd}', \n" +
                        "       'format' = 'json'" +
                        "       )",

                "CREATE TABLE instance_source(\n" +
                        "   `id`            STRING, \n" +
                        "   `properties`    STRING, \n" +
                        "   `start_time`    BIGINT, \n" +
                        "   `name`          STRING, \n" +
                        "   `service_id`    STRING, \n" +
                        "   PRIMARY KEY (`id`) NOT ENFORCED \n" + // flink支持用kafka的id 写到 es的metadata的_id中。 用于： 更新数据
                        "   ) WITH ( \n" +
                        "       'connector' = 'elasticsearch-7', \n" +
                        "       'host' = '" + ES_ADDR + "',\n" +
                        "       'index' = 'segment-{timestamp|yyyy-MM-dd}', \n" +
                        "       'format' = 'json'" +
                        "       )"
        };
    }
}

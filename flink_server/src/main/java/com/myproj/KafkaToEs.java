package com.myproj;

import com.myproj.v1.AbstractSql;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.table.api.StatementSet;
import org.apache.flink.table.api.Table;
import org.apache.flink.table.api.bridge.java.StreamStatementSet;
import org.apache.flink.table.api.bridge.java.StreamTableEnvironment;

/**
 * @author shenxie
 * @date 2024/4/23
 */
public class KafkaToEs extends AbstractSql {

    private static final Pattern CRAETE_TABLE_PATTERN = Pattern.compile("(?<=CREATE TABLE)\\s+(.*)(?=\\()");

    public static void main(String[] args) {
        init(new KafkaToEs());
        List<Table> transTables = new ArrayList<>();

        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        StreamTableEnvironment tableEnv = StreamTableEnvironment.create(env);
        StreamStatementSet statementSet = tableEnv.createStatementSet();
        Configuration config = tableEnv.getConfig().getConfiguration();
        config.setString("table.exec.sink.not-null-enforcer", "drop");

        // 执行sourceSql
        processSourceSql(tableEnv);

        // 执行transformSql
        processTransSql(tableEnv, transTables);

        // 执行sinkSql
        processSinkSql(tableEnv, transTables, statementSet);

        statementSet.execute();
    }

    private static void processSourceSql(StreamTableEnvironment tableEnv) {
        String[] sourceSqls = getSourceSql();
        for (String s : sourceSqls) {
            tableEnv.executeSql(s);
        }
    }

    private static void processTransSql(StreamTableEnvironment tableEnv, List<Table> transTables) {
        String[] transformSql = getTransformSql();
        for (String s : transformSql) {
            Table table = tableEnv.sqlQuery(s);
            transTables.add(table);
        }
    }

    private static void processSinkSql(StreamTableEnvironment tableEnv, List<Table> transTables, StatementSet statementSet) {
        String[] sinkSql = getSinkSql();
        for (int i = 0; i < sinkSql.length; i++) {
            String[] strings = sinkSql[i].split(SEPARATOR);
            for (String s : strings) {
                Matcher matcher = CRAETE_TABLE_PATTERN.matcher(sinkSql[i]);
                if (matcher.find()) {
                    String sinkTableName = matcher.group(1);
                    tableEnv.executeSql(s);
                    statementSet.addInsert(sinkTableName, transTables.get(i));
                }
            }
        }
    }
}
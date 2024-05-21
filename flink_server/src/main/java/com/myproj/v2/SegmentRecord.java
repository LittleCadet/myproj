package com.myproj.v2;

import lombok.Data;

/**
 * @author shenxie
 * @date 2024/5/21
 */
@Data
public class SegmentRecord {

    public String trace_id;

    public String endpoint_name;

    public Long latency;

    public Long end_time;

    public String endpoint_id;

    public String service_instance_id;

    public Long version;

    public String[] tags;

    public Long start_time;

    public String data_binary;

    public String service_id;

    public String statement;

    public Long time_bucket;

    public Long is_error;

    public String segment_id;

    public String id;
}

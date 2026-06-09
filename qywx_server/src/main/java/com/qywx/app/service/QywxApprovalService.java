package com.qywx.app.service;

import com.alibaba.fastjson.JSON;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class QywxApprovalService {

    private static final String CORP_ID = "ww0c7dd8c188047f58";
    private static final String CORP_SECRET = "mkvqJKUGlquF1RyWgjkYQsedtJc3I3b2I3LQ48Mus70";
    private static final String TEMPLATE_ID = "C4ejkW5VSwZbmDFw1vGUuCrmF1Mfzh9Z9AUEpHh8m";
    private static final String APPLY_EVENT_URL = "https://qyapi.weixin.qq.com/cgi-bin/oa/applyevent?access_token=";
    private static final String GET_TOKEN_URL = "https://qyapi.weixin.qq.com/cgi-bin/gettoken";
    private static final MediaType JSON_MEDIA_TYPE = MediaType.parse("application/json; charset=utf-8");

    private OkHttpClient httpClient;

    public QywxApprovalService() {
    }

    QywxApprovalService(OkHttpClient httpClient) {
        this.httpClient = httpClient;
    }

    @PostConstruct
    public void init() {
        if (httpClient == null) {
            httpClient = new OkHttpClient();
        }
    }

    public String getAccessToken() throws IOException {
        Request request = new Request.Builder()
                .url(GET_TOKEN_URL + "?corpid=" + CORP_ID + "&corpsecret=" + CORP_SECRET)
                .get()
                .build();

        Response response = httpClient.newCall(request).execute();
        try {
            if (response.body() == null) {
                System.out.println("获取accessToken异常");
                return "";
            }
            return JSON.parseObject(response.body().string()).getString("access_token");
        } finally {
            response.close();
        }
    }

    public String createApproval() throws IOException {
        String accessToken= getAccessToken();
        RequestBody requestBody = RequestBody.create(JSON_MEDIA_TYPE, JSON.toJSONString(buildApplyEventRequest()));
        Request request = new Request.Builder()
                .url(APPLY_EVENT_URL + accessToken)
                .post(requestBody)
                .build();

        Response response = httpClient.newCall(request).execute();
        try {
            if (response.body() == null) {
                System.out.println("提交申请异常");
                return "";
            }
            return response.body().string();
        } finally {
            response.close();
        }
    }

    private Map<String, Object> buildApplyEventRequest() {
        Map<String, Object> request = new HashMap<String, Object>();
        request.put("creator_userid", "xie.shen@sjstack.cn");
        request.put("template_id", TEMPLATE_ID);
        request.put("use_template_approver", 0);
        request.put("choose_department", 2);
        request.put("process", buildProcess());
        request.put("apply_data", buildApplyData());
        request.put("summary_list", buildSummaryList());
        return request;
    }

    private Map<String, Object> buildProcess() {
        Map<String, Object> process = new HashMap<String, Object>();
        List<Map<String, Object>> nodeList = new ArrayList<Map<String, Object>>();
        nodeList.add(buildProcessNode(Arrays.asList("xie.shen@sjstack.cn", "xie.shen@sjstack.cn")));
        nodeList.add(buildProcessNode(Arrays.asList("xie.shen@sjstack.cn")));
        nodeList.add(buildProcessNode(Arrays.asList("xie.shen@sjstack.cn")));
        process.put("node_list", nodeList);
        return process;
    }

    private Map<String, Object> buildProcessNode(List<String> userIds) {
        Map<String, Object> node = new HashMap<String, Object>();
        node.put("type", 1);
        node.put("apv_rel", 1);
        node.put("userid", userIds);
        return node;
    }

    private Map<String, Object> buildApplyData() {
        Map<String, Object> applyData = new HashMap<String, Object>();
        List<Map<String, Object>> contents = new ArrayList<Map<String, Object>>();

        Map<String, Object> content = new HashMap<String, Object>();
        content.put("control", "Text");
        content.put("id", "Text-15111111111");

        Map<String, Object> value = new HashMap<String, Object>();
        value.put("text", "文本填写的内容");
        content.put("value", value);

        contents.add(content);

        Map<String, Object> content2 = new HashMap<String, Object>();
        content.put("control", "Selector");
        content.put("id", "Text-15111111111");

        Map<String, Object> value2 = new HashMap<String, Object>();
        value.put("同意", "1");
        value.put("不同意", "0");
        content.put("value", value2);

        contents.add(content);
        contents.add(content2);
        applyData.put("contents", contents);
        return applyData;
    }

    private List<Map<String, Object>> buildSummaryList() {
        List<Map<String, Object>> summaryList = new ArrayList<Map<String, Object>>();
        summaryList.add(buildSummary("摘要第1行"));
        summaryList.add(buildSummary("摘要第2行"));
        summaryList.add(buildSummary("摘要第3行"));
        return summaryList;
    }

    private Map<String, Object> buildSummary(String text) {
        Map<String, Object> summary = new HashMap<String, Object>();
        List<Map<String, Object>> summaryInfo = new ArrayList<Map<String, Object>>();

        Map<String, Object> item = new HashMap<String, Object>();
        item.put("text", text);
        item.put("lang", "zh_CN");
        summaryInfo.add(item);

        summary.put("summary_info", summaryInfo);
        return summary;
    }
}

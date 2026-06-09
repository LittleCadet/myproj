package com.qywx.app.service;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class QywxApprovalServiceTest {

    @Test
    public void getAccessTokenReturnsTokenFromQywxResponse() throws Exception {
        final String[] requestedUrl = new String[1];
        OkHttpClient httpClient = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        requestedUrl[0] = chain.request().url().toString();
                        return new Response.Builder()
                                .request(chain.request())
                                .protocol(Protocol.HTTP_1_1)
                                .code(200)
                                .message("OK")
                                .body(ResponseBody.create(
                                        MediaType.parse("application/json; charset=utf-8"),
                                        "{\"errcode\":0,\"errmsg\":\"ok\",\"access_token\":\"token-from-qywx\",\"expires_in\":7200}"
                                ))
                                .build();
                    }
                })
                .build();
        QywxApprovalService qywxApprovalService = new QywxApprovalService(httpClient);

        String accessToken = qywxApprovalService.getAccessToken();

        assertEquals("token-from-qywx", accessToken);
        assertEquals("https://qyapi.weixin.qq.com/cgi-bin/gettoken?corpid=ww0c7dd8c188047f58&corpsecret=mkvqJKUGlquF1RyWgjkYQsedtJc3I3b2I3LQ48Mus70", requestedUrl[0]);
    }
}

package com.qywx.app.controller;

import com.qywx.app.service.QywxApprovalService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.context.TestPropertySource;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(QywxController.class)
@TestPropertySource(properties = {
        "qywx.callback.token=QDG6eK",
        "qywx.callback.encoding-aes-key=jWmYm7qr5nMoAUwZRjGtBxmz3KA1tkAj3ykkR6q2B2C",
        "qywx.callback.corp-id=wx5823bf96d3bd56c7"
})
public class QywxControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private QywxApprovalService qywxApprovalService;

    @Test
    public void createReturnsApprovalServiceResponse() throws Exception {
        given(qywxApprovalService.createApproval()).willReturn("{\"errcode\":0}");

        mockMvc.perform(post("/qywx/create"))
                .andExpect(status().isOk())
                .andExpect(content().string("{\"errcode\":0}"));
    }

    @Test
    public void callbackReturnsSuccess() throws Exception {
        String postData = "<xml><ToUserName><![CDATA[wx5823bf96d3bd56c7]]></ToUserName><Encrypt><![CDATA[RypEvHKD8QQKFhvQ6QleEB4J58tiPdvo+rtK1I9qca6aM/wvqnLSV5zEPeusUiX5L5X/0lWfrf0QADHHhGd3QczcdCUpj911L3vg3W/sYYvuJTs3TUUkSUXxaccAS0qhxchrRYt66wiSpGLYL42aM6A8dTT+6k4aSknmPj48kzJs8qLjvd4Xgpue06DOdnLxAUHzM6+kDZ+HMZfJYuR+LtwGc2hgf5gsijff0ekUNXZiqATP7PF5mZxZ3Izoun1s4zG4LUMnvw2r+KqCKIw+3IQH03v+BCA9nMELNqbSf6tiWSrXJB3LAVGUcallcrw8V2t9EL4EhzJWrQUax5wLVMNS0+rUPA3k22Ncx4XXZS9o0MBH27Bo6BpNelZpS+/uh9KsNlY6bHCmJU9p8g7m3fVKn28H3KDYA5Pl/T8Z1ptDAVe0lXdQ2YoyyH2uyPIGHBZZIs2pDBS8R07+qN+E7Q==]]></Encrypt><AgentID><![CDATA[218]]></AgentID></xml>";

        mockMvc.perform(post("/qywx/callback")
                        .param("msg_signature", "477715d11cdb4164915debcba66cb864d751f3e6")
                        .param("timestamp", "1409659813")
                        .param("nonce", "1372623149")
                        .content(postData)
                        .contentType(MediaType.APPLICATION_XML))
                .andExpect(status().isOk())
                .andExpect(content().string("success"));
    }

    @Test
    public void callbackRejectsInvalidMessageSignature() throws Exception {
        String postData = "<xml><ToUserName><![CDATA[wx5823bf96d3bd56c7]]></ToUserName><Encrypt><![CDATA[RypEvHKD8QQKFhvQ6QleEB4J58tiPdvo+rtK1I9qca6aM/wvqnLSV5zEPeusUiX5L5X/0lWfrf0QADHHhGd3QczcdCUpj911L3vg3W/sYYvuJTs3TUUkSUXxaccAS0qhxchrRYt66wiSpGLYL42aM6A8dTT+6k4aSknmPj48kzJs8qLjvd4Xgpue06DOdnLxAUHzM6+kDZ+HMZfJYuR+LtwGc2hgf5gsijff0ekUNXZiqATP7PF5mZxZ3Izoun1s4zG4LUMnvw2r+KqCKIw+3IQH03v+BCA9nMELNqbSf6tiWSrXJB3LAVGUcallcrw8V2t9EL4EhzJWrQUax5wLVMNS0+rUPA3k22Ncx4XXZS9o0MBH27Bo6BpNelZpS+/uh9KsNlY6bHCmJU9p8g7m3fVKn28H3KDYA5Pl/T8Z1ptDAVe0lXdQ2YoyyH2uyPIGHBZZIs2pDBS8R07+qN+E7Q==]]></Encrypt><AgentID><![CDATA[218]]></AgentID></xml>";

        mockMvc.perform(post("/qywx/callback")
                        .param("msg_signature", "invalid")
                        .param("timestamp", "1409659813")
                        .param("nonce", "1372623149")
                        .content(postData)
                        .contentType(MediaType.APPLICATION_XML))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void callbackVerifyUrlReturnsDecryptedEchoString() throws Exception {
        mockMvc.perform(get("/qywx/callback")
                        .param("msg_signature", "5c45ff5e21c57e6ad56bac8758b79b1d9ac89fd3")
                        .param("timestamp", "1409659589")
                        .param("nonce", "263014780")
                        .param("echostr", "P9nAzCzyDtyTWESHep1vC5X9xho/qYX3Zpb4yKa9SKld1DsH3Iyt3tP3zNdtp+4RPcs8TgAE7OaBO+FZXvnaqQ=="))
                .andExpect(status().isOk())
                .andExpect(content().string("1616140317555161061"));
    }

    @Test
    public void callbackVerifyUrlRejectsInvalidSignature() throws Exception {
        mockMvc.perform(get("/qywx/callback")
                        .param("msg_signature", "invalid")
                        .param("timestamp", "1409659589")
                        .param("nonce", "263014780")
                        .param("echostr", "P9nAzCzyDtyTWESHep1vC5X9xho/qYX3Zpb4yKa9SKld1DsH3Iyt3tP3zNdtp+4RPcs8TgAE7OaBO+FZXvnaqQ=="))
                .andExpect(status().isBadRequest());
    }
}

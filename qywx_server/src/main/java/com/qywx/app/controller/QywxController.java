package com.qywx.app.controller;

import com.qywx.app.service.QywxCallbackCrypto;
import com.qywx.app.service.QywxApprovalService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/qywx")
public class QywxController {

    private final QywxApprovalService qywxApprovalService;
    private final QywxCallbackCrypto qywxCallbackCrypto;

    @Value("${qywx.callback.token:}")
    private String callbackToken;

    @Value("${qywx.callback.encoding-aes-key:}")
    private String callbackEncodingAesKey;

    @Value("${qywx.callback.corp-id:}")
    private String callbackCorpId;

    public QywxController(QywxApprovalService qywxApprovalService) {
        this.qywxApprovalService = qywxApprovalService;
        this.qywxCallbackCrypto = new QywxCallbackCrypto();
    }

    @PostMapping("/create")
    public String create() throws IOException {
        return qywxApprovalService.createApproval();
    }

    @PostMapping("/callback")
    public ResponseEntity<String> receiveCallback(@RequestParam(value = "msg_signature", required = false) String msgSignature,
                                                  @RequestParam(value = "timestamp", required = false) String timestamp,
                                                  @RequestParam(value = "nonce", required = false) String nonce,
                                                  @RequestBody(required = false) String postData) {
        try {
            String message = qywxCallbackCrypto.decryptMessage(
                    callbackToken,
                    callbackEncodingAesKey,
                    callbackCorpId,
                    msgSignature,
                    timestamp,
                    nonce,
                    postData
            );
            System.out.println("callback消息解析: " + message);
            return ResponseEntity.ok("success");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("invalid qywx callback message");
        }
    }


    @GetMapping("/callback")
    public ResponseEntity<String> verifyCallback(
            @RequestParam(value = "msg_signature", required = false) String msgSignature,
            @RequestParam(value = "timestamp",required = false) String timestamp,
            @RequestParam(value = "nonce",required = false) String nonce,
            @RequestParam(value = "echostr",required = false) String echostr
    ) throws Exception {
        try {
            String echoText = qywxCallbackCrypto.verifyUrl(
                    callbackToken,
                    callbackEncodingAesKey,
                    callbackCorpId,
                    msgSignature,
                    timestamp,
                    nonce,
                    echostr
            );
            System.out.println("verifyCallback: " + echoText);
            return ResponseEntity.ok(echoText);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("invalid qywx callback signature");
        }
    }
}

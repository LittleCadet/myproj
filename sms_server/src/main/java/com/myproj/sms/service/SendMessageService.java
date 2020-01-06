package com.myproj.sms.service;

import cn.hutool.core.date.DateUtil;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @Description: 发送验证码
 * @Author: shenxie
 * @CreateDate: 2020/1/4
 * @UpdateUser:
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */
@Slf4j
@Service
public class SendMessageService {

    @Value("${spring.sms.domain}")
    private String domain;
    @Value("${spring.sms.version}")
    private String version;
    @Value("${spring.sms.regionId}")
    private String regionId;
    @Value("${spring.sms.signName}")
    private String signName;
    @Value("${spring.sms.templateCode}")
    private String templateCode;
    @Value("${spring.sms.accessKeyId}")
    private String accessKeyId;
    @Value("${spring.sms.secret}")
    private String secret;

    /*@Resource
    private RedisClient redisClient;*/

    public void sendVerifyCode(String phoneNumber){
        DefaultProfile profile = DefaultProfile.getProfile(regionId, accessKeyId, secret);
        IAcsClient client = new DefaultAcsClient(profile);
        CommonResponse response = null;
        CommonRequest request = new CommonRequest();
        //六位随机数
        int random = (int)((Math.random()*9+1)*100000);
        request.setMethod(MethodType.POST);
        request.setDomain(domain);
        request.setVersion("2017-05-25");
        request.setAction("SendSms");

        Map<String, String> params = new HashMap<>();
        params.put("RegionId",regionId);
        params.put("PhoneNumbers",phoneNumber);
        params.put("SignName",signName);
        params.put("TemplateCode",templateCode);
        params.put("TemplateParam",String.valueOf(random));

        Set<Map.Entry<String, String>> entrySet = params.entrySet();

        for (Map.Entry<String, String> param : entrySet) {
            if (param.getKey().equals("TemplateParam")) {
                request.putQueryParameter(param.getKey(), "{\"code\":" + "\"" + param.getValue() + "\"}");
            } else {
                request.putQueryParameter(param.getKey(), param.getValue());
            }
        }
        try {
            //发送验证码
            response = client.getCommonResponse(request);
            log.info("response:{}",response.getData());

            Thread.sleep(2*1000);

            //获取发送的验证码
            String code = getMessage(phoneNumber);
            //String result = redisClient.set(CodeConstants.VERIFY_CODE + phoneNumber,code,300);
            //log.info("result:" + result);
        } catch (ClientException | InterruptedException e) {
            log.error("failed to send verify code,e:{}",e.getMessage());
        }
    }

    public String getMessage(String phoneNumber){
        DefaultProfile profile = DefaultProfile.getProfile(regionId, accessKeyId, secret);
        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        String code = null;
        request.setMethod(MethodType.POST);
        request.setDomain(domain);
        request.setVersion("2017-05-25");
        request.setAction("QuerySendDetails");
        request.putQueryParameter("RegionId", regionId);
        request.putQueryParameter("PhoneNumber", phoneNumber);
        request.putQueryParameter("SendDate", DateUtil.format(new Date(),"yyyyMMdd"));
        request.putQueryParameter("PageSize", "100");
        request.putQueryParameter("CurrentPage", "1");
        try {
            CommonResponse response = client.getCommonResponse(request);
            String[] params = response.getData().split("，");
            code = params[0].substring(params[0].length() -6);
            log.info("phoneNumber:{},code:{}",phoneNumber,code);
        } catch (ClientException e) {
            log.error("failed to get code,e:{}",e.getMessage());
        }

        return code;
    }

    /**
     * 验证验证码是否正确
     * @param phoneNumber
     * @param code
     * @return
     */
    /*public BaseResponse<Integer> getCache(String phoneNumber, String code){
        if(code.equals(redisClient.get(CodeConstants.VERIFY_CODE + phoneNumber))){
            return new BaseResponse(1);
        }
        return new BaseResponse(0);
    }*/

}

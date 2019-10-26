/**
 *
 */
package com.fuiou.mchnt.entry.demo;

import java.io.*;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.text.Collator;
import java.util.*;

import javax.xml.bind.JAXBException;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.ZipUtil;
import com.alibaba.fastjson.JSONObject;
import com.fuiou.mchnt.entry.dto.ConfirmDto;
import com.fuiou.mchnt.entry.dto.MchntAddReqDto;
import com.fuiou.mchnt.entry.dto.MchntUpdReqDto;
import com.fuiou.mchnt.entry.dto.MerchantResponse;
import com.fuiou.mchnt.entry.exception.FieldException;
import com.fuiou.mchnt.entry.insEntry.dto.webankConfig.demo.WebankWechatConfigRetDto;
import com.fuiou.mchnt.entry.insEntry.util.HttpClientUtil;
import com.fuiou.mchnt.entry.util.*;
import com.google.gson.Gson;
import javafx.util.Pair;
import org.apache.commons.lang3.tuple.ImmutablePair;

/**
 * @project entry
 * @description
 * @author abel.li
 * @creation 2017年3月14日
 * @email
 * @version
 */
public class MchntTest {

    public static void main(String[] args) {
        //企业商户对公非法人结算
        //mchntAdd_0_1_0();
        //企业商户对公法人结算
        //mchntAdd_0_1_1();
        //企业商户对私非法人结算
        //mchntAdd_0_2_0();
        //企业商户对私法人结算
        //mchntAdd_0_2_1();

        //个体工商户对公非法人结算
        //getB_1_0();
        //个体工商户对公法人结算
        //getB_1_1();
        //个体工商户对私非法人结算
        //getB_2_0();
        //个体工商户对私法人结算
        //getB_2_1();

        //小微商户
        String traceNo = "1857946857111";
        String mchntName = "商户名LittleCadet111";
        ImmutablePair<MerchantResponse, String> pair = mchntAdd_A(traceNo, mchntName);

        //企业商户，对公
        //mchntUpd();

        //图片上传

        if (null != pair && null != pair.getLeft().getRet_code() && "0000".equals(pair.getLeft().getRet_code())) {
            upload(pair.getLeft().getFy_mchnt_cd());
            download(pair.getLeft().getFy_mchnt_cd());
            confirm(traceNo, pair.getLeft().getFy_mchnt_cd());
        }


    }

    /**
     * 企业商户，对公
     */
    /*protected static void mchntUpd() {
        MchntUpdReqDto reqDto = new MchntUpdReqDto();
        reqDto.setTrace_no("111111111111");
        reqDto.setIns_cd("08A9999999");
        //商户名，简称，真实姓名：不能包含“test”和“测试”这些敏感字段
        //商户名不能重复：不管操作成功还是失败
        reqDto.setMchnt_name("商户名LittleCadet7");
        reqDto.setMchnt_shortname("商户简称");
        reqDto.setReal_name("真实姓名");
        reqDto.setCertif_id("340111199607082547");
        reqDto.setCertif_id_expire_dt("20191122");
        reqDto.setArtif_nm("证件名");
        reqDto.setContact_person("联系人");
        reqDto.setContact_email("qq@qq.com");
        reqDto.setContact_phone("18577485847");
        reqDto.setContact_mobile("18577485847");
        reqDto.setAcnt_type("1"); //对私
        reqDto.setCity_cd("3310");
        reqDto.setCounty_cd("3310");
        reqDto.setContact_addr("杭州市");
        //入账卡开户行名称
        reqDto.setInter_bank_no("323596001013");
        //入账卡户名
        reqDto.setAcnt_nm("真实姓名");
        //入账卡账号
        reqDto.setAcnt_no("340114785895288");
        reqDto.setAcnt_certif_id("340111199608058747");
        reqDto.setSet_cd("M0008");
        //经营范围的代码
        reqDto.setBusiness("545");
        reqDto.setSettle_amt("132");
        //清算类型
        reqDto.setSettle_tp("1");
        reqDto.setLicense_type("0");
        //证件号码
        reqDto.setLicense_no("11222");
        reqDto.setLicense_expire_dt("20221023");
        reqDto.setLicense_start_dt("20101023");
        reqDto.setContact_cert_no("132456789123456789");
        reqDto.setCard_start_dt("20191022");
        reqDto.setLic_regis_addr("杭州市");
        reqDto.setAcnt_certif_expire_dt("20221023");


        try {
            FieldVerifyUtil.checkField(reqDto);
        } catch (FieldException e1) {
            e1.printStackTrace();
        } catch (Exception e1) {
            e1.printStackTrace();
        }

        String signPacket = SignUtil.generateSign(reqDto);
        System.out.println("------signPacket:" + signPacket);

        String sign = Md5.MD5Encode(signPacket + "&key=040f23510fbf4b34ae3895272e83c58c", "GBK");
        System.out.println("------sign:" + sign);

        reqDto.setSign(sign);

        try {
            String xmlReport = JaxbUtil.toXml(reqDto, MchntUpdReqDto.class, "UTF-8");

            System.out.println("------xmlReport:" + xmlReport);

            String encodedXml = URLEncoder.encode(URLEncoder.encode(xmlReport, "GBK"), "GBK");

            String retResult = RequestUtil.doRequest("http://www-1.fuiou.com:28090/wmp/wxMchntMng.fuiou?action=wxMchntAdd", RequestUtil.Method.POST, "GBK", "req=" + encodedXml);

            retResult = URLDecoder.decode(retResult, "GBK");

            System.out.println("------retResult:" + retResult);

        } catch (JAXBException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }*/

    /**
     * 小微商户
     */
    protected static ImmutablePair<MerchantResponse, String> mchntAdd_A(String traceNo, String mchntName) {
        MerchantResponse response = null;
        BufferedWriter bw = null;
        File file = null;
        MchntAddReqDto reqDto = new MchntAddReqDto();
        reqDto.setAcnt_artif_flag("1");
        reqDto.setAcnt_certif_expire_dt("20221023");
        reqDto.setAcnt_certif_tp("0");
        reqDto.setArtif_nm("王金娥");
        reqDto.setTrace_no(traceNo);
        reqDto.setIns_cd("08A9999999");
        reqDto.setMchnt_name(mchntName);
        reqDto.setMchnt_shortname("商户简称");
        reqDto.setReal_name("王金娥");
        reqDto.setCertif_id("34012119790201192x");
        reqDto.setCertif_id_expire_dt("20191122");
        reqDto.setContact_person("联系人");
        reqDto.setContact_email("qq@qq.com");
        reqDto.setContact_phone("18577485847");
        reqDto.setContact_mobile("18577485847");
        reqDto.setAcnt_type("2"); //2:对私 1:对公
        reqDto.setCity_cd("8380");
        reqDto.setCounty_cd("8380");
        reqDto.setContact_addr("杭州市");
        //入账卡开户行名称
        reqDto.setInter_bank_no("323596001013");
        //入账卡户名
        reqDto.setAcnt_nm("王金娥");
        //入账卡账号
        reqDto.setAcnt_no("6217993640004729261");
        reqDto.setAcnt_certif_id("34012119790201192x");
        reqDto.setSet_cd("M0008");
        //经营范围的代码
        reqDto.setBusiness("545");
        reqDto.setSettle_amt("132");
        //清算类型
        reqDto.setSettle_tp("1");
        //角色类型
        reqDto.setLicense_type("A");
        //证件号码
        reqDto.setLicense_no("11222");
        reqDto.setLicense_expire_dt("20221023");
        reqDto.setLicense_start_dt("20101023");
        reqDto.setContact_cert_no("132456789123456789");
        reqDto.setCard_start_dt("20191022");
        reqDto.setLic_regis_addr("杭州市");

        try {
            FieldVerifyUtil.checkField(reqDto);
        } catch (FieldException e1) {
            e1.printStackTrace();
        } catch (Exception e1) {
            e1.printStackTrace();
        }

        String signPacket = SignUtil.generateSign(reqDto);
        System.out.println("------signPacket:" + signPacket);

        String sign = Md5.MD5Encode(signPacket + "&key=040f23510fbf4b34ae3895272e83c58c", "GBK");
        System.out.println("------sign:" + sign);

        reqDto.setSign(sign);

        try {
            String xmlReport = JaxbUtil.toXml(reqDto, MchntAddReqDto.class, "UTF-8");

            System.out.println("------xmlReport:" + xmlReport);

            String encodedXml = URLEncoder.encode(URLEncoder.encode(xmlReport, "GBK"), "GBK");

            String retResult = RequestUtil.doRequest("http://www-1.fuiou.com:28090/wmp/wxMchntMng.fuiou?action=wxMchntAdd", RequestUtil.Method.POST, "GBK", "req=" + encodedXml, 1);

            retResult = URLDecoder.decode(retResult, "GBK");

            System.out.println("------retResult:" + retResult);

            file = new File("C:\\Users\\Administrator\\Desktop\\富友\\zip\\xml\\object.xml");
            bw = new BufferedWriter(new FileWriter(file));
            bw.write(retResult);

        } catch (JAXBException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != bw)
                    bw.close();
                //Thread.sleep(1000);
                response = JaxbUtil.readString(MerchantResponse.class, file.getPath());
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JAXBException e) {
                e.printStackTrace();
            }
        }

        return ImmutablePair.of(response, sign);
    }

    /**
     * 企业商户对公非法人结算
     */
    /*protected static void mchntAdd_0_1_0() {
        MchntAddReqDto reqDto = new MchntAddReqDto();
        reqDto.setTrace_no("111111111111");
        reqDto.setIns_cd("08A9999999");
        //商户名，简称，真实姓名：不能包含“test”和“测试”这些敏感字段
        //商户名不能重复：不管操作成功还是失败
        reqDto.setMchnt_name("商户名LittleCadet60");
        reqDto.setMchnt_shortname("商户简称");
        reqDto.setReal_name("真实姓名");
        reqDto.setCertif_id("340111199607082547");
        reqDto.setCertif_id_expire_dt("20191122");
        reqDto.setArtif_nm("证件名");
        reqDto.setContact_person("联系人");
        reqDto.setContact_email("qq@qq.com");
        reqDto.setContact_phone("18577485847");
        reqDto.setContact_mobile("18577485847");
        reqDto.setAcnt_type("1"); //对私
        reqDto.setCity_cd("3310");
        reqDto.setCounty_cd("3310");
        reqDto.setContact_addr("杭州市");
        //入账卡开户行名称
        reqDto.setInter_bank_no("323596001013");
        //入账卡户名
        reqDto.setAcnt_nm("真实姓名");
        //入账卡账号
        reqDto.setAcnt_no("340114785895288");
        reqDto.setAcnt_certif_id("340111199608058747");
        reqDto.setSet_cd("M0008");
        //经营范围的代码
        reqDto.setBusiness("545");
        reqDto.setSettle_amt("132");
        //清算类型
        reqDto.setSettle_tp("1");
        reqDto.setLicense_type("0");
        //证件号码
        reqDto.setLicense_no("11222");
        reqDto.setLicense_expire_dt("20221023");
        reqDto.setLicense_start_dt("20101023");
        reqDto.setContact_cert_no("132456789123456789");
        reqDto.setCard_start_dt("20191022");
        reqDto.setLic_regis_addr("杭州市");
        reqDto.setAcnt_certif_expire_dt("20221023");
        //法人入账标识：0：非法人，1：法人
        reqDto.setAcnt_artif_flag("0");
        //省份证号：非法人毕传 （acnt_artif_flag = 0）
        reqDto.setAcnt_certif_tp("0");

        try {
            FieldVerifyUtil.checkField(reqDto);
        } catch (FieldException e1) {
            e1.printStackTrace();
        } catch (Exception e1) {
            e1.printStackTrace();
        }

        String signPacket = SignUtil.generateSign(reqDto);
        System.out.println("------signPacket:" + signPacket);

        String sign = Md5.MD5Encode(signPacket + "&key=040f23510fbf4b34ae3895272e83c58c", "GBK");
        System.out.println("------sign:" + sign);

        reqDto.setSign(sign);

        try {
            String xmlReport = JaxbUtil.toXml(reqDto, MchntAddReqDto.class, "UTF-8");

            System.out.println("------xmlReport:" + xmlReport);

            String encodedXml = URLEncoder.encode(URLEncoder.encode(xmlReport, "GBK"), "GBK");

            String retResult = RequestUtil.doRequest("http://www-1.fuiou.com:28090/wmp/wxMchntMng.fuiou?action=wxMchntAdd", RequestUtil.Method.POST, "GBK", "req=" + encodedXml);

            retResult = URLDecoder.decode(retResult, "GBK");

            System.out.println("------retResult:" + retResult);

        } catch (JAXBException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/

    /**
     * 企业商户对公法人结算
     */
    /*protected static void mchntAdd_0_1_1() {
        MchntAddReqDto reqDto = new MchntAddReqDto();
        reqDto.setTrace_no("111111111111");
        reqDto.setIns_cd("08A9999999");
        //商户名，简称，真实姓名：不能包含“test”和“测试”这些敏感字段
        //商户名不能重复：不管操作成功还是失败
        reqDto.setMchnt_name("商户名LittleCadet23");
        reqDto.setMchnt_shortname("商户简称");
        reqDto.setReal_name("真实姓名");
        reqDto.setCertif_id("340111199607082547");
        reqDto.setCertif_id_expire_dt("20191122");
        reqDto.setArtif_nm("证件名");
        reqDto.setContact_person("联系人");
        reqDto.setContact_email("qq@qq.com");
        reqDto.setContact_phone("18577485847");
        reqDto.setContact_mobile("18577485847");
        reqDto.setAcnt_type("1"); //对私
        reqDto.setCity_cd("3310");
        reqDto.setCounty_cd("3310");
        reqDto.setContact_addr("杭州市");
        //入账卡开户行名称
        reqDto.setInter_bank_no("323596001013");
        //入账卡户名
        reqDto.setAcnt_nm("真实姓名");
        //入账卡账号
        reqDto.setAcnt_no("340114785895288");
        reqDto.setAcnt_certif_id("340111199608058747");
        reqDto.setSet_cd("M0008");
        //经营范围的代码
        reqDto.setBusiness("545");
        reqDto.setSettle_amt("132");
        //清算类型
        reqDto.setSettle_tp("1");
        reqDto.setLicense_type("0");
        //证件号码
        reqDto.setLicense_no("11222");
        reqDto.setLicense_expire_dt("20221023");
        reqDto.setLicense_start_dt("20101023");
        reqDto.setContact_cert_no("132456789123456789");
        reqDto.setCard_start_dt("20191022");
        reqDto.setLic_regis_addr("杭州市");
        reqDto.setAcnt_certif_expire_dt("20221023");
        //法人入账标识：0：非法人，1：法人
        reqDto.setAcnt_artif_flag("1");
        //省份证号：非法人毕传 （acnt_artif_flag = 0）
        reqDto.setAcnt_certif_tp("0");

        try {
            FieldVerifyUtil.checkField(reqDto);
        } catch (FieldException e1) {
            e1.printStackTrace();
        } catch (Exception e1) {
            e1.printStackTrace();
        }

        String signPacket = SignUtil.generateSign(reqDto);
        System.out.println("------signPacket:" + signPacket);

        String sign = Md5.MD5Encode(signPacket + "&key=040f23510fbf4b34ae3895272e83c58c", "GBK");
        System.out.println("------sign:" + sign);

        reqDto.setSign(sign);

        try {
            String xmlReport = JaxbUtil.toXml(reqDto, MchntAddReqDto.class, "UTF-8");

            System.out.println("------xmlReport:" + xmlReport);

            String encodedXml = URLEncoder.encode(URLEncoder.encode(xmlReport, "GBK"), "GBK");

            String retResult = RequestUtil.doRequest("http://www-1.fuiou.com:28090/wmp/wxMchntMng.fuiou?action=wxMchntAdd", RequestUtil.Method.POST, "GBK", "req=" + encodedXml);

            retResult = URLDecoder.decode(retResult, "GBK");

            System.out.println("------retResult:" + retResult);

        } catch (JAXBException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/

    /**
     * 企业商户对私法人结算
     */
    /*protected static void mchntAdd_0_2_1() {
        MchntAddReqDto reqDto = new MchntAddReqDto();
        reqDto.setTrace_no("111111111111");
        reqDto.setIns_cd("08A9999999");
        //商户名，简称，真实姓名：不能包含“test”和“测试”这些敏感字段
        //商户名不能重复：不管操作成功还是失败
        reqDto.setMchnt_name("商户名LittleCadet25");
        reqDto.setMchnt_shortname("商户简称");
        reqDto.setReal_name("王金娥");
        reqDto.setCertif_id("34012119790201192x");
        reqDto.setCertif_id_expire_dt("20191122");
        reqDto.setArtif_nm("王金娥");
        reqDto.setContact_person("联系人");
        reqDto.setContact_email("qq@qq.com");
        reqDto.setContact_phone("18577485847");
        reqDto.setContact_mobile("18577485847");
        reqDto.setAcnt_type("2"); //1：对公；2：对私;
        reqDto.setCity_cd("3310");
        reqDto.setCounty_cd("3310");
        reqDto.setContact_addr("杭州市");
        //入账卡开户行名称
        reqDto.setInter_bank_no("323596001013");
        //入账卡户名
        reqDto.setAcnt_nm("王金娥");
        //入账卡账号
        reqDto.setAcnt_no("6217993640004729261");
        reqDto.setAcnt_certif_id("34012119790201192x");
        reqDto.setSet_cd("M0008");
        //经营范围的代码
        reqDto.setBusiness("545");
        reqDto.setSettle_amt("132");
        //清算类型
        reqDto.setSettle_tp("1");
        reqDto.setLicense_type("0");
        //证件号码
        reqDto.setLicense_no("11222");
        reqDto.setLicense_expire_dt("20221023");
        reqDto.setLicense_start_dt("20101023");
        reqDto.setContact_cert_no("132456789123456789");
        reqDto.setCard_start_dt("20191022");
        reqDto.setLic_regis_addr("杭州市");
        reqDto.setAcnt_certif_expire_dt("20221023");
        //法人入账标识：0：非法人，1：法人
        reqDto.setAcnt_artif_flag("1");
        //省份证号：非法人毕传 （acnt_artif_flag = 0）
        reqDto.setAcnt_certif_tp("0");

        try {
            FieldVerifyUtil.checkField(reqDto);
        } catch (FieldException e1) {
            e1.printStackTrace();
        } catch (Exception e1) {
            e1.printStackTrace();
        }

        String signPacket = SignUtil.generateSign(reqDto);
        System.out.println("------signPacket:" + signPacket);

        String sign = Md5.MD5Encode(signPacket + "&key=040f23510fbf4b34ae3895272e83c58c", "GBK");
        System.out.println("------sign:" + sign);

        reqDto.setSign(sign);

        try {
            String xmlReport = JaxbUtil.toXml(reqDto, MchntAddReqDto.class, "UTF-8");

            System.out.println("------xmlReport:" + xmlReport);

            String encodedXml = URLEncoder.encode(URLEncoder.encode(xmlReport, "GBK"), "GBK");

            String retResult = RequestUtil.doRequest("http://www-1.fuiou.com:28090/wmp/wxMchntMng.fuiou?action=wxMchntAdd", RequestUtil.Method.POST, "GBK", "req=" + encodedXml);

            retResult = URLDecoder.decode(retResult, "GBK");

            System.out.println("------retResult:" + retResult);

        } catch (JAXBException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/

    /**
     * 企业商户对私非法人结算
     */
    /*protected static void mchntAdd_0_2_0() {
        MchntAddReqDto reqDto = new MchntAddReqDto();
        reqDto.setTrace_no("111111111111");
        reqDto.setIns_cd("08A9999999");
        //商户名，简称，真实姓名：不能包含“test”和“测试”这些敏感字段
        //商户名不能重复：不管操作成功还是失败
        reqDto.setMchnt_name("商户名LittleCadet26");
        reqDto.setMchnt_shortname("商户简称");
        reqDto.setReal_name("王金娥");
        reqDto.setCertif_id("34012119790201192x");
        reqDto.setCertif_id_expire_dt("20191122");
        reqDto.setArtif_nm("王金娥");
        reqDto.setContact_person("联系人");
        reqDto.setContact_email("qq@qq.com");
        reqDto.setContact_phone("18577485847");
        reqDto.setContact_mobile("18577485847");
        reqDto.setAcnt_type("2"); //1：对公；2：对私;
        reqDto.setCity_cd("3310");
        reqDto.setCounty_cd("3310");
        reqDto.setContact_addr("杭州市");
        //入账卡开户行名称
        reqDto.setInter_bank_no("323596001013");
        //入账卡户名
        reqDto.setAcnt_nm("王金娥");
        //入账卡账号
        reqDto.setAcnt_no("6217993640004729261");
        reqDto.setAcnt_certif_id("34012119790201192x");
        reqDto.setSet_cd("M0008");
        //经营范围的代码
        reqDto.setBusiness("545");
        reqDto.setSettle_amt("132");
        //清算类型
        reqDto.setSettle_tp("1");
        reqDto.setLicense_type("0");
        //证件号码
        reqDto.setLicense_no("11222");
        reqDto.setLicense_expire_dt("20221023");
        reqDto.setLicense_start_dt("20101023");
        reqDto.setContact_cert_no("132456789123456789");
        reqDto.setCard_start_dt("20191022");
        reqDto.setLic_regis_addr("杭州市");
        reqDto.setAcnt_certif_expire_dt("20221023");
        //法人入账标识：0：非法人，1：法人
        reqDto.setAcnt_artif_flag("0");
        //省份证号：非法人毕传 （acnt_artif_flag = 0）
        reqDto.setAcnt_certif_tp("0");

        try {
            FieldVerifyUtil.checkField(reqDto);
        } catch (FieldException e1) {
            e1.printStackTrace();
        } catch (Exception e1) {
            e1.printStackTrace();
        }

        String signPacket = SignUtil.generateSign(reqDto);
        System.out.println("------signPacket:" + signPacket);

        String sign = Md5.MD5Encode(signPacket + "&key=040f23510fbf4b34ae3895272e83c58c", "GBK");
        System.out.println("------sign:" + sign);

        reqDto.setSign(sign);

        try {
            String xmlReport = JaxbUtil.toXml(reqDto, MchntAddReqDto.class, "UTF-8");

            System.out.println("------xmlReport:" + xmlReport);

            String encodedXml = URLEncoder.encode(URLEncoder.encode(xmlReport, "GBK"), "GBK");

            String retResult = RequestUtil.doRequest("http://www-1.fuiou.com:28090/wmp/wxMchntMng.fuiou?action=wxMchntAdd", RequestUtil.Method.POST, "GBK", "req=" + encodedXml);

            retResult = URLDecoder.decode(retResult, "GBK");

            System.out.println("------retResult:" + retResult);

        } catch (JAXBException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/

    /**
     * 个体工商户(对公结算、对私法人结算、非法人结算)
     * 此方法为对公法人结算
     * @return
     */
   /* private static void getB_1_1(){
        MchntAddReqDto reqDto = new MchntAddReqDto();
        //证件类型： 0 营业执照，1 三证合一 -->企业商户根 据证件类型传 0 或 1; A 身份证（一证下机）-->小微商户传 A; B 个体户-->个体工商户不论证件是营业 执照还是三证合一都传 B; 2，事业单位
        reqDto.setLicense_type("B");
        //2:对私 1:对公
        reqDto.setAcnt_type("1");
        // 法人入账标识(0:非法人入账,1:法人入账,若 license_type=A，这此字段必须 填：1:法人入账)
        reqDto.setAcnt_artif_flag("1");

        reqDto.setTrace_no("111131111111");
        reqDto.setIns_cd("08A9999999");
        reqDto.setMchnt_name("商户名LittleCadet36");
        reqDto.setMchnt_shortname("商户简称一一");
        reqDto.setReal_name("王金娥一");
        reqDto.setCertif_id("34012119790201192x");
        reqDto.setCertif_id_expire_dt("20191122");
        reqDto.setArtif_nm("王金娥");
        reqDto.setContact_person("联系人");
        reqDto.setContact_email("qq@qq.com");
        reqDto.setContact_phone("18577485847");
        reqDto.setContact_mobile("18577485847");

        reqDto.setCity_cd("8380");
        reqDto.setCounty_cd("8380");
        reqDto.setContact_addr("杭州市");
        //入账卡开户行名称
        reqDto.setInter_bank_no("323596001013");
        //入账卡户名
        reqDto.setAcnt_nm("王金娥");
        //入账卡账号
        reqDto.setAcnt_no("6217993640004729261");
        reqDto.setAcnt_certif_id("34012119790201192x");
        reqDto.setSet_cd("M0008");
        //经营范围的代码
        reqDto.setBusiness("545");
        reqDto.setSettle_amt("132");
        //清算类型
        reqDto.setSettle_tp("1");

        //证件号码
        reqDto.setLicense_no("11222");
        reqDto.setLicense_expire_dt("20221023");
        reqDto.setLicense_start_dt("20101023");
        reqDto.setContact_cert_no("132456789123456789");
        reqDto.setCard_start_dt("20191022");
        reqDto.setLic_regis_addr("杭州市");
        reqDto.setAcnt_certif_expire_dt("20221023");
        try {
            FieldVerifyUtil.checkField(reqDto);
        } catch (FieldException e1) {
            e1.printStackTrace();
        } catch (Exception e1) {
            e1.printStackTrace();
        }

        String signPacket = SignUtil.generateSign(reqDto);
        System.out.println("------signPacket:" + signPacket);

        String sign = Md5.MD5Encode(signPacket + "&key=040f23510fbf4b34ae3895272e83c58c", "GBK");
        System.out.println("------sign:" + sign);

        reqDto.setSign(sign);

        try {
            String xmlReport = JaxbUtil.toXml(reqDto, MchntAddReqDto.class, "UTF-8");

            System.out.println("------xmlReport:" + xmlReport);

            String encodedXml = URLEncoder.encode(URLEncoder.encode(xmlReport, "GBK"), "GBK");

            String retResult = RequestUtil.doRequest("http://www-1.fuiou.com:28090/wmp/wxMchntMng.fuiou?action=wxMchntAdd", RequestUtil.Method.POST, "GBK", "req=" + encodedXml);

            retResult = URLDecoder.decode(retResult, "GBK");

            System.out.println("------retResult:" + retResult);

        } catch (JAXBException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/

    /**
     * 个体工商户(对公结算、对私法人结算、非法人结算)
     * 此方法为对公非法人结算
     * @return
     */
    /*private static void getB_1_0(){
        MchntAddReqDto reqDto = new MchntAddReqDto();
        //证件类型： 0 营业执照，1 三证合一 -->企业商户根 据证件类型传 0 或 1; A 身份证（一证下机）-->小微商户传 A; B 个体户-->个体工商户不论证件是营业 执照还是三证合一都传 B; 2，事业单位
        reqDto.setLicense_type("B");
        //2:对私 1:对公
        reqDto.setAcnt_type("1");
        // 法人入账标识(0:非法人入账,1:法人入账,若 license_type=A，这此字段必须 填：1:法人入账)
        reqDto.setAcnt_artif_flag("0");

        reqDto.setTrace_no("111131111111");
        reqDto.setIns_cd("08A9999999");
        reqDto.setMchnt_name("商户名LittleCadet41");
        reqDto.setMchnt_shortname("商户简称一一");
        reqDto.setReal_name("王金娥一");
        reqDto.setCertif_id("34012119790201192x");
        reqDto.setCertif_id_expire_dt("20191122");
        reqDto.setArtif_nm("王金娥");
        reqDto.setContact_person("联系人");
        reqDto.setContact_email("qq@qq.com");
        reqDto.setContact_phone("18577485847");
        reqDto.setContact_mobile("18577485847");

        reqDto.setCity_cd("8380");
        reqDto.setCounty_cd("8380");
        reqDto.setContact_addr("杭州市");
        //入账卡开户行名称
        reqDto.setInter_bank_no("323596001013");
        //入账卡户名
        reqDto.setAcnt_nm("王金娥");
        //入账卡账号
        reqDto.setAcnt_no("6217993640004729261");
        reqDto.setAcnt_certif_id("34012119790201192x");
        reqDto.setSet_cd("M0008");
        //经营范围的代码
        reqDto.setBusiness("545");
        reqDto.setSettle_amt("132");
        //清算类型
        reqDto.setSettle_tp("1");

        //证件号码
        reqDto.setLicense_no("11222");
        reqDto.setLicense_expire_dt("20221023");
        reqDto.setLicense_start_dt("20101023");
        reqDto.setContact_cert_no("132456789123456789");
        reqDto.setCard_start_dt("20191022");
        reqDto.setLic_regis_addr("杭州市");
        reqDto.setAcnt_certif_expire_dt("20221023");
        //省份证号：非法人毕传 （acnt_artif_flag = 0）
        reqDto.setAcnt_certif_tp("0");
        try {
            FieldVerifyUtil.checkField(reqDto);
        } catch (FieldException e1) {
            e1.printStackTrace();
        } catch (Exception e1) {
            e1.printStackTrace();
        }

        String signPacket = SignUtil.generateSign(reqDto);
        System.out.println("------signPacket:" + signPacket);

        String sign = Md5.MD5Encode(signPacket + "&key=040f23510fbf4b34ae3895272e83c58c", "GBK");
        System.out.println("------sign:" + sign);

        reqDto.setSign(sign);

        try {
            String xmlReport = JaxbUtil.toXml(reqDto, MchntAddReqDto.class, "UTF-8");

            System.out.println("------xmlReport:" + xmlReport);

            String encodedXml = URLEncoder.encode(URLEncoder.encode(xmlReport, "GBK"), "GBK");

            String retResult = RequestUtil.doRequest("http://www-1.fuiou.com:28090/wmp/wxMchntMng.fuiou?action=wxMchntAdd", RequestUtil.Method.POST, "GBK", "req=" + encodedXml);

            retResult = URLDecoder.decode(retResult, "GBK");

            System.out.println("------retResult:" + retResult);

        } catch (JAXBException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/


    /**
     * 个体工商户(对公结算、对私法人结算、非法人结算)
     * 此方法为对私法人结算
     * @return
     */
    /*private static void getB_2_1(){
        MchntAddReqDto reqDto = new MchntAddReqDto();
        //证件类型： 0 营业执照，1 三证合一 -->企业商户根 据证件类型传 0 或 1; A 身份证（一证下机）-->小微商户传 A; B 个体户-->个体工商户不论证件是营业 执照还是三证合一都传 B; 2，事业单位
        reqDto.setLicense_type("B");
        //2:对私 1:对公
        reqDto.setAcnt_type("2");
        // 法人入账标识(0:非法人入账,1:法人入账,若 license_type=A，这此字段必须 填：1:法人入账)
        reqDto.setAcnt_artif_flag("1");

        reqDto.setTrace_no("111131111111");
        reqDto.setIns_cd("08A9999999");
        reqDto.setMchnt_name("商户名LittleCadet46");
        reqDto.setMchnt_shortname("商户简称一一");
        reqDto.setReal_name("王金娥一");
        reqDto.setCertif_id("34012119790201192x");
        reqDto.setCertif_id_expire_dt("20191122");
        reqDto.setArtif_nm("王金娥");
        reqDto.setContact_person("联系人");
        reqDto.setContact_email("qq@qq.com");
        reqDto.setContact_phone("18577485847");
        reqDto.setContact_mobile("18577485847");

        reqDto.setCity_cd("8380");
        reqDto.setCounty_cd("8380");
        reqDto.setContact_addr("杭州市");
        //入账卡开户行名称
        reqDto.setInter_bank_no("323596001013");
        //入账卡户名
        reqDto.setAcnt_nm("王金娥");
        //入账卡账号
        reqDto.setAcnt_no("6217993640004729261");
        reqDto.setAcnt_certif_id("34012119790201192x");
        reqDto.setSet_cd("M0008");
        //经营范围的代码
        reqDto.setBusiness("545");
        reqDto.setSettle_amt("132");
        //清算类型
        reqDto.setSettle_tp("1");

        //证件号码
        reqDto.setLicense_no("11222");
        reqDto.setLicense_expire_dt("20221023");
        reqDto.setLicense_start_dt("20101023");
        reqDto.setContact_cert_no("132456789123456789");
        reqDto.setCard_start_dt("20191022");
        reqDto.setLic_regis_addr("杭州市");
        reqDto.setAcnt_certif_expire_dt("20221023");

        try {
            FieldVerifyUtil.checkField(reqDto);
        } catch (FieldException e1) {
            e1.printStackTrace();
        } catch (Exception e1) {
            e1.printStackTrace();
        }

        String signPacket = SignUtil.generateSign(reqDto);
        System.out.println("------signPacket:" + signPacket);

        String sign = Md5.MD5Encode(signPacket + "&key=040f23510fbf4b34ae3895272e83c58c", "GBK");
        System.out.println("------sign:" + sign);

        reqDto.setSign(sign);

        try {
            String xmlReport = JaxbUtil.toXml(reqDto, MchntAddReqDto.class, "UTF-8");

            System.out.println("------xmlReport:" + xmlReport);

            String encodedXml = URLEncoder.encode(URLEncoder.encode(xmlReport, "GBK"), "GBK");

            String retResult = RequestUtil.doRequest("http://www-1.fuiou.com:28090/wmp/wxMchntMng.fuiou?action=wxMchntAdd", RequestUtil.Method.POST, "GBK", "req=" + encodedXml);

            retResult = URLDecoder.decode(retResult, "GBK");

            System.out.println("------retResult:" + retResult);

        } catch (JAXBException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/

    /**
     * 个体工商户(对公结算、对私法人结算、非法人结算)
     * 此方法为非法人结算
     * @return
     */
   /* private static void getB_2_0(){
        MchntAddReqDto reqDto = new MchntAddReqDto();
        //证件类型： 0 营业执照，1 三证合一 -->企业商户根 据证件类型传 0 或 1; A 身份证（一证下机）-->小微商户传 A; B 个体户-->个体工商户不论证件是营业 执照还是三证合一都传 B; 2，事业单位
        reqDto.setLicense_type("B");
        //2:对私 1:对公
        reqDto.setAcnt_type("2");
        // 法人入账标识(0:非法人入账,1:法人入账,若 license_type=A，这此字段必须 填：1:法人入账)
        reqDto.setAcnt_artif_flag("0");
        // 入账证件类型("0":"身份证"【默 认】【acnt_artif_flag = 0 时必填】
        reqDto.setAcnt_certif_tp("0");


        reqDto.setTrace_no("111131111111");
        reqDto.setIns_cd("08A9999999");
        reqDto.setMchnt_name("商户名LittleCadet45");
        reqDto.setMchnt_shortname("商户简称一一");
        reqDto.setReal_name("王金娥一");
        reqDto.setCertif_id("34012119790201192x");
        reqDto.setCertif_id_expire_dt("20191122");
        reqDto.setArtif_nm("王金娥");
        reqDto.setContact_person("联系人");
        reqDto.setContact_email("qq@qq.com");
        reqDto.setContact_phone("18577485847");
        reqDto.setContact_mobile("18577485847");

        reqDto.setCity_cd("8380");
        reqDto.setCounty_cd("8380");
        reqDto.setContact_addr("杭州市");
        //入账卡开户行名称
        reqDto.setInter_bank_no("323596001013");
        //入账卡户名
        reqDto.setAcnt_nm("王金娥");
        //入账卡账号
        reqDto.setAcnt_no("6217993640004729261");
        reqDto.setAcnt_certif_id("34012119790201192x");
        reqDto.setSet_cd("M0008");
        //经营范围的代码
        reqDto.setBusiness("545");
        reqDto.setSettle_amt("132");
        //清算类型
        reqDto.setSettle_tp("1");

        //证件号码
        reqDto.setLicense_no("11222");
        reqDto.setLicense_expire_dt("20221023");
        reqDto.setLicense_start_dt("20101023");
        reqDto.setContact_cert_no("132456789123456789");
        reqDto.setCard_start_dt("20191022");
        reqDto.setLic_regis_addr("杭州市");
        reqDto.setAcnt_certif_expire_dt("20221023");

        try {
            FieldVerifyUtil.checkField(reqDto);
        } catch (FieldException e1) {
            e1.printStackTrace();
        } catch (Exception e1) {
            e1.printStackTrace();
        }

        String signPacket = SignUtil.generateSign(reqDto);
        System.out.println("------signPacket:" + signPacket);

        String sign = Md5.MD5Encode(signPacket + "&key=040f23510fbf4b34ae3895272e83c58c", "GBK");
        System.out.println("------sign:" + sign);

        reqDto.setSign(sign);

        try {
            String xmlReport = JaxbUtil.toXml(reqDto, MchntAddReqDto.class, "UTF-8");

            System.out.println("------xmlReport:" + xmlReport);

            String encodedXml = URLEncoder.encode(URLEncoder.encode(xmlReport, "GBK"), "GBK");

            String retResult = RequestUtil.doRequest("http://www-1.fuiou.com:28090/wmp/wxMchntMng.fuiou?action=wxMchntAdd", RequestUtil.Method.POST, "GBK", "req=" + encodedXml);

            retResult = URLDecoder.decode(retResult, "GBK");

            System.out.println("------retResult:" + retResult);

        } catch (JAXBException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/
    public static void upload(String fy_mchnt_cd) {
        FtpUpload upload = new FtpUpload();

        File idCard = null;
        File idCardReverse = null;
        File business = null;
        File front = null;
        File head = null;
        File licence  = null;
        File bank  = null;
        File agreement  = null;
        File hand  = null;
        File directory = FileUtil.file("C:\\Users\\Administrator\\Desktop\\富友\\4、寿县富足足浴店-个体工商户\\4、寿县富足足浴店");
        File[] files = directory.listFiles();
        for (File file : files) {
            if("法人身份证明正面.jpg".equals(file.getName()))
            {
                String realName = null;
                file = FileUtil.file("C:\\Users\\Administrator\\Desktop\\富友\\4、寿县富足足浴店-个体工商户\\4、寿县富足足浴店\\" + file.getName());
                idCard = processFile(file,realName,idCard);
            }else if( "法人身份证明背面.jpg".equals(file.getName()))
            {
                String realName = null;
                file = FileUtil.file("C:\\Users\\Administrator\\Desktop\\富友\\4、寿县富足足浴店-个体工商户\\4、寿县富足足浴店\\" + file.getName());
                idCardReverse = processFile(file,realName,idCardReverse);
            }else if( "法人身份证明反面.jpg".equals(file.getName()))
            {
                String realName = null;
                file = FileUtil.file("C:\\Users\\Administrator\\Desktop\\富友\\4、寿县富足足浴店-个体工商户\\4、寿县富足足浴店\\" + file.getName());
                idCardReverse = processFile(file,realName,idCardReverse);
            }else if( "证件照片.jpg".equals(file.getName()))
            {
                String realName = null;
                file = FileUtil.file("C:\\Users\\Administrator\\Desktop\\富友\\4、寿县富足足浴店-个体工商户\\4、寿县富足足浴店\\" + file.getName());
                business = processFile(file,realName,business);
            }else if( "门脸照片.jpg".equals(file.getName()))
            {
                String realName = null;
                file = FileUtil.file("C:\\Users\\Administrator\\Desktop\\富友\\4、寿县富足足浴店-个体工商户\\4、寿县富足足浴店\\" + file.getName());
                front = processFile(file,realName,front);
            }else if("门头照片.jpg".equals(file.getName()))
            {
                String realName = null;
                file = FileUtil.file("C:\\Users\\Administrator\\Desktop\\富友\\4、寿县富足足浴店-个体工商户\\4、寿县富足足浴店\\" + file.getName());
                head = processFile(file,realName,head);
            }else if("开户许可证.jpg".equals(file.getName()))
            {
                String realName = null;
                file = FileUtil.file("C:\\Users\\Administrator\\Desktop\\富友\\4、寿县富足足浴店-个体工商户\\4、寿县富足足浴店\\" + file.getName());
                licence = processFile(file,realName,licence);
            }else if("银行卡正面.jpg".equals(file.getName()))
            {
                String realName = null;
                file = FileUtil.file("C:\\Users\\Administrator\\Desktop\\富友\\4、寿县富足足浴店-个体工商户\\4、寿县富足足浴店\\" + file.getName());
                bank = processFile(file,realName,licence);
            }else if("商户协议右.jpg".equals(file.getName()))
            {
                String realName = null;
                file = FileUtil.file("C:\\Users\\Administrator\\Desktop\\富友\\4、寿县富足足浴店-个体工商户\\4、寿县富足足浴店\\" + file.getName());
                agreement = processFile(file,realName,licence);
            }else if("手持证件照片.jpg".equals(file.getName())) {
                String realName = null;
                file = FileUtil.file("C:\\Users\\Administrator\\Desktop\\富友\\4、寿县富足足浴店-个体工商户\\4、寿县富足足浴店\\" + file.getName());
                hand = processFile(file, realName, licence);
            }

        }
        File zip = ZipUtil.zip(FileUtil.file("C:\\Users\\Administrator\\Desktop\\富友\\zip\\" + fy_mchnt_cd + ".zip"), false,
                FileUtil.file("C:\\Users\\Administrator\\Desktop\\富友\\4、寿县富足足浴店-个体工商户\\4、寿县富足足浴店\\" + idCard.getName()),
                FileUtil.file(idCardReverse.getPath()),
                FileUtil.file(business.getPath()),
                FileUtil.file(front.getPath()),
                FileUtil.file(head.getPath()),
                FileUtil.file(licence.getPath()),
                FileUtil.file(bank.getPath()),
                FileUtil.file(agreement.getPath()),
                FileUtil.file(hand.getPath()));
        System.out.println("result:" + upload.upload(zip.getPath(), "/"));
    }

    public static void download(String fy_mchnt_cd) {
        FtpDownload download = new FtpDownload();
        System.out.println("result:" + download.download("/" + fy_mchnt_cd + ".zip", "C:\\Users\\Administrator\\Desktop\\富友\\zip\\download", 0));
    }

    public static void confirm(String traceNo, String fy_mchnt_cd) {

        ConfirmDto confirmDto = ConfirmDto.builder().trace_no(traceNo).ins_cd("08A9999999").mchnt_cd(fy_mchnt_cd).build();
        String signPacket = SignUtil.generateSign(confirmDto);
        System.out.println("------signPacket:" + signPacket);

        String sign = Md5.MD5Encode(signPacket + "&key=040f23510fbf4b34ae3895272e83c58c", "GBK");
        System.out.println("------sign:" + sign);

        confirmDto.setSign(sign);

        String param = new Gson().toJson(confirmDto);
        try {
            System.out.println(param);
            String url = "http://www-1.fuiou.com:28090/wmp/wxMchntMng.fuiou?action=attachConfirm";
            Map<String, String> paramMap = new HashMap<String, String>();
            paramMap.put("req", param);
            String result = HttpClientUtil.postJsonWithMap(url, paramMap);
            System.out.println("返回报文：" + result);
            ConfirmDto retDto = new Gson().fromJson(result, ConfirmDto.class);
            System.out.println("retDto:" + retDto);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static File processFile(File file, String realName, File realFile){
        try {
            System.out.println(file.getName().replaceAll(".jpg", ""));
            String encode = URLEncoder.encode(file.getName().replaceAll(".jpg", ""), "GBK");
            System.out.println(encode);
            realName = encode.replaceAll("%", "").toLowerCase();
            System.out.println(realName);
            realFile = FileUtil.file("C:\\Users\\Administrator\\Desktop\\富友\\4、寿县富足足浴店-个体工商户\\4、寿县富足足浴店\\" + realName + ".jpg");
            file.renameTo(realFile);
            System.out.println(realFile.getName());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return realFile;
    }


}

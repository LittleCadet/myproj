package com.myproj.app.string;

import org.springframework.util.StringUtils;

import java.util.regex.Pattern;

import static java.util.regex.Pattern.*;
import static java.util.regex.Pattern.compile;

/**
 * @author shenxie
 * @date 2021/5/18
 */
public class Matcher {

    public static void main(String[] args) {
//        Pattern pattern = compile("\\d{3,5}");
//        String charSequence = "123-34345-234-00";
//        java.util.regex.Matcher matcher = pattern.matcher(charSequence);
//
//        //虽然匹配失败，但由于charSequence里面的"123"和pattern是匹配的,所以下次的匹配从位置4开始
//        System.out.println(matcher.matches());
//        //测试匹配位置
////        matcher.find();
//        System.out.println(matcher.start());
//
//        //使用reset方法重置匹配位置
//        matcher.reset();
//
//        //第一次find匹配以及匹配的目标和匹配的起始位置
//        System.out.println(matcher.find());
//        System.out.println(matcher.group()+" - "+matcher.start());
//        //第二次find匹配以及匹配的目标和匹配的起始位置
//        System.out.println(matcher.find());
//        System.out.println(matcher.group()+" - "+matcher.start());
//
//        //第一次lookingAt匹配以及匹配的目标和匹配的起始位置
//        System.out.println(matcher.lookingAt());
//        System.out.println(matcher.group()+" - "+matcher.start());
//
//        //第二次lookingAt匹配以及匹配的目标和匹配的起始位置
//        System.out.println(matcher.lookingAt());
//        System.out.println(matcher.group()+" - "+matcher.start());

        showInfo();
//        testa();
//        testb();
    }

    private static void showInfo() {
        String str ="# <font color=#0099ff size=2>【liveservice】指定接口响应时间过长！</font>\n" +
                "----\n" +
                "![avatar](https://kkl-static.oss-cn-hangzhou.aliyuncs.com/alert/slow3.jpg)\n" +
                "\n" +
                "应用liveservice最近2分钟内接口响应时间196次超过阈值1ms(1次)!\n" +
                "\n" +
                "2021-05-17 16:43:00到16:45:00如下接口响应时间超过阈值1次：\n" +
                ">LiveShowPrivateFacade:selectLiveShowSimpleById -- 58次 平均1.0ms\n" +
                "LiveClassManagerFacade:getClassStudentInfo -- 48次 平均15.0ms\n" +
                "LiveCourseQueryFacade:getOnlineNumber -- 38次 平均1.4ms\n" +
                "LiveRankingFacade:getAllClassAttendanceRank -- 24次 平均50.0ms\n" +
                "JobInvoker:executeAll -- 9次 平均1.0ms\n" +
                "等等\n";
        // 正则的零宽断言
        String regex = "(?<=响应时间).*?(?=次)";
        String regex3 = "([a-z0-9]*\\.){2}([a-z0-9]*)|([a-z0-9]*\\:{1}([a-z0-9]*))|(?<=-- ).*?(?=次)|(?<=平均).*?(?=ms)";
//        String regex3 = "([a-z0-9]*\\:{1}([a-z0-9]*))";
//        String regex4 = "^(?!.*helloworld).*$";
        Pattern p = compile(regex);
        java.util.regex.Matcher m = p.matcher(str);

        if(m.find()){
            System.out.println("应用超时总次数：" + m.group());
        }
        String s1 = str.replaceAll("([0-9]{2})\\:", "");
        System.out.println("result:" + s1);
        String s = s1.replaceAll("https://kkl-static.oss-cn-hangzhou.aliyuncs.com", "");
        Pattern compile1 = compile(regex3,  CASE_INSENSITIVE);
        java.util.regex.Matcher m3 = compile1.matcher(s);
        while(m3.find()){
            if(! StringUtils.isEmpty(m3.group())){
                System.out.println("接口2：" + m3.group());
            }
        }
    }

    static String teststr = "UAPPROJECT_ID='402894cb4833decf014833e04fd70002 ; \n\r */' select ";

    /**
     * 包含回车换行符的处理
     */
    public static void testa(){
        Pattern wp = compile("'.*?'", CASE_INSENSITIVE | DOTALL);
        java.util.regex.Matcher m = wp.matcher(teststr);
        String result = m.replaceAll("---");
        System.out.println("result:" + result);
    }

    /**
     * 包含回车换行符的处理
     */
    public static void testb(){
        String result = teststr.replaceAll("(?s)'.*?'", "");
        System.out.println("result:" + result);
    }
}

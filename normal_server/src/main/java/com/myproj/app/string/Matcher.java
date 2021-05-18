package com.myproj.app.string;

import org.springframework.util.StringUtils;

import java.util.regex.Pattern;

import static java.util.regex.Pattern.compile;

/**
 * @author shenxie
 * @date 2021/5/18
 */
public class Matcher {

    public static void main(String[] args) {
        Pattern pattern = compile("\\d{3,5}");
        String charSequence = "123-34345-234-00";
        java.util.regex.Matcher matcher = pattern.matcher(charSequence);

        //虽然匹配失败，但由于charSequence里面的"123"和pattern是匹配的,所以下次的匹配从位置4开始
        System.out.println(matcher.matches());
        //测试匹配位置
//        matcher.find();
        System.out.println(matcher.start());

        //使用reset方法重置匹配位置
        matcher.reset();

        //第一次find匹配以及匹配的目标和匹配的起始位置
        System.out.println(matcher.find());
        System.out.println(matcher.group()+" - "+matcher.start());
        //第二次find匹配以及匹配的目标和匹配的起始位置
        System.out.println(matcher.find());
        System.out.println(matcher.group()+" - "+matcher.start());

        //第一次lookingAt匹配以及匹配的目标和匹配的起始位置
        System.out.println(matcher.lookingAt());
        System.out.println(matcher.group()+" - "+matcher.start());

        //第二次lookingAt匹配以及匹配的目标和匹配的起始位置
        System.out.println(matcher.lookingAt());
        System.out.println(matcher.group()+" - "+matcher.start());

        showInfo();
    }

    private static void showInfo() {
        String str = "# <font color=#0099ff size=2>【kkl-gateway】指定接口响应时间过长！</font>\n" +
                "----\n" +
                "![avatar](https://kkl-static.oss-cn-hangzhou.aliyuncs.com/alert/slow3.jpg)\n" +
                "\n" +
                "应用kkl-gateway最近2分钟内接口响应时间137次超过阈值500ms(10次)!\n" +
                "\n" +
                "2021-05-18 17:02:00到17:04:00如下接口响应时间超过阈值10次：\n" +
                ">estudy.StudyTaskPageV2Facade.queryHomePageInfoWithoutTodayLive -- 16次 平均611.2ms\n" +
                "liveentryprod.LiveCourseQueryFacade.getLiveSeriesInfo -- 14次 平均596.5ms\n" +
                "liveservice.LiveBasicInfoFacade.getAllWidgetList -- 14次 平均622.3ms\n" +
                "estudy.LiveClassManagerFacade.getClassOnlineNum -- 13次 平均610.7ms\n" +
                "liveservice.LiveInteractionFacade.reserveHandUp -- 12次 平均602.6ms\n" +
                "等等";
        // 正则的零宽断言
        String regex = "(?<=响应时间).*?(?=次)";
        String regex3 = "([a-z0-9]*\\.){2}([a-z0-9]*)|(?<=-- ).*?(?=次)|(?<=平均).*?(?=ms)";
        Pattern p = Pattern.compile(regex);
        java.util.regex.Matcher m = p.matcher(str);

        if(m.find()){
            System.out.println("应用超时总次数：" + m.group());
        }
        Pattern compile1 = compile(regex3,  Pattern.CASE_INSENSITIVE);
        java.util.regex.Matcher m3 = compile1.matcher(str);
        while(m3.find()){
            if(! StringUtils.isEmpty(m3.group())){
                System.out.println("接口2：" + m3.group());
            }
        }
    }
}

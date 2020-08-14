package com.oss.app.controller;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.GetObjectRequest;
import com.oss.app.util.ImageUploadUtil;
import com.oss.app.util.QRCodeUtil;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;

/**
 *
 * 二维码添加背景图
 *
 * LittleCadet
 */
public class MergeQr {

    public static void main(String[] args) {
        String bigPath = "D:\\download\\聚合收款码.png";
        String newFilePath = "C:\\Users\\Administrator\\Desktop\\image\\image.png";
        try {
            //生成二维码
            //File file =  QRCodeUtil.zxingCodeCreate("http://47.99.112.38:8090/image/judge","C:\\Users\\Administrator\\Desktop\\image",380,"C:\\Users\\Public\\Pictures\\Sample Pictures\\7.2kb.jpg");

            File file = new File("D:\\download\\聚合收款码-saas 微信.png");
            //二维码上传到Oss
            OSS ossClient = new OSSClientBuilder().build("oss-cn-hangzhou.aliyuncs.com", "LTAI4Ff6LjbrgESvCDR9w8Xg", "rTTwoDhDe4dFpBN3JlIqDXdicRMNUl");
            String fileName = ImageUploadUtil.imageUpload(file,ossClient,"facepay-test");
            System.out.println("ossPath:" + fileName);

            //从oss下载文件到本地
            //OSS ossClient = new OSSClientBuilder().build("oss-cn-hangzhou.aliyuncs.com", "LTAI4Ff6LjbrgESvCDR9w8Xg", "rTTwoDhDe4dFpBN3JlIqDXdicRMNUl");
            ossClient.getObject(new GetObjectRequest("facepay-test","聚合收款码-saas 微信.png" ), new File("C:\\Users\\Administrator\\Desktop\\image\\oss.jpg"));

            //二维码添加背景图（背景图地址，二维码地址，距离背景图的x轴长度，距离背景图的y轴的长度）
            //mergeImage("C:\\Users\\Administrator\\Desktop\\image\\oss.png",file.getPath(),newFilePath,"95","250");


        } catch (Exception e) {
            e.printStackTrace();
        }

    }



    /***
     * 二维码嵌套背景图的方法
     *@param bigPath 背景图 - 可传网络地址
     *@param smallPath 二维码地址 - 可传网络地址
     *@param newFilePath 生成新图片的地址
     * @param  x 二维码x坐标
     *  @param  y 二维码y坐标
     * */
    public static File mergeImage(String bigPath, String smallPath, String newFilePath, String x, String y) throws IOException {

        File realFile =  null;
        try {
            BufferedImage small;
            BufferedImage big;
            realFile =  new File(newFilePath);
            if (bigPath.contains("http://") || bigPath.contains("https://")) {
                URL url = new URL(bigPath);
                big = ImageIO.read(url);
            } else {
                big = ImageIO.read(new File(bigPath));
            }


            if (smallPath.contains("http://") || smallPath.contains("https://")) {

                URL url = new URL(smallPath);
                small = ImageIO.read(url);
            } else {
                small = ImageIO.read(new File(smallPath));
            }

            Graphics2D g = big.createGraphics();

            float fx = Float.parseFloat(x);
            float fy = Float.parseFloat(y);
            int x_i = (int) fx;
            int y_i = (int) fy;
            g.drawImage(small, x_i, y_i, small.getWidth(), small.getHeight(), null);
            g.dispose();
            ImageIO.write(big, "png", realFile);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return realFile;
    }
}
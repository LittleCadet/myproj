package com.oss.app.controller;

/**
 * @Description:
 * @Author: shenxie
 * @CreateDate: 2019/9/29
 * @UpdateUser:
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */
import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import com.oss.app.util.QRCodeUtil;
import lombok.extern.slf4j.Slf4j;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * 二维码解码
 */
@Slf4j
public class DeocdeQR {

    public static void main(String[] args) {
        try {
            //File file = new File("C:\\Users\\Administrator\\Desktop\\1012.png");
            //生成二维码(默认大小250mm)
            File file =  QRCodeUtil.zxingCodeCreate("www.baidu.com","C:\\Users\\Administrator\\Desktop\\image",250,"C:\\Users\\Public\\Pictures\\Sample Pictures\\7.2kb.jpg");
            BufferedImage image = ImageIO.read(new FileInputStream(file));
            /**裁剪原图  目前访问微信 微信返回的是 470*535 像素 170620*/
            BufferedImage subImage = image.getSubimage(0, 0, image.getWidth(), (int) (image.getHeight() * 0.3));

            System.out.println(decodeQrcode(subImage));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }



    public static String decodeQrcode(BufferedImage image) throws NotFoundException {

        MultiFormatReader formatReader = new MultiFormatReader();

        BinaryBitmap binaryBitmap=new BinaryBitmap(new HybridBinarizer(new BufferedImageLuminanceSource(image)));

        //定义二维码的参数:
        Map<DecodeHintType, Object> hints = new HashMap<DecodeHintType, Object>();

        //定义字符集
        hints.put(DecodeHintType.CHARACTER_SET,"utf-8");

        //开启复杂模式
        hints.put(DecodeHintType.PURE_BARCODE, Boolean.TRUE);

        //优化精度
        hints.put(DecodeHintType.TRY_HARDER, Boolean.TRUE);
        Result result = formatReader.decode(binaryBitmap, hints);//开始解析

        return result.getText();
    }


    public static Result decodeQrcode(File file) {
        BufferedImage image = null;
        try {
            image = ImageIO.read(new FileInputStream(file));
        } catch (IOException e) {
            log.error("failed to get file!e :{}",e.getMessage());
        }
        /**裁剪原图  目前访问微信 微信返回的是 470*535 像素 170620*/
        BufferedImage subImage = image.getSubimage(0, 0, image.getWidth(), (int) (image.getHeight() * 0.85));

        MultiFormatReader formatReader = new MultiFormatReader();

        BinaryBitmap binaryBitmap=new BinaryBitmap(new HybridBinarizer(new BufferedImageLuminanceSource(subImage)));

        //定义二维码的参数:
        Map<DecodeHintType, Object> hints = new HashMap<DecodeHintType, Object>();
        hints.put(DecodeHintType.CHARACTER_SET,"utf-8");//定义字符集
        hints.put(DecodeHintType.PURE_BARCODE, Boolean.TRUE);
        Result result = null;//开始解析
        try {
            result = formatReader.decode(binaryBitmap, hints);
            log.info("result:{}",result);
        } catch (NotFoundException e) {
            log.error("failed to decode ! e:{}",e.getMessage());
        }

        return result;
    }

}

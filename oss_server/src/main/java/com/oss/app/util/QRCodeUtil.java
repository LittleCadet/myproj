package com.oss.app.util;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import org.apache.tomcat.util.http.fileupload.FileUtils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * 二维码的工具类
 * LittleCadet
 */
public class QRCodeUtil {

    /**
     * 生成二维码
     * @param content
     * @param path
     * @param size
     * @param logoPath
     * @return
     */
    public static File zxingCodeCreate(String content, String path, Integer size, String logoPath) {
        try {
            String imageType = "jpg";
            BufferedImage image = getBufferedImage(content, size, logoPath);
            Random random = new Random();
            File file = new File(path + random.nextInt(1000) + ".jpg");
            if (!file.exists()) {
                file.mkdirs();
            }

            ImageIO.write(image, imageType, file);
            FileUtils.forceDelete(file);
            return file;
        } catch (IOException var8) {
            var8.printStackTrace();
            return null;
        }

    }


    public static BufferedImage getBufferedImage(String content, Integer size, String logoPath) {
        if (size == null || size <= 0) {
            size = 250;
        }

        BufferedImage image = null;

        try {
            Map<EncodeHintType, Object> hints = new HashMap();
            hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
            hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
            hints.put(EncodeHintType.MARGIN, 1);
            MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
            BitMatrix bitMatrix = multiFormatWriter.encode(content, BarcodeFormat.QR_CODE, size, size, hints);
            int codeWidth = bitMatrix.getWidth();
            int codeHeight = bitMatrix.getHeight();
            image = new BufferedImage(codeWidth, codeHeight, 1);

            for(int i = 0; i < codeWidth; ++i) {
                for(int j = 0; j < codeHeight; ++j) {
                    image.setRGB(i, j, bitMatrix.get(i, j) ? -16777216 : -1);
                }
            }

            if (logoPath != null && !"".equals(logoPath)) {
                File logoPic = new File(logoPath);
                if (logoPic.exists()) {
                    Graphics2D g = image.createGraphics();
                    BufferedImage logo = ImageIO.read(logoPic);
                    int widthLogo = logo.getWidth((ImageObserver)null) > image.getWidth() * 2 / 10 ? image.getWidth() * 2 / 10 : logo.getWidth((ImageObserver)null);
                    int heightLogo = logo.getHeight((ImageObserver)null) > image.getHeight() * 2 / 10 ? image.getHeight() * 2 / 10 : logo.getHeight((ImageObserver)null);
                    int x = (image.getWidth() - widthLogo) / 2;
                    int y = (image.getHeight() - heightLogo) / 2;
                    g.drawImage(logo, x, y, widthLogo, heightLogo, (ImageObserver)null);
                    g.drawRoundRect(x, y, widthLogo, heightLogo, 15, 15);
                    g.setStroke(new BasicStroke(2.0F));
                    g.setColor(Color.WHITE);
                    g.drawRect(x, y, widthLogo, heightLogo);
                    g.dispose();
                    logo.flush();
                    image.flush();
                }
            }
        } catch (WriterException var16) {
            var16.printStackTrace();
        } catch (IOException var17) {
            var17.printStackTrace();
        }

        return image;
    }

}

package cn.siques.mangoprocessing.utils;

import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.IOException;

import org.icepdf.core.pobjects.Document;
import org.icepdf.core.util.GraphicsRenderingHints;

/**
 * PDF 转 图片
 */
public class IcePdf {



    public static RenderedImage pdf2Pic(String pdfPath) throws Exception {
        Document document = new Document();
        document.setFile(pdfPath);
        //缩放比例
        float scale = 2.5f;
        //旋转角度
        float rotation = 0f;

        for (int i = 0; i < document.getNumberOfPages(); i++) {
            BufferedImage image = (BufferedImage)
                    document.getPageImage(i, GraphicsRenderingHints.SCREEN, org.icepdf.core.pobjects.Page.BOUNDARY_CROPBOX, rotation, scale);
            RenderedImage rendImage = image;
            String imgName = i + ".png";
            System.out.println(imgName);
            return rendImage;
        }

        document.dispose();
        return null;
    }

}
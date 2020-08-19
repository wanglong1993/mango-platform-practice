package cn.siques.mangoprocessing.utils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import jdk.internal.util.xml.impl.Input;
import org.icepdf.core.pobjects.Document;

import org.icepdf.core.pobjects.Page;
import org.icepdf.core.util.GraphicsRenderingHints;

/**
 * PDF 转 图片
 */
public class IcePdf {



    public static List<BufferedImage> pdf2Pic(InputStream in) throws Exception {
        Document document = new Document();
        document.setInputStream(in,"src/main/resources");
        //缩放比例
        float scale = 2.5f;
        //旋转角度
        float rotation = 0f;
        List<BufferedImage> collect = IntStream.range(0, document.getNumberOfPages()).mapToObj(
                i -> {
                    try {
                        BufferedImage pageImage = (BufferedImage) document.getPageImage(
                                i, GraphicsRenderingHints.SCREEN,
                                Page.BOUNDARY_CROPBOX, rotation, scale);
                        return pageImage;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return null;
                }
        ).collect(Collectors.toList());

        document.dispose();
        return collect;
    }

}
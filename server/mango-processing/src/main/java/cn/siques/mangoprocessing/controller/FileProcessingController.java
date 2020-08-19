package cn.siques.mangoprocessing.controller;


import cn.siques.mangoprocessing.utils.IcePdf;
import com.alibaba.nacos.client.identify.Base64;
import com.alibaba.nacos.common.util.IoUtils;
import com.netflix.client.http.HttpRequest;
import org.apache.commons.io.FileUtils;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/sys/v1/pri/")
public class FileProcessingController {


    @GetMapping("test")
    public int test(){
        return 1;
    }

//    @PostMapping(value = "pdf2pic")
//    @ResponseBody
//    public void getFile(MultipartFile file, HttpServletResponse response) throws Exception {
//
//        InputStream inputStream = file.getInputStream();
//
//
//        RenderedImage renderedImage = IcePdf.pdf2Pic(inputStream);
//
////        response.setHeader("Cache-Control","no-store, no-cache");
//        response.setContentType("image/jpeg");
//        ServletOutputStream outputStream = response.getOutputStream();
//
//
//        ImageIO.write(renderedImage,"jpg",outputStream);
//        IOUtils.closeQuietly(outputStream);
//
//    }

    @PostMapping(value = "pdf2base64")
    @ResponseBody
    public List<String> getBase64(MultipartFile file) throws Exception {



        List<BufferedImage> bufferedImages = IcePdf.pdf2Pic(file.getInputStream());

        List<String> list = bufferedImages.stream().map(bufferedImage -> {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            try {
                ImageIO.write(bufferedImage, "jpg", out);
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return new String(Base64.encodeBase64(out.toByteArray()));
        }).collect(Collectors.toList());


        return list;

    }


}

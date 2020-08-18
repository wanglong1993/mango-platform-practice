package cn.siques.mangoprocessing.controller;


import cn.siques.mangoprocessing.utils.IcePdf;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.RenderedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;

@RestController
@RequestMapping
public class FileProcessingController {

    @GetMapping(value = "pdf2pic")
    @ResponseBody
    public void getFile( HttpServletResponse response) throws Exception {


        String filePath = "C:\\Users\\Administrator\\Desktop\\044031900111-37789244.pdf";

        RenderedImage renderedImage = IcePdf.pdf2Pic(filePath);

//        response.setHeader("Cache-Control","no-store, no-cache");
        response.setContentType("image/jpeg");
        ServletOutputStream outputStream = response.getOutputStream();


        ImageIO.write(renderedImage,"jpg",outputStream);
        IOUtils.closeQuietly(outputStream);

    }

}

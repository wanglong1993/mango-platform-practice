package cn.siques.mangocore.utils;


import cn.siques.mangocore.entity.JsonData;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class RenderJson {


    public  static  void renderJson(HttpServletResponse response, String msg) {


        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");

        ServletOutputStream output = null;
        try {
            output = response.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            output.write(JsonData.buildError(msg).toString().getBytes());
            output.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }



    }
}

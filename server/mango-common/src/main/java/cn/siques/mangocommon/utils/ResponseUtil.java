package cn.siques.mangocommon.utils;



import cn.siques.mangocommon.dto.JsonData;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ResponseUtil {


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

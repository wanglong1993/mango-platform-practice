package cn.siques.mango.controller;

import cn.siques.mango.config.security.JwtAuthenticationToken;
import cn.siques.mango.config.security.PasswordUtils;
import cn.siques.mango.config.security.SecurityUtils;
import cn.siques.mango.controller.dto.LoginDto;
import cn.siques.mango.entity.JsonData;
import cn.siques.mango.entity.SysUser;
import cn.siques.mango.service.SysUserService;
import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import org.apache.commons.compress.utils.IOUtils;
import org.apache.tomcat.util.bcel.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.spring.web.json.Json;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

@RestController()
@RequestMapping("/api/sys/v1/pub/")
public class SysLoginController {
    @Autowired
    private Producer producer;

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public JsonData login(@RequestBody LoginDto loginDto,HttpServletRequest request){
        String username = loginDto.getAccount();
        String password = loginDto.getPassword();
        String captcha = loginDto.getCaptcha();
        // 获取之前保存的验证码，与前台传来的验证码进行匹配
        Object attribute = request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
        if(attribute==null){
            return JsonData.buildError("验证码以失效");
        }

        if(!captcha.equals(attribute)){
            return JsonData.buildError("验证码错误");
        }
        // 用户信息
        SysUser user = sysUserService.findByName(username);
        if(user ==null){
            return JsonData.buildError("账号不存在");
        }

        if(!PasswordUtils.matches(user.getSalt(),password,user.getPassword())){
            return JsonData.buildError("密码不正确");
        }

        if(user.getStatus()==0){
            return JsonData.buildError("账号已锁定，请联系管理员");
        }
        // 把当前验证码舍弃
        request.getSession().setAttribute(Constants.KAPTCHA_SESSION_KEY,"");
        // 系统登陆认证
      JwtAuthenticationToken token =  SecurityUtils.login(request,username,password,authenticationManager);
        return JsonData.buildSuccess(token);
    }


    /**
     * 验证码接口
     * @param response
     * @param request
     * @throws IOException
     */
    @GetMapping("captcha.jpg")
    public void captcha(HttpServletResponse response,HttpServletRequest request) throws IOException {
        response.setHeader("Cache-Control","no-store, no-cache");
        response.setContentType("image/jpeg");
        //生成验证
        String text = producer.createText();
        BufferedImage image = producer.createImage(text);

        // 保存到session 或者redis
        request.getSession().setAttribute(Constants.KAPTCHA_SESSION_KEY,text);
        ServletOutputStream outputStream = response.getOutputStream();
        ImageIO.write(image,"jpg",outputStream);
        IOUtils.closeQuietly(outputStream);
    }

}

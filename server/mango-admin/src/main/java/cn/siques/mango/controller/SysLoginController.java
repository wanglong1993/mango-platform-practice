package cn.siques.mango.controller;

import cn.siques.mango.config.RedisUtils;
import cn.siques.mango.controller.dto.RegisterDto;
import cn.siques.mango.service.SysLoginLogService;


import cn.siques.mango.controller.dto.LoginDto;

import cn.siques.mangocommon.Page.PageRequest;
import cn.siques.mangocommon.Page.PageResult;
import cn.siques.mangocommon.dto.JsonData;
import cn.siques.mangocommon.utils.*;
import cn.siques.mangocore.entity.SysUser;
import cn.siques.mango.service.SysUserService;
import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import jdk.nashorn.internal.runtime.regexp.JoniRegExp;
import org.apache.commons.compress.utils.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Collection;

@RestController()
@RequestMapping("/api/sys/v1/pub/")
public class SysLoginController {
    @Autowired
    private Producer producer;

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private SysLoginLogService sysLoginLogService;

    @Value("${server.port}")
    private String port;
    @GetMapping("test")
    public JsonData test(){
        return JsonData.buildSuccess(port);

    }
    @Autowired
    public  RedisUtils<String,String> redisUtils;




    @PostMapping("/online")
    public JsonData onLine(@RequestBody PageRequest pageRequest){
        PageResult page = sysLoginLogService.findPage(pageRequest);
        return JsonData.buildSuccess(page);
    }


    @PostMapping("/register")
    public JsonData register(@RequestBody RegisterDto registerDto){
        String account = registerDto.getAccount();
        String password = registerDto.getPassword();
        SysUser sysUser = new SysUser();
        sysUser.setName(account);
        PasswordEncoder passwordEncoder = new PasswordEncoder("");
        String encode = passwordEncoder.encode(password);
        sysUser.setPassword(encode);
        System.out.println(sysUser);
        int save = sysUserService.save(sysUser);
        return JsonData.buildSuccess(save);
    }


    @GetMapping("/logout")
    public JsonData logout(HttpServletRequest request){
        String username = SecurityUtils.getUsername();
        try {
            redisUtils.deleteKey(username);
        } catch (SecurityException e) {
            throw new SecurityException(HttpStatus.UNAUTHORIZED.toString());
        }
        sysLoginLogService.loginOutLog(username, IPUtils.getIpAddr(request));
        return JsonData.buildSuccess(1);
    }


    @PostMapping("/login")
    public JsonData login(@RequestBody LoginDto loginDto,HttpServletRequest request){
        String username = loginDto.getAccount();
        String password = loginDto.getPassword();
        String captcha = loginDto.getCaptcha();
        // 获取之前保存的验证码，与前台传来的验证码进行匹配
        Object attribute = request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
        if(attribute==null){
            return JsonData.buildError("验证码已失效");
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
        // token存入
      JwtAuthenticationToken token =  SecurityUtils.login(request,username,password,authenticationManager);

        redisUtils.setKey(token.getPrincipal().toString(),token.getToken());
        sysLoginLogService.writeLoginLog(username, IPUtils.getIpAddr(request));

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

//        response.setHeader("Access-Control-allow-credentials","true");
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

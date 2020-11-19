package cn.siques.mango.controller;

import cn.siques.mango.config.OauthResourceTokenConfig;
import cn.siques.mango.config.RedisUtils;
import cn.siques.mango.controller.dto.RegisterDto;
import cn.siques.mango.service.SysLoginLogService;


import cn.siques.mango.controller.dto.LoginDto;

import cn.siques.Page.PageRequest;
import cn.siques.Page.PageResult;
import cn.siques.dto.JsonData;
import cn.siques.utils.*;
import cn.siques.mango.entity.SysUser;
import cn.siques.mango.service.SysUserService;
import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController()
@RequestMapping("/api/sys/v1/pub/")
public class SysLoginController {
    @Autowired
    private Producer producer;

    @Autowired
    private SysUserService sysUserService;


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

    @Autowired
    BCryptPasswordEncoder passwordEncoder;



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
        sysUser.setPassword(passwordEncoder.encode(password));

        boolean save = sysUserService.save(sysUser);
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
//        sysLoginLogService.loginOutLog(username, IPUtils.getIpAddr(request));
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

        if(!passwordEncoder.matches(password,user.getPassword())){

            return JsonData.buildError("密码不正确");
        }

        if(user.getStatus()==0){
            return JsonData.buildError("账号已锁定，请联系管理员");
        }
        // 把当前验证码舍弃
        request.getSession().setAttribute(Constants.KAPTCHA_SESSION_KEY,"");

        // 系统登陆认证
        // token存入
//      JwtAuthenticationToken token =  SecurityUtils.login(request,username,password,authenticationManager);
        System.out.println(password);
        String accessToken = getAccessToken(username, password);


        JwtAuthenticationToken token = new JwtAuthenticationToken (username, "").setToken("Bearer "+accessToken);
        List userInfo = getUserInfo(accessToken);


        token.setPermissions(userInfo);
//        redisUtils.setKey(token.getPrincipal().toString(),token.getToken());
//        sysLoginLogService.writeLoginLog(username, IPUtils.getIpAddr(request));

        return JsonData.buildSuccess(token);
    }

    @Autowired
    OauthResourceTokenConfig resource;

    @Value("${security.oauth2.client.access-token-uri}")
    private String accessTokenUri;


    @Value("${security.oauth2.client.user-info-uri}")
    private String userInfoUri;
    /**
     * 不使用授权码
     * @return
     */
    public String getAccessToken(String username,String password)   {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        String authorization = resource.encodeClient();
        httpHeaders.add("Authorization", authorization);

        MultiValueMap<String, String> param = new LinkedMultiValueMap<>();

        param.add("grant_type", "password");
        param.add("username", username);
        param.add("password", password);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(param, httpHeaders);
        ResponseEntity<Map> response = restTemplate.postForEntity(accessTokenUri, request , Map.class);
        Map result = response.getBody();


        return (String)result.get("access_token");
    }

    /**
     * 获取用户权限信息
     * @param accessToken
     * @return
     */
    public List  getUserInfo(String accessToken){
        RestTemplate restTemplate = new RestTemplate();
        Map result = restTemplate.getForObject(userInfoUri+"?access_token="+accessToken,Map.class);
        List<String> list = (List) result.get("authorities");
        return list;
    }

    /**
     * 验证码接口
     * @param response
     * @param request
     * @throws IOException
     */
    @GetMapping("captcha.jpg")
    public void captcha(HttpServletResponse response,HttpServletRequest request) throws IOException {


        System.out.println(SecurityContextHolder.getContext().getAuthentication().getPrincipal());

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

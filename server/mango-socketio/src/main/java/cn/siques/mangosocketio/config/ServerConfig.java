package cn.siques.mangosocketio.config;


import cn.hutool.core.util.StrUtil;
import cn.siques.mangocommon.utils.JwtTokenUtils;
import cn.siques.mangocommon.utils.SecurityUtils;
import com.corundumstudio.socketio.AuthorizationListener;
import com.corundumstudio.socketio.HandshakeData;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.annotation.SpringAnnotationScanner;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.context.SecurityContextHolder;

@Configuration
@EnableConfigurationProperties({WsConfig.class})
public class ServerConfig {


    // 验证token是否过期
    @Bean
    public SocketIOServer server(WsConfig wsConfig){
        com.corundumstudio.socketio.Configuration config = new com.corundumstudio.socketio.Configuration();
        config.setHostname(wsConfig.getHost());
        config.setPort(wsConfig.getPort());
        config.setAuthorizationListener(new AuthorizationListener() {
            @Override
            public boolean isAuthorized(HandshakeData data) {
                String token = data.getSingleUrlParam("token");
                Boolean tokenExpired = JwtTokenUtils.isTokenExpired(token);
                if(!tokenExpired){
                    String usernameFromToken = JwtTokenUtils.getUsernameFromToken(token);
                    return StrUtil.isNotBlank(usernameFromToken);
                }
                return  false;
            }
        });

        return new SocketIOServer(config);
    }

    /**
     * socketio 扫描自定义注解
     */
    @Bean
    public SpringAnnotationScanner springAnnotationScanner(SocketIOServer server){
        return new SpringAnnotationScanner(server);
    }



}

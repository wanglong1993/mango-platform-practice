package cn.siques.mango.config.security;


//import cn.siques.mangocommon.utils.JwtUserDetails;
//import cn.siques.mangocommon.utils.PasswordEncoder;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//
//// 登录认证
//public class JwtAuthenticationProvider extends DaoAuthenticationProvider {
//    @Override
//    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
//      // 没有密码
//        if(authentication.getCredentials()==null){
//          logger.debug("Authentication faild: no credentials provided");
//          throw new BadCredentialsException(messages.getMessage("AbstractUserDetialsAuthenticationProvider.badCredentials","Bad credentials"));
//      }
//      String presentedPassword =authentication.getCredentials().toString();
//        String salt = ((JwtUserDetails) userDetails).getSalt();
//        // 密码比对
//        if(!new PasswordEncoder(salt).matches(userDetails.getPassword(),presentedPassword)){
//            logger.debug("Authenticaiton failed：password dost not match" );
//            throw new BadCredentialsException(messages.getMessage("AbstractUserDetialsAuthenticationProvider.badCredentials","Bad credentials"));
//
//        }
//    }
//
//    public JwtAuthenticationProvider(UserDetailsService userDetailsService) {
//       setUserDetailsService(userDetailsService);
//    }
//}

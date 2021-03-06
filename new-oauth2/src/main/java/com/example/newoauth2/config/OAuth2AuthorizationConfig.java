//package com.example.newoauth2.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.crypto.password.NoOpPasswordEncoder;
//import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
//import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
//import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
//import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
//import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
//import org.springframework.security.oauth2.provider.token.TokenStore;
//import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;
//
//@Configuration
//@EnableAuthorizationServer
///**
// * 这个注解告诉 Spring 这个应用是 OAuth2 的授权服务器,
// * 提供/oauth/authorize,/oauth/token,/oauth/check_token,/oauth/confirm_access,/oauth/error
// *
// * @author ningcs
// */
//
//public class OAuth2AuthorizationConfig extends AuthorizationServerConfigurerAdapter {
//
//    @Autowired
//    private AuthenticationManager authenticationManager;
//
//
//
//    @Autowired
//    private TokenStore tokenStore;
//
//    @Override
//    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
//        clients.inMemory()
//                .withClient("test")//客户端ID
//                .authorizedGrantTypes("password", "refresh_token")//设置验证方式
//                .scopes("read", "write")
//                .secret("123456")
//                .accessTokenValiditySeconds(10000) //token过期时间
//                .refreshTokenValiditySeconds(10000); //refresh过期时间
//        //dGVzdDoxMjM0NTY=
//    }
//
//    @Override
//    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
//        endpoints.tokenStore(tokenStore)
//                .authenticationManager(authenticationManager);
////                .userDetailsService(userService); //配置userService 这样每次认证的时候会去检验用户是否锁定，有效等
//    }
//
//    @Bean
//    public TokenStore tokenStore() {
//        //使用内存的tokenStore
//        return new InMemoryTokenStore();
//    }
//
//    @Override
//    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
//        security
//                .allowFormAuthenticationForClients()
//                .checkTokenAccess("permitAll()")
//                .passwordEncoder(NoOpPasswordEncoder.getInstance());
//    }
//}

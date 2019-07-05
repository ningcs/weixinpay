package com.example.newoauth2.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;

/**
 * @description:
 * @author: ningcs
 * @create: 2019-07-03 16:37
 * OAuth 授权服务器配置
 * https://segmentfault.com/a/1190000014371789
 **/
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig1 extends AuthorizationServerConfigurerAdapter {

    private static final String DEMO_RESOURCE_ID = "order";

    @Autowired
    AuthenticationManager authenticationManager;
//    @Autowired
//    RedisConnectionFactory redisConnectionFactory;

    @Autowired
    private TokenStore tokenStore;

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        String finalSecret = "{bcrypt}"+new BCryptPasswordEncoder().encode("123456");
        System.out.println(finalSecret);
//        clients.setBuilder(builder);
        //这里通过实现 ClientDetailsService接口
//        clients.withClientDetails(new BaseClientDetailService());

        clients.inMemory()
                .withClient("client_1")
                .secret("123456")
                .authorizedGrantTypes("password", "refresh_token")
                .scopes("read", "write")
                .accessTokenValiditySeconds(10000)
                .refreshTokenValiditySeconds(10000);



//
//        clients.inMemory()
//                .withClient("test")//客户端ID
//                .authorizedGrantTypes("password", "refresh_token")//设置验证方式
//                .scopes("read", "write")
//                .secret("123456")
//                .accessTokenValiditySeconds(10000) //token过期时间
//                .refreshTokenValiditySeconds(10000); //refresh过期时间
//        //配置客户端,一个用于password认证一个用于client认证
//        clients.inMemory()
//            .withClient("client_1")
//            .resourceIds(DEMO_RESOURCE_ID)
//            .authorizedGrantTypes("client_credentials", "refresh_token")
//            .scopes("select")
//            .authorities("oauth2")
//            .secret(finalSecret)
//            .and()
//            .withClient("client_2")
//            .resourceIds(DEMO_RESOURCE_ID)
//            .authorizedGrantTypes("password", "refresh_token")
//            .scopes("select")
//            .authorities("oauth2")
//            .secret(finalSecret)
//            .and()
//            .withClient("client_code")
//            .resourceIds(DEMO_RESOURCE_ID)
//            .authorizedGrantTypes("authorization_code", "client_credentials", "refresh_token",
//                    "password", "implicit")
//            .scopes("all")
//            //.authorities("oauth2")
//            .redirectUris("http://www.baidu.com")
//            .accessTokenValiditySeconds(1200)
//            .refreshTokenValiditySeconds(50000);
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints
                .tokenStore(tokenStore)
                .authenticationManager(authenticationManager)
                .allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST);

//        //配置TokenService参数
//        DefaultTokenServices tokenService = new DefaultTokenServices();
//        tokenService.setTokenStore(endpoints.getTokenStore());
//        tokenService.setSupportRefreshToken(true);
//        tokenService.setClientDetailsService(endpoints.getClientDetailsService());
//        tokenService.setTokenEnhancer(endpoints.getTokenEnhancer());
//        tokenService.setAccessTokenValiditySeconds((int) TimeUnit.DAYS.toSeconds(30)); //30天
//        tokenService.setRefreshTokenValiditySeconds((int) TimeUnit.DAYS.toSeconds(50)); //50天
//        tokenService.setReuseRefreshToken(false);
//        endpoints.tokenServices(tokenService);

    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
        //允许表单认证
        //这里增加拦截器到安全认证链中，实现自定义认证，包括图片验证，短信验证，微信小程序，第三方系统，CAS单点登录
        //addTokenEndpointAuthenticationFilter(IntegrationAuthenticationFilter())
        //IntegrationAuthenticationFilter 采用 @Component 注入
        oauthServer.allowFormAuthenticationForClients()
                .tokenKeyAccess("isAuthenticated()")
                .checkTokenAccess("permitAll()");
    }
    @Bean
    public TokenStore tokenStore() {
        //使用内存的tokenStore
        return new InMemoryTokenStore();
    }



}
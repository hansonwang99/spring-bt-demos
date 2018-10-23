package cn.codesheep.springbt_security_jwt.config;

import cn.codesheep.springbt_security_jwt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserService userService;

    @Bean
    public JwtAuthenticationTokenFilter authenticationTokenFilterBean() throws Exception {
        return new JwtAuthenticationTokenFilter();
    }

    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure( AuthenticationManagerBuilder auth ) throws Exception {  // 通过重载，配置user-detail服务 (即用户存储服务，如：基于内存的用户存储/基于数据表 等)
        auth.userDetailsService( userService ).passwordEncoder( new BCryptPasswordEncoder() );
    }

    @Override
    protected void configure( HttpSecurity httpSecurity ) throws Exception {

        httpSecurity
                .csrf().disable() // 由于使用的是JWT，我们这里不需要csrf
                .sessionManagement().sessionCreationPolicy( SessionCreationPolicy.STATELESS ).and()  // 基于token，所以不需要session
                .authorizeRequests()
                .antMatchers(
                        HttpMethod.GET,
                        "/",
                        "/*.html",
                        "/favicon.ico",
                        "/**/*.html",
                        "/**/*.css",
                        "/**/*.js"
                ).permitAll()
                .antMatchers("/authentication/**").permitAll()  // 对于获取token的rest api要允许匿名访问
                .anyRequest().authenticated();  // 除上面外的所有请求全部需要鉴权认证
        // 添加JWT filter
        httpSecurity
                .addFilterBefore( authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class );
        httpSecurity.headers().cacheControl(); // 禁用缓存
    }

}

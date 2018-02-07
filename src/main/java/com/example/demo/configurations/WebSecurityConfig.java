package com.example.demo.configurations;

import com.example.demo.constants.ProjectUrls;
import com.example.demo.constants.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.annotation.Resource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private static PasswordEncoder encoder;

    @Autowired
    @Qualifier("userInfoService")
    private UserDetailsService userDetailsService;

    @Autowired
    CustomSuccessHandler customSuccessHandler;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .formLogin()
                .loginPage(ProjectUrls.Login)
                .failureUrl(ProjectUrls.Login + "?error")
                .loginProcessingUrl(ProjectUrls.Login)
                .permitAll()
                .successHandler(customSuccessHandler)
                .and()
                .exceptionHandling().accessDeniedPage(ProjectUrls.ErrorPage403)
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher(ProjectUrls.Logout))
                .logoutSuccessUrl(ProjectUrls.Login + "?logout")
                .permitAll()
                .and()
                .authorizeRequests()
                .antMatchers("/static/**").permitAll()
                .antMatchers(ProjectUrls.ErrorPage403).permitAll()
                .antMatchers(ProjectUrls.ErrorPage404).permitAll()
                .antMatchers(ProjectUrls.ErrorPage500).permitAll()
                .antMatchers(ProjectUrls.Admin + "/**").hasAnyAuthority(Role.ADMINISTRATOR.name())
                .antMatchers(ProjectUrls.User + "/**").hasAnyAuthority(Role.ADMINISTRATOR.name(), Role.USER.name())
                .anyRequest().denyAll();
        http.authorizeRequests().anyRequest().authenticated()
                .and()
                .rememberMe()
                .key("remember-me")
                .tokenValiditySeconds(86400);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        if (encoder == null) {
            encoder = new BCryptPasswordEncoder();
        }
        return encoder;
    }
}

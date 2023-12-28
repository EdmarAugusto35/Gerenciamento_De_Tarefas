package com.example.backend.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
public class ConfiguracaoSeguranca extends WebSecurityConfiguration {

    protected void configure(HttpSecurity http) throws Exception{
        http.httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers("/main/secured").permitAll()
                .anyRequest().authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }


    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("admin")
                .password("{noop}admin")
                .roles("USER")
                .and()
                .withUser("ed")
                .password("{noop}1234567")
                .roles("USER");
    }

    public void configure(final WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/h2-console/**");
    }
}

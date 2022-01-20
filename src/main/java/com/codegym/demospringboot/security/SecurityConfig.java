package com.codegym.demospringboot.security;

import com.codegym.demospringboot.service.AppUserServiceImpl;
import com.codegym.demospringboot.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    AppUserServiceImpl appUserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/").permitAll()
                .and().authorizeRequests().antMatchers("/home").hasAnyAuthority("ROLE_USER", "ROLE_ADMIN")
                .and()
                .formLogin().loginPage("/login")
                .and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"));
        http.csrf().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(appUserService).passwordEncoder(NoOpPasswordEncoder.getInstance());
    }
}

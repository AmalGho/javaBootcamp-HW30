package com.example.recyclingsystem.Config;

import com.example.recyclingsystem.Service.MyUserDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class ConfigurationSecurity {

    private final MyUserDetailService myUserDetailService;

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(myUserDetailService);
        daoAuthenticationProvider.setPasswordEncoder(new BCryptPasswordEncoder());
        return daoAuthenticationProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                .and()
                .authenticationProvider(daoAuthenticationProvider())
                .authorizeHttpRequests()
                .requestMatchers("/api/v1/auth/adminRegister","/api/v1/auth/companyRegister", "/api/v1/auth/residentRegister").permitAll()
                .requestMatchers("/api/v1/detail/**","/api/v1/company/getAll","/api/v1/resident/getAll").hasAuthority("ADMIN")
                .requestMatchers("/api/v1/company/addProfile","/api/v1/company/updateEmail","/api/v1/company/delete","/api/v1/request/getAll","/api/v1/request/buyRequest","/api/v1/request/getCompanyPurchases").hasAuthority("COMPANY")
                .requestMatchers("/api/v1/resident/addProfile","/api/v1/resident/updateEmail","/api/v1/resident/delete","/api/v1/resident/wallet","/api/v1/resident/prize","/api/v1/request/getUserRequests","/api/v1/request/add","/api/v1/request/update","/api/v1/request/delete").hasAuthority("RESIDENT")
                .anyRequest().authenticated()
                .and()
                .logout().logoutUrl("/api/v1/auth/logout")
                .deleteCookies("JSESSIONID")
                .invalidateHttpSession(true)
                .and()
                .httpBasic();
        return http.build();
    }

}

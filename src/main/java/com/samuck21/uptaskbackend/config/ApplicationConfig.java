package com.samuck21.uptaskbackend.config;

import com.samuck21.uptaskbackend.service.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {
    private  final CustomUserDetailsService customUserDetailsService;
    private  final PasswordEncoder passwordEncoder;
    @Bean
    public AuthenticationManager  authenticationManager(CustomUserDetailsService customUserDetailsService,PasswordEncoder passwordEncoder){
     return  authentication -> {
         String username = authentication.getName();
         String password = authentication.getCredentials().toString();

         UserDetails user =  customUserDetailsService.loadUserByUsername(username);
         if(!passwordEncoder.matches(password,user.getPassword())){
             throw new BadCredentialsException("Credenciales invalidas");
         }
         return new UsernamePasswordAuthenticationToken(user, password,user.getAuthorities());
     };
    }
    @Bean
    public UserDetailsService userDetailsService(){
        return customUserDetailsService;
    }

}

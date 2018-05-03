package br.com.baiocchilousa.socialbooks.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
        auth.inMemoryAuthentication().withUser("algaworks")
        .password(passwordEncoder().encode("s3nh4")).roles("USER");
    }
    
    protected void configure(HttpSecurity http) throws Exception {
        http.
            authorizeRequests().anyRequest().authenticated()
            .and()
                .httpBasic()
            .and()
                .csrf().disable();
               
    }
    
    //Encriptação de strings passadas para o método: passwordEncoder().encode("senha")
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
}

package br.com.baiocchilousa.socialbooks.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
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
        auth.inMemoryAuthentication().withUser("usuario")
        .password(passwordEncoder().encode("senha")).roles("USER");
    }
    
    protected void configure(HttpSecurity http) throws Exception {
        http.
            authorizeRequests()
            .antMatchers("/h2-console/**").permitAll()//Permite acesso ao console do H2
            .antMatchers(HttpMethod.OPTIONS, "/**").permitAll() //Permite o envio das OPTIONS do HTTP (CORS)
            .anyRequest().authenticated()
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

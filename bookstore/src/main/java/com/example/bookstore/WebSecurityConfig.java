package com.example.bookstore;

import com.example.bookstore.service.UserDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableWebSecurity
@ComponentScan("com.example.bookstore")
public class WebSecurityConfig {
    @Autowired
    private UserDetailServiceImpl userDetailService;
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, HandlerMappingIntrospector introspector) throws Exception {
        MvcRequestMatcher.Builder mvcMatcherBuilder = new MvcRequestMatcher.Builder(introspector);
        return http
                .authorizeHttpRequests(authorizeHttpRequests -> authorizeHttpRequests
                        .requestMatchers(mvcMatcherBuilder.pattern("/css/**"), mvcMatcherBuilder.pattern("/signup"), mvcMatcherBuilder.pattern("/saveuser")).permitAll()
                        .anyRequest().authenticated())
                .formLogin(form -> form.loginPage("/login").defaultSuccessUrl("/booklist", true).permitAll())
                .logout(logout -> logout.permitAll()).build();
    }
    @Bean
    public UserDetailsService userDetailsService() {
        List<UserDetails> users = new ArrayList<UserDetails>();
        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        UserDetails user1 = User.withUsername("user").password(passwordEncoder.encode("user")).roles("USER").build();
        users.add(user1);
        UserDetails user2 = User.withUsername("admin").password(passwordEncoder.encode("admin")).roles("USER", "ADMIN").build();
        users.add(user2);
        return new InMemoryUserDetailsManager(users);
    }
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailService).passwordEncoder(new BCryptPasswordEncoder());
    }
}

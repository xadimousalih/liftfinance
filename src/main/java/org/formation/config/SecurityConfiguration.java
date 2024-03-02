//package org.formation.config;
//
//import org.formation.service.CustomUserDetailsService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfiguration {
//
//	@Autowired
//    private CustomUserDetailsService userDetailsService;
//
//     // omitting constructor
//
////    @Bean
////    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
////        http.userDetailsService(userDetailsService)
////            .authorizeRequests()
////            .antMatchers("/resources/**").permitAll()
////            .anyRequest()
////            .authenticated()
////            .and()
////            .formLogin()
////            .loginPage("/login")
////            .permitAll()
////            .successForwardUrl("/pages/dashboard")
////            .and()
////            .logout()
////            .permitAll()
////            .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
////            .logoutSuccessUrl("/login");
////        return http.build();
////    }
//    
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http.formLogin()
//          .loginPage("/login.html")
//          .loginProcessingUrl("/perform_login")
//          .defaultSuccessUrl("/homepage.html",true)
//          .failureUrl("/login.html?error=true");
//        return http.build();
//    }
//
//}
package org.ey.iot.smartparking.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.ey.iot.smartparking.model.Parameters;
import org.ey.iot.smartparking.model.ResourcePaths;
import org.ey.iot.smartparking.security.AuthFailureHandler;
import org.ey.iot.smartparking.security.AuthSuccessHandler;
import org.ey.iot.smartparking.security.HttpAuthenticationEntryPoint;
import org.ey.iot.smartparking.security.HttpLogoutSuccessHandler;
import org.ey.iot.smartparking.security.IOTUserDetailsService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
@ComponentScan(value = "org.ey")
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private static final String LOGIN_PATH = ResourcePaths.User.ROOT + ResourcePaths.User.LOGIN;

    @Autowired
    private IOTUserDetailsService userDetailsService;
    @Autowired
    private HttpAuthenticationEntryPoint authenticationEntryPoint;
    @Autowired
    private AuthSuccessHandler authSuccessHandler;
    @Autowired
    private AuthFailureHandler authFailureHandler;
    @Autowired
    private HttpLogoutSuccessHandler logoutSuccessHandler;

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    @Override
    public UserDetailsService userDetailsServiceBean() throws Exception {
        return super.userDetailsServiceBean();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(new ShaPasswordEncoder());

        return authenticationProvider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	 http.csrf().disable()
         .authorizeRequests()
         .antMatchers("/").permitAll()
         .antMatchers("/SmartParking/*").permitAll()
         .anyRequest().authenticated()
         .and()
         .authenticationProvider(authenticationProvider())
         .exceptionHandling()
         .authenticationEntryPoint(authenticationEntryPoint)
         .and()
         .formLogin()
         .permitAll()
         .loginProcessingUrl(LOGIN_PATH)
         .usernameParameter(Parameters.USERNAME)
         .passwordParameter(Parameters.PASSWORD)
         .successHandler(authSuccessHandler)
         .failureHandler(authFailureHandler)
         .and()
         .logout()
         .permitAll()
         
         .logoutRequestMatcher(new AntPathRequestMatcher(LOGIN_PATH, "DELETE"))
         .logoutSuccessHandler(logoutSuccessHandler)
         .and()
         .sessionManagement()
         .maximumSessions(1);
    	 http.httpBasic();
 http.authorizeRequests().anyRequest().authenticated();
    	
    	
    	
    	/*http.authorizeRequests().anyRequest().authenticated().and().
        csrf().disable();*/
        
    }
}

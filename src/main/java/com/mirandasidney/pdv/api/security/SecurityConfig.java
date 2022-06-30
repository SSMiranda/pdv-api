package com.mirandasidney.pdv.api.security;

import com.mirandasidney.pdv.api.enums.Role;
import com.mirandasidney.pdv.api.service.UserServiceImpl;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.http.HttpSessionListener;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter implements HttpSessionListener {

    private final UserServiceImpl userService;

    public SecurityConfig(UserServiceImpl userService) {
    this.userService = userService;
    }

    /**
     *  Método responsável por consultar o usuário no banco de dados com Spring Security
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(new BCryptPasswordEncoder(12));
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf()
                .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                    .disable()
                .authorizeRequests()
                    .antMatchers(HttpMethod.POST, "/auth/login").permitAll()
                    .antMatchers(HttpMethod.POST, "/users").hasAuthority(Role.ADMIN.getName())
                .anyRequest()
                .authenticated()
                .and()
                    .addFilterAfter(new JWTFilter("/auth/login", authenticationManager()),
                        UsernamePasswordAuthenticationFilter.class)
                    .addFilterBefore(new JwtApiAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                    .httpBasic()
                .and()
                    .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//              Filtra as requisições para login com JWT
                .and()
                    .logout()
                    .logoutSuccessUrl("/auth/logout?error=true")
                    .invalidateHttpSession(true)
                    // Mapeia o logout do sistema
                    .logoutRequestMatcher(new AntPathRequestMatcher("/auth/logout"));
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/swagger-ui/**", "/v3/api-docs/**", "/h2-console/**", "*.html");
    }
}

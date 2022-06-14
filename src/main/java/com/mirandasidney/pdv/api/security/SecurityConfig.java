package com.mirandasidney.pdv.api.security;

import com.mirandasidney.pdv.api.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
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
    private static final String[] AUTH_WHITELIST = {
            "/",
            "/auth/login",
            "/auth/logout",
            "/swagger-ui/**"
    };

    @Autowired
    public SecurityConfig(UserServiceImpl userService) {
    this.userService = userService;
    }

    /**
     *  Método responsável por consultar o usuário no banco de dados com Spring Security
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf()
                .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                    .disable()
                .authorizeRequests()
                    .antMatchers(AUTH_WHITELIST).permitAll()
                    .antMatchers(HttpMethod.GET, "/h2-console").permitAll()
                    .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
//                    .antMatchers("/api/**").authenticated()
                    .antMatchers(HttpMethod.POST, "/users").hasRole("ADMIN")
                // Redireciona para index quando logout
                .anyRequest().authenticated()
                .and()
                .logout().logoutSuccessUrl("/auth/login")

                // Mapeia o logout do sistema
                .logoutRequestMatcher(new AntPathRequestMatcher("/auth/logout"))

//                Filtra as requisições para login com JWT
                .and()
                .addFilterAfter(new JWTFilter("/auth/login", authenticationManager()),
                        UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(new JwtApiAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
//                .addFilterAfter(new JWTFilter("/swagger-ui/**", authenticationManager()),
//                        UsernamePasswordAuthenticationFilter.class)
//                .addFilterBefore(new JwtApiAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                .httpBasic().and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/swagger-ui/**", "/v3/api-docs/**", "/h2-console");
    }
}

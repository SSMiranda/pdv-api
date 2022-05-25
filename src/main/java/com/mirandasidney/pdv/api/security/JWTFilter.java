package com.mirandasidney.pdv.api.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mirandasidney.pdv.api.domain.User;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Sidney Miranda
 */

public class JWTFilter extends AbstractAuthenticationProcessingFilter {

    /**
     * Configura o gerenciador de autenticação
     */
    public JWTFilter(String url, AuthenticationManager authenticationManager) {
        super(new AntPathRequestMatcher(url));
        setAuthenticationManager(authenticationManager);
    }

    /**
     * @return Retorna o usuário ao processar a autenticação
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException, IOException {

        User user = new ObjectMapper().readValue(request.getInputStream(), User.class);

        return getAuthenticationManager().authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication)
            throws IOException {
        new JWTTokenAuthenticationService().addAuthentication(response, authentication.getName());
    }
}

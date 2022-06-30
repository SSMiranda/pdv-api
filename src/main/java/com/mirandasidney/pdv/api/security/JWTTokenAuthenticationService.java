package com.mirandasidney.pdv.api.security;

import com.mirandasidney.pdv.api.ApplicationContextLoad;
import com.mirandasidney.pdv.api.entities.User;
import com.mirandasidney.pdv.api.repository.UserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

/**
 * @author Sidney Miranda
 * Serviço que adiciona a autenticação e validação do token
 */

@Service
@PropertySource("classpath:application.properties")
public class JWTTokenAuthenticationService {

    private static final String TOKEN_PREFIX = "Bearer ";
    private static final String HEADER_STRING = "Authorization";
    private static final long EXPIRATION_TIME = 3600000;
    private static final String SECRET = "aoiscnsck3423";

    @Value("${jwt.secret}")
    private String palavra;


    /**
     * Método que efetua a liberação do CORS no navegador
     */
    private void corsRelease(HttpServletResponse response) {
        if (response.getHeader("Access-Control-Allow-Origin") == null) {
            response.addHeader("Access-Control-Allow-Origin", "*");
        }
        if (response.getHeader("Access-Control-Allow-Headers") == null) {
            response.addHeader("Access-Control-Allow-Headers", "*");
        }
        if (response.getHeader("Access-Control-Request-Headers") == null) {
            response.addHeader("Access-Control-Request-Headers", "*");
        }
        if (response.getHeader("Access-Control-Allow-Methods") == null) {
            response.addHeader("Access-Control-Allow-Methods", "*");
        }
    }

    /**
     * Método que gera o token com o JWT e retorna o response para o client
     */
    public void addAuthentication(HttpServletResponse response, String username) throws IOException {
        String JWT = Jwts.builder()
                .setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();

        String token = TOKEN_PREFIX + JWT;

        response.addHeader(HEADER_STRING, token);

        this.corsRelease(response);

        response.getWriter().write("{\"Authorization\": \"" + token + "\"}");
    }

    /**
     * Método que retorna o usuário validado com o token ou nulo caso o usuário não seja válido
     */
    public Authentication getAuthentication(HttpServletRequest request, HttpServletResponse response) {
        String token = request.getHeader(HEADER_STRING);
        if (token != null) {
            String user = Jwts.parser()
                    .setSigningKey(SECRET)
                    .parseClaimsJws(token.replace(TOKEN_PREFIX, "").trim())
                    .getBody()
                    .getSubject();

            if (user != null) {
                User userFound = ApplicationContextLoad
                        .getApplicationContext()
                        .getBean(UserRepository.class)
                        .findUserByUsername(user);

                if (userFound != null) {
                    return new UsernamePasswordAuthenticationToken(
                            userFound.getUsername(),
                            userFound.getPassword(),
                            userFound.getAuthorities());
                }
            }
        }
        return null;
    }
}

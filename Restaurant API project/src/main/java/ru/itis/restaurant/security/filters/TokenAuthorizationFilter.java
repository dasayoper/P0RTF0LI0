package ru.itis.restaurant.security.filters;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import ru.itis.restaurant.exceptions.AccountNotFoundException;
import ru.itis.restaurant.repositories.BlacklistRepository;
import ru.itis.restaurant.security.jwt.JwtProvider;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

@RequiredArgsConstructor
public class TokenAuthorizationFilter extends OncePerRequestFilter {
    public static final String API_LOGIN = "/login";
    private final ObjectMapper objectMapper;
    private final JwtProvider jwtProvider;
    private final BlacklistRepository blacklistRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if(request.getRequestURI().equals(API_LOGIN)) {
            filterChain.doFilter(request, response);
        } else {
            String token = jwtProvider.getToken(request);
            if(token != null) {
                if(jwtProvider.validate(token) && !blacklistRepository.exists(token)) {
                    try {
                        Authentication authentication = jwtProvider.getAuthentication(token);
                        SecurityContextHolder.getContext().setAuthentication(authentication);
                        filterChain.doFilter(request, response);
                    } catch (AccountNotFoundException e) {
                        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                        objectMapper.writeValue(response.getWriter(), Collections.singletonMap("Error", "No author with such token"));
                    }
                } else {
                    response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                    response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                    objectMapper.writeValue(response.getWriter(), Collections.singletonMap("Error", "Token is expired or invalid"));
                }
            } else {
                filterChain.doFilter(request, response);
            }
        }
    }
}

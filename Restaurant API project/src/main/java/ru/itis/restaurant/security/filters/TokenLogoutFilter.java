package ru.itis.restaurant.security.filters;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.web.filter.OncePerRequestFilter;
import ru.itis.restaurant.repositories.BlacklistRepository;
import ru.itis.restaurant.security.jwt.JwtProvider;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
public class TokenLogoutFilter extends OncePerRequestFilter {

    private final JwtProvider jwtProvider;
    private final BlacklistRepository blacklistRepository;
    private final static RequestMatcher logoutRequest = new AntPathRequestMatcher("/logout", "GET");


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (logoutRequest.matches(request)) {
            String token = jwtProvider.getToken(request);
            if (token != null) {
                blacklistRepository.save(token);
                SecurityContextHolder.getContext();
                return;
            }
        }

        filterChain.doFilter(request, response);

    }
}

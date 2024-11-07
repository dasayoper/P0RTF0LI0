package ru.itis.restaurant.security.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import ru.itis.restaurant.repositories.AccountsRepository;
import ru.itis.restaurant.repositories.BlacklistRepository;
import ru.itis.restaurant.security.details.AccountUserDetailsService;
import ru.itis.restaurant.security.filters.TokenAuthenticationFilter;
import ru.itis.restaurant.security.filters.TokenAuthorizationFilter;
import ru.itis.restaurant.security.filters.TokenLogoutFilter;
import ru.itis.restaurant.security.jwt.JwtProvider;

@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    private final PasswordEncoder passwordEncoder;
    private final ObjectMapper objectMapper;
    private final AccountUserDetailsService authorUserDetailsService;
    private final AccountsRepository accountsRepository;
    private final JwtProvider jwtProvider;
    private final BlacklistRepository blacklistRepository;

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(authorUserDetailsService).passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        TokenAuthenticationFilter tokenAuthenticationFilter =
                new TokenAuthenticationFilter(authenticationManagerBean(), objectMapper, accountsRepository, jwtProvider);
        tokenAuthenticationFilter.setFilterProcessesUrl("/login");

        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilter(tokenAuthenticationFilter);
        http.addFilterBefore(new TokenAuthorizationFilter(objectMapper, jwtProvider, blacklistRepository),
                UsernamePasswordAuthenticationFilter.class);
        http.addFilterBefore(new TokenLogoutFilter(jwtProvider, blacklistRepository), TokenAuthenticationFilter.class);

        http.authorizeRequests()
                .antMatchers("/logout/**").hasAnyAuthority("ADMIN", "MANAGER", "USER")
                .antMatchers("/login/").permitAll()
                .antMatchers("/sign-up").permitAll()
                .antMatchers("/accounts/**").hasAuthority("ADMIN")
                .antMatchers("/accounts/update-account").authenticated()
                .antMatchers(HttpMethod.GET,"/menu/**").authenticated()
                .antMatchers(HttpMethod.POST, "/menu/**").hasAnyAuthority("ADMIN", "MANAGER")
                .antMatchers(HttpMethod.POST, "/spots/**").hasAnyAuthority("ADMIN", "MANAGER")
                .antMatchers(HttpMethod.GET, "/spots/**").authenticated()
                .antMatchers("/orders/**").authenticated()
                .antMatchers("/bookings/**").authenticated()
                .and()
                .logout()
                .disable();
    }
}

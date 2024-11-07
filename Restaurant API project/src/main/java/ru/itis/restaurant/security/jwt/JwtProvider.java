package ru.itis.restaurant.security.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import ru.itis.restaurant.exceptions.AccountNotFoundException;
import ru.itis.restaurant.models.Account;
import ru.itis.restaurant.repositories.AccountsRepository;
import ru.itis.restaurant.security.details.AccountUserDetails;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.function.Supplier;

@Component
@RequiredArgsConstructor
public class JwtProvider {
    @Value("${jwt.token.secret}")
    private String secret;

    @Value("${jwt.token.expired}")
    private Long expired;

    private final AccountsRepository accountsRepository;

    public Authentication getAuthentication(String token) {
        DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC256(secret)).build().verify(token);
        String email = decodedJWT.getSubject();

        Account account = accountsRepository.findByEmail(email).orElseThrow((Supplier<RuntimeException>) ()
                -> new AccountNotFoundException("Account with such id did not exists")
        );
        AccountUserDetails accountUserDetails = new AccountUserDetails(account);

        return new UsernamePasswordAuthenticationToken(token, accountUserDetails, accountUserDetails.getAuthorities());
    }

    public String getToken(HttpServletRequest request) {
        String tokenHeader = request.getHeader("Authorization");
        if (tokenHeader != null && tokenHeader.startsWith("Bearer ")) {
            return tokenHeader.substring("Bearer ".length());
        }

        return null;
    }

    public boolean validate(String token) {
        DecodedJWT decodedJWT;
        try {
            decodedJWT = JWT.require(Algorithm.HMAC256(secret)).build().verify(token);
        } catch (JWTVerificationException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    public String createToken(AccountUserDetails accountUserDetails) {
        Date startTime = new Date();
        Date endTime = new Date(startTime.getTime() + expired);

        return JWT.create()
                .withSubject(accountUserDetails.getUsername())
                .withClaim("role", accountUserDetails.getAccount().getRole().toString())
                .withIssuedAt(startTime)
                .withExpiresAt(endTime)
                .sign(Algorithm.HMAC256(secret));
    }
}

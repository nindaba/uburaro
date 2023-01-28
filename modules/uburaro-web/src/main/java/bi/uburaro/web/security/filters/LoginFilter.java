package bi.uburaro.web.security.filters;

import bi.uburaro.facade.data.PrincipalData;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Map;

public class LoginFilter extends UsernamePasswordAuthenticationFilter {
    protected String secret;
    protected Integer tokenTtl;

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        PrincipalData user = (PrincipalData) authResult.getPrincipal();
        Algorithm algorithm = Algorithm.HMAC256(secret.getBytes());

        Instant expires = LocalDateTime.now()
                .plusHours(tokenTtl)
                .atZone(ZoneId.systemDefault())
                .toInstant();

        String token = JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(Date.from(expires))
                .sign(algorithm);

        response.setContentType(MediaType.APPLICATION_JSON.toString());
        new ObjectMapper().writeValue(response.getOutputStream(), Map.of("token", token));
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    /**
     * Time in hours for the token to live
     * @param tokenTtl
     */
    public void setTokenTtl(Integer tokenTtl) {
        this.tokenTtl = tokenTtl;
    }
}

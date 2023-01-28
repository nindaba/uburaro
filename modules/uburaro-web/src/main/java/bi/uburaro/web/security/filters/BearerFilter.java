package bi.uburaro.web.security.filters;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.List;

public class BearerFilter extends OncePerRequestFilter {
    private final Environment environment;
    private final UserDetailsService userDetailsService;
    private static final String SECRET = "security.jwt.secret";
    private static final String DEFAULT_SECRET = "security.jwt.secret";
    private static final Logger LOG =  LogManager.getLogger();

    public BearerFilter(Environment environment, UserDetailsService userDetailsService) {
        this.environment = environment;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        if (httpServletRequest.getServletPath().matches("/userapi/login")) {
            filterChain.doFilter(httpServletRequest, httpServletResponse);
        } else {
            final String bearer = httpServletRequest.getHeader("Authorization");
            if (StringUtils.startsWithIgnoreCase(bearer, "bearer ")) {
                try {
                    Algorithm algorithm = Algorithm.HMAC256(environment.getProperty(SECRET,DEFAULT_SECRET).getBytes());
                    final String token = bearer.substring("bearer ".length());
                    JWTVerifier verifier = JWT.require(algorithm).build();
                    DecodedJWT verify = verifier.verify(token);
                    String username = verify.getSubject().split(" ")[0];
                    UserDetails user = userDetailsService.loadUserByUsername(username);

                    if (user == null) {
                        httpServletResponse.sendError(1, "Invalid Token");
                        LOG.debug("User {} was not found",username);
                        filterChain.doFilter(httpServletRequest, httpServletResponse);
                    }

                    Collection<? extends GrantedAuthority> roles = List.of(new SimpleGrantedAuthority("ROLE_BASIC"));
                    Authentication authentication = new UsernamePasswordAuthenticationToken(username,null,roles);

                    SecurityContextHolder.getContext().setAuthentication(authentication);
                    filterChain.doFilter(httpServletRequest, httpServletResponse);
                } catch (Exception x) {
                    httpServletResponse.sendError(1, "Invalid Token");
                    LOG.error("Unable to authorise {}",bearer,x);
                }
            } else {
                filterChain.doFilter(httpServletRequest, httpServletResponse);
            }
        }
    }
}
package bi.uburaro.web.security.manager;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

public class DefaultAuthenticationManager implements AuthenticationManager {
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        return authentication;
    }
}

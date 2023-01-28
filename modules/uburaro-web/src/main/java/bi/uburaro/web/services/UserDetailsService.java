package bi.uburaro.web.services;

import bi.uburaro.core.types.PrincipalType;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsPasswordService;

public abstract class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService, UserDetailsPasswordService {
    public abstract void updatePassword(PrincipalType principal);
}

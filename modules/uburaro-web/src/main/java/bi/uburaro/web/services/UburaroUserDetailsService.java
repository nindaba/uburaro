package bi.uburaro.web.services;

import bi.uburaro.core.services.TypeService;
import bi.uburaro.core.types.PrincipalType;
import bi.uburaro.facade.data.EmployeeData;
import bi.uburaro.facade.data.PrincipalData;
import bi.uburaro.facade.facades.EmployeeFacade;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsPasswordService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

public class UburaroUserDetailsService extends UserDetailsService {
    private final EmployeeFacade employeeFacade;
    private final PasswordEncoder passwordEncoder;
    private final TypeService typeService;

    public UburaroUserDetailsService(EmployeeFacade employeeFacade, PasswordEncoder passwordEncoder, TypeService typeService) {
        this.employeeFacade = employeeFacade;
        this.passwordEncoder = passwordEncoder;
        this.typeService = typeService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return employeeFacade.getEmployeeForCode(username, false);
    }

    @Override
    public UserDetails updatePassword(UserDetails user, String newPassword) {
        if (user instanceof PrincipalData) {
            PrincipalData principalData = (PrincipalData) user;
            principalData.setPassword(passwordEncoder.encode(newPassword));

            PrincipalType principalType = typeService.findItemByCode(principalData.getCode(), PrincipalType.class);
            if (principalType != null) {
                principalType.setPassword(principalData.getPassword());
                typeService.save(principalType);
            }
        }
        return user;
    }

    @Override
    public void updatePassword(PrincipalType principal) {
        principal.setPassword(passwordEncoder.encode(principal.getPassword()));
        typeService.save(principal);
    }
}

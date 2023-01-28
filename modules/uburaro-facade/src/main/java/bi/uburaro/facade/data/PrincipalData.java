package bi.uburaro.facade.data;

import bi.uburaro.facade.data.groups.GroupData;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;

public abstract class PrincipalData extends ItemData implements UserDetails {
    protected Collection<? extends GrantedAuthority> authorities;
    private Set<GroupData> groups;

    private String username;
    private String password;

    public Set<GroupData> getGroups() {
        return groups;
    }

    public void setGroups(Set<GroupData> groups) {
        this.groups = groups;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
    }
}

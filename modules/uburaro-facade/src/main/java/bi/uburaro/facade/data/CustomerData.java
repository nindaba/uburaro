package bi.uburaro.facade.data;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.Collections;

public class CustomerData extends PrincipalData {
    private String firstName;
    private String lastName;
    private String phone;
    private String identity;
    private String Nationality;
    private String Age;
    private String gender;
    private Collection<AddressData> address;
    private Collection<CompanyData> companies;

    public Collection<CompanyData> getCompanies() {
        return companies;
    }

    public void setCompanies(Collection<CompanyData> companies) {
        this.companies = companies;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public String getNationality() {
        return Nationality;
    }

    public void setNationality(String nationality) {
        Nationality = nationality;
    }

    public String getAge() {
        return Age;
    }

    public void setAge(String age) {
        Age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Collection<AddressData> getAddress() {
        return address;
    }

    public void setAddress(Collection<AddressData> address) {
        this.address = address;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isActive();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return isActive();
    }
}

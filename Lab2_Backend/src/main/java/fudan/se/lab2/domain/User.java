package fudan.se.lab2.domain;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

/**
 * @author HZH
 */
@Entity
public class User implements UserDetails {

    private static final long serialVersionUID = -6140085056226164016L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long userid;

    @Column(unique = true)
    private String username;

    private String password;
    private String name;
    private int sex;
    private int area;
    private int identity;
    private String telephone;
    private ArrayList<Long> patientsid = new ArrayList<>();


    public User(){
        patientsid = new ArrayList<>();
    }
    public User(String username, String password, String name, int sex, int area, int identity, String telephone) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.sex = sex;
        this.area = area;
        this.identity = identity;
        this.telephone = telephone;
    }

    public ArrayList<Long> getPatientsid() {
        return patientsid;
    }

    public void setPatientsid(ArrayList<Long> patientsid) {
        this.patientsid = patientsid;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public void setUsername(String username) {
        this.username = username;
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public int getIdentity() {
        return identity;
    }

    public void setIdentity(int identity) {
        this.identity = identity;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public int getArea() {
        return area;
    }

    public void setArea(int area) {
        this.area = area;
    }

}

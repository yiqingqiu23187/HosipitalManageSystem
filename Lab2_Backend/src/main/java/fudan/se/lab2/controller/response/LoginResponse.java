package fudan.se.lab2.controller.response;

import fudan.se.lab2.domain.User;

public class LoginResponse {
    String token;
    User userDetail;

    public void setToken(String token) {
        this.token = token;
    }

    public void setUserDetail(User userDatail) {
        this.userDetail = userDatail;
    }

    public String getToken() {
        return token;
    }

    public User getUserDetail() {
        return userDetail;
    }
}

package spring.pro.springboot.jdbc.service.views;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class AuthView implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull
    @NotBlank
    private String login;
    @NotNull
    @NotBlank
    private String password;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "AuthView{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}

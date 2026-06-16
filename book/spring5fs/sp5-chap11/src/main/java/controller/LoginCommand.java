package controller;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import javax.annotation.Resource;
import javax.validation.constraints.Size;

@Resource
public class LoginCommand {

    @Email
    @NotEmpty
    private String email;
    @NotBlank
    @Size(min = 6, max = 10)
    private String password;
    private boolean rememberEmail;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isRememberEmail() {
        return rememberEmail;
    }

    public void setRememberEmail(boolean rememberEmail) {
        this.rememberEmail = rememberEmail;
    }
}

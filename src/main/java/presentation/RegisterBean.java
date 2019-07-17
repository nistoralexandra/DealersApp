package presentation;

import domain.dto.UserDTO;
import service.UserService;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import java.io.Serializable;

@ManagedBean(name = "registerBean")
@RequestScoped
public class RegisterBean implements Serializable {

    private String username;
    private String password;
    private String email;

    @ManagedProperty(value = "#{userService}")
    private UserService userService;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public void createUser() {
        UserDTO userDTO = new UserDTO(this.username, this.password, this.email);
        this.userService.createUser(userDTO);
    }
}

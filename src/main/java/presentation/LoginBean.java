package presentation;

import domain.dto.UserDTO;
import service.UserService;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.io.Serializable;
import java.util.Map;

@ManagedBean(name = "loginBean")
@SessionScoped
public class LoginBean implements Serializable {

    private String username;
    private String password;

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

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    private void showSuccessMessage() {
        String successMessage = "Welcome " + this.username + ", your password is " + this.password;
        System.out.println(successMessage);
    }

    public void login() {
        FacesContext context = FacesContext.getCurrentInstance();
        boolean valid = userService.validateUser(this.username, this.password);
        if (valid) {
            UserDTO loggedInUser = userService.getLoggedInUser();
            Map<String, Object> sessionMap = context.getExternalContext().getSessionMap();
            sessionMap.put("username", loggedInUser.getUsername());
            sessionMap.put("email", loggedInUser.getEmail());
            sessionMap.put("cars", loggedInUser.getCars());
            showSuccessMessage();
            try {
                context.getExternalContext().redirect("welcome.xhtml");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            FacesMessage invalidMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid Credentials", null);
            context.addMessage(null, invalidMessage);
        }
    }

    public void logout() {
        FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().invalidateSession();
        try {
            context.getExternalContext().redirect("login.xhtml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

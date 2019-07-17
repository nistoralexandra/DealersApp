package domain.dto;

import java.util.ArrayList;
import java.util.List;

public class UserDTO {

    private String username;
    private String password;
    private String email;
    private List<CarDTO> cars = new ArrayList<>();

    public UserDTO() {}

    public UserDTO(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public UserDTO(String username, String password, String email, List<CarDTO> cars) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.cars = cars;
    }

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

    public List<CarDTO> getCars() {
        return cars;
    }

    public void setCars(List<CarDTO> cars) {
        this.cars = cars;
    }
}

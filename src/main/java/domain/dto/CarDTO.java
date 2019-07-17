package domain.dto;

import java.util.ArrayList;
import java.util.List;

public class CarDTO {

    private String brand;
    private String model;
    private List<UserDTO> users = new ArrayList<>();

    public CarDTO() {}

    public CarDTO(String brand, String model) {
        this.brand = brand;
        this.model = model;
    }

    public CarDTO(String brand, String model, List<UserDTO> users) {
        this.brand = brand;
        this.model = model;
        this.users = users;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public List<UserDTO> getUsers() {
        return users;
    }

    public void setUsers(List<UserDTO> users) {
        this.users = users;
    }
}

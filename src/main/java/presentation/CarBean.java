package presentation;

import domain.dto.CarDTO;
import service.CarService;
import service.UserService;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.io.Serializable;

@ManagedBean(name = "carBean")
@SessionScoped
public class CarBean implements Serializable {

    private String brand;
    private String model;

    @ManagedProperty(value = "#{carService}")
    private CarService carService;

    @ManagedProperty(value = "#{userService}")
    private UserService userService;

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

    public void setCarService(CarService carService) {
        this.carService = carService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public void createCar() {
        CarDTO carDTO = new CarDTO(this.brand, this.model);
        this.carService.createCar(carDTO);
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("carTable.xhtml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

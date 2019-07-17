package presentation;

import domain.dto.CarDTO;
import domain.dto.UserDTO;
import service.CarService;
import service.UserService;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.List;

@ManagedBean
@SessionScoped
public class CarTableBean implements Serializable {

    private List<CarDTO> cars;

    @ManagedProperty(value = "#{carService}")
    private CarService carService;

    @ManagedProperty(value = "#{userService}")
    private UserService userService;

    @PostConstruct
    private void init() {
        UserDTO loggedInUser = userService.getLoggedInUser();
        this.cars = carService.findCarsByUser(loggedInUser.getUsername());
        if(this.cars.isEmpty()) {
            FacesContext context = FacesContext.getCurrentInstance();
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "No cars added yet", null);
            context.addMessage(null, message);
        }
    }

    public List<CarDTO> getCars() {
        return cars;
    }

    public void setCars(List<CarDTO> cars) {
        this.cars = cars;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public void setCarService(CarService carService) {
        this.carService = carService;
    }
}

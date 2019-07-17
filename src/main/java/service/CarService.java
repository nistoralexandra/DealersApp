package service;

import domain.dto.CarDTO;
import domain.model.CarEntity;
import helper.EntityToDtoConverter;
import persistence.CarRepository;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import java.util.List;
import java.util.Optional;

@ManagedBean(name = "carService")
@ApplicationScoped
public class CarService {

    @ManagedProperty(value = "#{carRepository}")
    private CarRepository carRepository;

    public void setCarRepository(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public void createCar(CarDTO carDTO)
    {
        carRepository.createCar(carDTO);
    }

    public List<CarDTO> findCarsByUser(String username) {
        Optional<List<CarEntity>> optionalCars = carRepository.findCarsByUser(username);
        return optionalCars.map(EntityToDtoConverter::convertCarList).orElse(null);
    }
}

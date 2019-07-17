package helper;

import domain.dto.CarDTO;
import domain.dto.UserDTO;
import domain.model.CarEntity;
import domain.model.UserEntity;

import java.util.List;
import java.util.stream.Collectors;

public class EntityToDtoConverter {

    public static List<UserDTO> convertUserList(List<UserEntity> users) {
        return users.stream()
                .map(user -> new UserDTO(user.getUsername(), user.getPassword(), user.getEmail()))
                .collect(Collectors.toList());
    }

    public static List<CarDTO> convertCarList(List<CarEntity> cars) {
        return cars.stream()
                .map(car -> new CarDTO(car.getBrand(), car.getModel()))
                .collect(Collectors.toList());
    }

    public static UserDTO convert(UserEntity user) {
        return new UserDTO(user.getUsername(), user.getPassword(), user.getEmail(), convertCarList(user.getCars()));
    }

    public static CarDTO convert(CarEntity car) {
        return new CarDTO(car.getBrand(), car.getModel(), convertUserList(car.getUsers()));
    }
}

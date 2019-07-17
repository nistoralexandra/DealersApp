package helper;

import domain.dto.CarDTO;
import domain.dto.UserDTO;
import domain.model.CarEntity;
import domain.model.UserEntity;

import java.util.List;
import java.util.stream.Collectors;

public class DtoToEntityConverter {

    public static List<UserEntity> convertUserList(List<UserDTO> users) {
        return users.stream()
                .map(userDTO -> new UserEntity(userDTO.getUsername(), userDTO.getPassword(), userDTO.getEmail()))
                .collect(Collectors.toList());
    }

    public static List<CarEntity> convertCarList(List<CarDTO> cars) {
        return cars.stream()
                .map(carDTO -> new CarEntity(carDTO.getBrand(), carDTO.getModel()))
                .collect(Collectors.toList());
    }

    public static UserEntity convert(UserDTO userDTO) {
        return new UserEntity(userDTO.getUsername(), userDTO.getPassword(), userDTO.getEmail(), convertCarList(userDTO.getCars()));
    }

    public static CarEntity convert(CarDTO carDTO) {
        return new CarEntity(carDTO.getBrand(), carDTO.getModel(), convertUserList(carDTO.getUsers()));
    }
}

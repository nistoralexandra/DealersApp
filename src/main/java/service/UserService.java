package service;

import domain.dto.UserDTO;
import persistence.UserRepository;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import java.util.Optional;

@ManagedBean(name = "userService")
@ApplicationScoped
public class UserService {

    private UserDTO loggedInUser;

    @ManagedProperty(value = "#{userRepository}")
    private UserRepository userRepository;

    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void createUser(UserDTO userDTO) {
        userRepository.createUser(userDTO);
    }

    public boolean validateUser(String username, String password) {
        Optional<UserDTO> optionalUser = userRepository.getUser(username, password);
        boolean valid = optionalUser.isPresent();
        if(valid) {
            loggedInUser = optionalUser.get();
        }
        return valid;
    }

    public UserDTO getLoggedInUser() {
        return loggedInUser;
    }
}

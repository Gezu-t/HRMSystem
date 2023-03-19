package et.hrms.controller;

import et.hrms.dal.dto.UserDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserController {

    ResponseEntity<UserDTO> createUser(UserDTO userDTO);
    ResponseEntity<UserDTO> getUserById(Long id);
    ResponseEntity<UserDTO> updateUser(Long id, UserDTO userDTO);
    ResponseEntity<Void> deleteUser(Long id);
    ResponseEntity<List<UserDTO>> getAllUsers();
}

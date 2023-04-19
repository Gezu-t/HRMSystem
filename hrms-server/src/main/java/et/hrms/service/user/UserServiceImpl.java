package et.hrms.service.user;


import et.hrms.dal.dto.UserDTO;
import et.hrms.dal.mapping.UserMapper;
import et.hrms.dal.model.User;
import et.hrms.dal.repository.UserRepository;
import et.hrms.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    @Autowired
    private final UserRepository userRepository;

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        // Check if the password is not null
        if (userDTO.getPassword() == null || userDTO.getPassword().isBlank()) {
            throw new IllegalArgumentException("Password cannot be null or empty");
        }

        // Encode the password
//        String encodedPassword = passwordEncoder.encode(userDTO.getPassword());
//        userDTO.setPassword(encodedPassword);

        User user = UserMapper.INSTANCE.userDTOtoUser(userDTO);
        User savedUser = userRepository.save(user);
        return UserMapper.INSTANCE.userToUserDTO(savedUser);
    }

    @Override
    public UserDTO getUserById(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        return optionalUser.map(UserMapper.INSTANCE::userToUserDTO).orElse(null);
    }

    @Override
    public UserDTO updateUser(UserDTO userDTO) {
        User user = UserMapper.INSTANCE.userDTOtoUser(userDTO);
        User updatedUser = userRepository.save(user);
        return UserMapper.INSTANCE.userToUserDTO(updatedUser);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(UserMapper.INSTANCE::userToUserDTO)
                .toList();
    }

}

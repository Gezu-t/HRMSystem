package et.hrms.service.impl;


import et.hrms.dal.dto.UserDTO;
import et.hrms.dal.mapping.UserMapper;
import et.hrms.dal.model.User;
import et.hrms.dal.repository.UserRepository;
import et.hrms.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDTO createUser(UserDTO userDTO) {
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
                .collect(Collectors.toList());
    }

}

package et.hrms.service;

import et.hrms.dal.dto.UserRequestDTO;
import et.hrms.dal.dto.UserResponseDTO;
import et.hrms.dal.mapper.UserMapper;
import et.hrms.dal.model.Role;
import et.hrms.dal.model.RoleName;
import et.hrms.dal.model.UserAccount;
import et.hrms.dal.repository.RoleRepository;
import et.hrms.dal.repository.UserRepository;
import et.hrms.exception.ResourceAlreadyExistsException;
import et.hrms.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    @Override
    public UserResponseDTO createUser(UserRequestDTO userRequest) {
        if (userRepository.existsByUsername(userRequest.getUsername())) {
            throw new ResourceAlreadyExistsException("Username is already taken");
        }

        if (userRepository.existsByEmail(userRequest.getEmail())) {
            throw new ResourceAlreadyExistsException("Email is already in use");
        }

        UserAccount userAccount = userMapper.toEntity(userRequest);
        userAccount.setPasswordHash(passwordEncoder.encode(userRequest.getPassword()));
        userAccount.setCreatedAt(Instant.now());
        userAccount.setUpdatedAt(Instant.now());
        userAccount.setActive(true);

        // Assign roles based on the request
        Set<Role> roles = userRequest.getRoles().stream()
                .map(roleName -> roleRepository.findByName(RoleName.valueOf(roleName))
                        .orElseThrow(() -> new ResourceNotFoundException("Role not found: " + roleName)))
                .collect(Collectors.toSet());

        userAccount.setRoles(roles);

        UserAccount savedUserAccount = userRepository.save(userAccount);
        return userMapper.toDto(savedUserAccount);
    }


    @Override
    @Transactional(readOnly = true)
    public UserResponseDTO getUserById(Long id) {
        UserAccount userAccount = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("UserAccount not found with id: " + id));
        return userMapper.toDto(userAccount);
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserResponseDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserResponseDTO updateUser(Long id, UserRequestDTO userRequest) {
        UserAccount userAccount = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("UserAccount not found with id: " + id));

        if (!userAccount.getEmail().equals(userRequest.getEmail()) && userRepository.existsByEmail(userRequest.getEmail())) {
            throw new ResourceAlreadyExistsException("Email is already in use");
        }

        userMapper.updateUserFromDto(userRequest, userAccount);
        userAccount.setUpdatedAt(Instant.now());

        if (userRequest.getPassword() != null && !userRequest.getPassword().isEmpty()) {
            userAccount.setPasswordHash(passwordEncoder.encode(userRequest.getPassword()));
        }

        UserAccount updatedUserAccount = userRepository.save(userAccount);
        return userMapper.toDto(updatedUserAccount);
    }

    @Override
    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new ResourceNotFoundException("UserAccount not found with id: " + id);
        }
        userRepository.deleteById(id);
    }
}
package et.hrms.controller;

import et.hrms.dal.dto.UserRequestDTO;
import et.hrms.dal.dto.UserResponseDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface UserController {

    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    ResponseEntity<UserResponseDTO> createUser(@Valid @RequestBody UserRequestDTO userRequest);

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_USER') or hasAuthority('ROLE_ADMIN')")
    ResponseEntity<UserResponseDTO> getUserById(@PathVariable Long id);

    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    ResponseEntity<List<UserResponseDTO>> getAllUsers();

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_USER') or hasAuthority('ROLE_ADMIN')")
    ResponseEntity<UserResponseDTO> updateUser(@PathVariable Long id,
                                               @Valid @RequestBody UserRequestDTO userRequest);

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    ResponseEntity<Void> deleteUser(@PathVariable Long id);
}

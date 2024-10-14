package et.hrms.dal.mapper;

import et.hrms.dal.dto.UserRequestDTO;
import et.hrms.dal.dto.UserResponseDTO;
import et.hrms.dal.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UserMapper {

    @Mapping(target = "password", ignore = true)
    @Mapping(target = "roles", ignore = true)
    User toEntity(UserRequestDTO userRequestDTO);

    @Mapping(target = "roles", expression = "java(user.getRoles().stream().map(role -> role.getName().name()).collect(java.util.stream.Collectors.toSet()))")
    UserResponseDTO toDto(User user);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "roles", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    void updateUserFromDto(UserRequestDTO dto, @MappingTarget User user);
}
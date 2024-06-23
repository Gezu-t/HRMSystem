package et.hrms.dal.mapper;

import et.hrms.dal.dto.UserDTO;
import et.hrms.dal.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(target = "password", ignore = true)
    UserDTO userToUserDTO(User user);

    @Mapping(target = "password", ignore = true)
    User userDTOtoUser(UserDTO userDTO);
}
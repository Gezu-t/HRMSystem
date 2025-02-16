package com.hrms.employee.core.dal.mapping.organization;

import com.hrms.employee.core.dal.dto.organization.OwnersDTO;
import com.hrms.employee.core.dal.model.organization.Owners;
import org.mapstruct.Mapper;

import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OwnersMapper {

    OwnersMapper INSTANCE = Mappers.getMapper(OwnersMapper.class);

    OwnersDTO toOwnersDTO(Owners owners);

    Owners toOwners(OwnersDTO ownersDTO);

    void updateOwners(OwnersDTO ownersDTO, @MappingTarget Owners owners);

    List<OwnersDTO> toOwnersDTOList(List<Owners> owners);
}

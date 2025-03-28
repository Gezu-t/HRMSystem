package com.hrmsystem.employeeservice.core.dal.mapping.organization;

import dal.dto.organization.OwnersDTO;
import dal.model.organization.Owners;
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

    List<OwnersDTO> toOwnersDTOList(List<Owners> owners);  // List mapping helper
}

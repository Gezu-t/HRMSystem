package et.hrms.dal.mapping;

import et.hrms.dal.dto.AddressDTO;
import et.hrms.dal.model.Address;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;


@Mapper(componentModel = "spring")
public interface AddressMapper {


    AddressMapper INSTANCE = Mappers.getMapper(AddressMapper.class);


    Address toAddress(AddressDTO addressDTO);

    AddressDTO toAddressDTO(Address address);


    List<Address> toAddressList(List<AddressDTO> addressDTOS);


}

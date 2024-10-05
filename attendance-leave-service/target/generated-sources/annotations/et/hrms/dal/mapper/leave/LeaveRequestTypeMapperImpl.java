package et.hrms.dal.mapper.leave;

import et.hrms.dal.dto.leave.LeaveRequestTypeDTO;
import et.hrms.dal.model.leave.LeaveRequestType;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-10-05T13:21:42+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.9 (Oracle Corporation)"
)
public class LeaveRequestTypeMapperImpl implements LeaveRequestTypeMapper {

    @Override
    public LeaveRequestTypeDTO leaveRequestTypeToDTO(LeaveRequestType leaveRequestType) {
        if ( leaveRequestType == null ) {
            return null;
        }

        LeaveRequestTypeDTO leaveRequestTypeDTO = new LeaveRequestTypeDTO();

        return leaveRequestTypeDTO;
    }
}

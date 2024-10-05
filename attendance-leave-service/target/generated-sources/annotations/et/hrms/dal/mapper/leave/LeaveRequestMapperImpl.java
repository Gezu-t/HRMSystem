package et.hrms.dal.mapper.leave;

import et.hrms.dal.dto.leave.LeaveRequestDTO;
import et.hrms.dal.model.leave.LeaveRequest;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-10-05T13:21:42+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.9 (Oracle Corporation)"
)
public class LeaveRequestMapperImpl implements LeaveRequestMapper {

    @Override
    public LeaveRequestDTO leaveRequestToDTO(LeaveRequest leaveRequest) {
        if ( leaveRequest == null ) {
            return null;
        }

        LeaveRequestDTO leaveRequestDTO = new LeaveRequestDTO();

        leaveRequestDTO.setStartDate( leaveRequest.getStartDate() );
        leaveRequestDTO.setEndDate( leaveRequest.getEndDate() );
        leaveRequestDTO.setLeaveRequestEvents( leaveRequest.getLeaveRequestEvents() );
        leaveRequestDTO.setLeaveRequestStates( leaveRequest.getLeaveRequestStates() );
        leaveRequestDTO.setEmployeeId( leaveRequest.getEmployeeId() );
        leaveRequestDTO.setTaskId( leaveRequest.getTaskId() );
        leaveRequestDTO.setProjectId( leaveRequest.getProjectId() );
        leaveRequestDTO.setLeaveStatus( leaveRequest.getLeaveStatus() );
        leaveRequestDTO.setStatus( leaveRequest.getStatus() );
        leaveRequestDTO.setLeaveRequestReason( leaveRequest.getLeaveRequestReason() );

        return leaveRequestDTO;
    }
}

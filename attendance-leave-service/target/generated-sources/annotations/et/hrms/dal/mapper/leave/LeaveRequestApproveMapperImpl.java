package et.hrms.dal.mapper.leave;

import et.hrms.dal.dto.leave.LeaveRequestApproveDTO;
import et.hrms.dal.model.leave.LeaveRequestApprove;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-10-05T13:21:42+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.9 (Oracle Corporation)"
)
@Component
public class LeaveRequestApproveMapperImpl implements LeaveRequestApproveMapper {

    @Override
    public LeaveRequestApproveDTO toDTO(LeaveRequestApprove leaveRequestApprove) {
        if ( leaveRequestApprove == null ) {
            return null;
        }

        LeaveRequestApproveDTO leaveRequestApproveDTO = new LeaveRequestApproveDTO();

        leaveRequestApproveDTO.setRequestApproveDate( leaveRequestApprove.getRequestApproveDate() );
        leaveRequestApproveDTO.setLeaveRequestEvents( leaveRequestApprove.getLeaveRequestEvents() );
        if ( leaveRequestApprove.getStatus() != null ) {
            leaveRequestApproveDTO.setStatus( leaveRequestApprove.getStatus() );
        }
        leaveRequestApproveDTO.setApprovalUserComment( leaveRequestApprove.getApprovalUserComment() );
        leaveRequestApproveDTO.setDescription( leaveRequestApprove.getDescription() );

        return leaveRequestApproveDTO;
    }

    @Override
    public LeaveRequestApprove toEntity(LeaveRequestApproveDTO leaveRequestApproveDTO) {
        if ( leaveRequestApproveDTO == null ) {
            return null;
        }

        LeaveRequestApprove leaveRequestApprove = new LeaveRequestApprove();

        leaveRequestApprove.setRequestApproveDate( leaveRequestApproveDTO.getRequestApproveDate() );
        leaveRequestApprove.setLeaveRequestEvents( leaveRequestApproveDTO.getLeaveRequestEvents() );
        leaveRequestApprove.setStatus( leaveRequestApproveDTO.isStatus() );
        leaveRequestApprove.setApprovalUserComment( leaveRequestApproveDTO.getApprovalUserComment() );
        leaveRequestApprove.setDescription( leaveRequestApproveDTO.getDescription() );

        return leaveRequestApprove;
    }
}

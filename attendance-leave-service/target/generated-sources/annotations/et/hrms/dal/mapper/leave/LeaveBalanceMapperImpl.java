package et.hrms.dal.mapper.leave;

import et.hrms.dal.dto.leave.LeaveBalanceDTO;
import et.hrms.dal.model.leave.LeaveBalance;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-10-05T13:21:42+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.9 (Oracle Corporation)"
)
@Component
public class LeaveBalanceMapperImpl implements LeaveBalanceMapper {

    @Override
    public LeaveBalance toEntity(LeaveBalanceDTO dto) {
        if ( dto == null ) {
            return null;
        }

        LeaveBalance leaveBalance = new LeaveBalance();

        leaveBalance.setAnnualLeaveQuota( dto.getAnnualLeaveQuota() );
        leaveBalance.setEmployeeId( dto.getEmployeeId() );

        return leaveBalance;
    }

    @Override
    public LeaveBalanceDTO toDTO(LeaveBalance entity) {
        if ( entity == null ) {
            return null;
        }

        Integer annualLeaveQuota = null;
        Long employeeId = null;

        annualLeaveQuota = entity.getAnnualLeaveQuota();
        employeeId = entity.getEmployeeId();

        Long leaveBalanceId = null;
        String remainingDate = null;
        Integer numberOfLeaveTaken = null;
        boolean status = false;

        LeaveBalanceDTO leaveBalanceDTO = new LeaveBalanceDTO( leaveBalanceId, remainingDate, numberOfLeaveTaken, annualLeaveQuota, employeeId, status );

        return leaveBalanceDTO;
    }

    @Override
    public void updateEntityFromDto(LeaveBalanceDTO dto, LeaveBalance entity) {
        if ( dto == null ) {
            return;
        }

        entity.setAnnualLeaveQuota( dto.getAnnualLeaveQuota() );
        entity.setEmployeeId( dto.getEmployeeId() );
    }
}

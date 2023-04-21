package et.hrms.service.leave;

import et.hrms.dal.dto.leave.LeaveRequestTypeDTO;
import et.hrms.dal.mapping.leave.LeaveRequestTypeMapper;
import et.hrms.dal.model.leave.LeaveRequestType;
import et.hrms.dal.repository.leave.LeaveRequestTypeRepository;
import et.hrms.exceptions.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@Service
public class LeaveRequestTypeServiceImpl implements LeaveRequestTypeService {

  private final LeaveRequestTypeRepository leaveRequestTypeRepository;
  private final LeaveRequestTypeMapper leaveRequestTypeMapper;

  /**
   * @param leaveRequestTypeDTO
   */
  @Override
  public void createLeaveRequestType(LeaveRequestTypeDTO leaveRequestTypeDTO) {

    leaveRequestTypeRepository.save(leaveRequestTypeMapper.toEntity(leaveRequestTypeDTO));

  }

  /**
   * @param leaveRequestTypeDTO
   * @return
   */
  @Override
  public LeaveRequestTypeDTO updateLeaveRequestType(LeaveRequestTypeDTO leaveRequestTypeDTO) {
    LeaveRequestType leaveRequestType = leaveRequestTypeRepository.findById(leaveRequestTypeDTO.getLeaveTypeId())
            .orElseThrow(() -> new EntityNotFoundException("Leave request type is not found by this ID: " + leaveRequestTypeDTO.getLeaveTypeId()));
    if (!Objects.equals(leaveRequestType.getTypeName(), leaveRequestTypeDTO.getLeaveRequestTypeName())) {
      leaveRequestType.setTypeName(leaveRequestTypeDTO.getLeaveRequestTypeName());
    }
    LeaveRequestType updatedLeaveRequestType = leaveRequestTypeRepository.save(leaveRequestType);
    return leaveRequestTypeMapper.toDTO(updatedLeaveRequestType);
  }

  /**
   * @param leaveRequestTypeId
   * @return
   */
  @Override
  public LeaveRequestTypeDTO getLeaveRequestTypeId(Long leaveRequestTypeId) {
    LeaveRequestType leaveRequestType = leaveRequestTypeRepository.findById(leaveRequestTypeId)
            .orElseThrow(() -> new EntityNotFoundException("Leave request type is not found by this ID " + leaveRequestTypeId));

    return leaveRequestTypeMapper.toDTO(leaveRequestType);
  }

  /**
   * @return
   */
  @Override
  public List<LeaveRequestTypeDTO> getAllLeaveRequestType() {
    List<LeaveRequestType> leaveRequestTypes = leaveRequestTypeRepository.findAll();
    return leaveRequestTypes.stream().map(leaveRequestTypeMapper::toDTO)
            .toList();
  }
}

package com.gtltek.service.impl;

import com.gtltek.messaging.dto.leave.LeaveRequestTypeDTO;
import com.gtltek.messaging.mapper.LeaveRequestTypeMapper;
import com.gtltek.messaging.repository.LeaveRequestTypeRepository;
import com.gtltek.service.LeaveRequestTypeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class LeaveRequestTypeServiceImpl implements LeaveRequestTypeService {

    private final LeaveRequestTypeRepository leaveRequestTypeRepository;

    public LeaveRequestTypeServiceImpl(LeaveRequestTypeRepository leaveRequestTypeRepository) {
        this.leaveRequestTypeRepository = leaveRequestTypeRepository;
    }

    @Override
    public LeaveRequestTypeDTO getLeaveRequestTypeById(Long typeId) {
        return leaveRequestTypeRepository.findById(typeId)
                .map(LeaveRequestTypeMapper.INSTANCE::leaveRequestTypeToDTO)
                .orElseThrow(() -> new RuntimeException("Leave request type not found with id: " + typeId));
    }

    @Override
    public List<LeaveRequestTypeDTO> getAllLeaveRequestTypeDTOs() {
        return leaveRequestTypeRepository.findAll().stream()
                .map(LeaveRequestTypeMapper.INSTANCE::leaveRequestTypeToDTO)
                .collect(Collectors.toList());
    }
}

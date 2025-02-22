package com.gtltek.controller.impl;

import com.gtltek.controller.LeaveRequestController;
import et.hrms.messaging.dto.employee.EmployeeLeaveDTO;
import com.gtltek.messaging.dto.leave.CreateLeaveRequestDTO;
import com.gtltek.messaging.dto.leave.LeaveRequestDTO;
import com.gtltek.service.LeaveRequestService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/leave-requests")
public class LeaveRequestControllerImpl implements LeaveRequestController {

    private final LeaveRequestService leaveRequestService;

    public LeaveRequestControllerImpl(LeaveRequestService leaveRequestService) {
        this.leaveRequestService = leaveRequestService;
    }

    @Override
    @PostMapping("/leave-requests")
    public ResponseEntity<LeaveRequestDTO> createLeaveRequest(@RequestBody CreateLeaveRequestDTO createDto) {
        LeaveRequestDTO leaveRequestDTO = leaveRequestService.createLeaveRequest(createDto);
        return ResponseEntity.ok(leaveRequestDTO);
    }

    /**
     * Get employee details based on employee name.
     * @param employeeId The employee name to search for.
     * @return ResponseEntity containing EmployeeLeaveDTO or not found status.
     */
    @GetMapping("/employee/{employeeId}")
    @Override
    public ResponseEntity<EmployeeLeaveDTO> getEmployeeDetails(@PathVariable Long employeeId) {
        try {
            EmployeeLeaveDTO employee = leaveRequestService.handleLeaveRequestForEmployee(employeeId)
                    .orElseThrow(() -> new RuntimeException("No employeeprofile found with the ID: " + employeeId));
            return ResponseEntity.ok(employee);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

//    /**
//     * Get employeeprofile details asynchronously based on employeeprofile name.
//     * @param employeeName The employeeprofile's name to search for.
//     * @return A CompletableFuture wrapped in ResponseEntity containing EmployeeLeaveDTO.
//     */
//    @GetMapping("/employeeprofile/{employeeName}")
//    public CompletableFuture<ResponseEntity<EmployeeLeaveDTO>> getEmployeeDetails(@PathVariable String employeeName) {
//        return leaveRequestService.handleLeaveRequestForEmployeeAsync(employeeName)
//                .thenApply(employeeprofile -> ResponseEntity.ok(employeeprofile))
//                .exceptionally(ex -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
//    }

}

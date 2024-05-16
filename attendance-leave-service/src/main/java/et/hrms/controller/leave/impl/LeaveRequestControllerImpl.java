package et.hrms.controller.leave.impl;

import et.hrms.controller.leave.LeaveRequestController;
import et.hrms.dal.dto.employee.EmployeeDTO;
import et.hrms.dal.dto.leave.CreateLeaveRequestDTO;
import et.hrms.dal.dto.leave.LeaveRequestDTO;
import et.hrms.service.leave.LeaveRequestService;
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
     * @param employeeName The employee's name to search for.
     * @return ResponseEntity containing EmployeeDTO or not found status.
     */
    @Override
    @GetMapping("/employee/{employeeName}")
    public ResponseEntity<EmployeeDTO> getEmployeeDetails(@PathVariable String employeeName) {
        try {
            EmployeeDTO employee = leaveRequestService.handleLeaveRequestForEmployee(employeeName)
                    .orElseThrow(() -> new RuntimeException("No employee found with the name: " + employeeName));
            return ResponseEntity.ok(employee);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

//    /**
//     * Get employee details asynchronously based on employee name.
//     * @param employeeName The employee's name to search for.
//     * @return A CompletableFuture wrapped in ResponseEntity containing EmployeeDTO.
//     */
//    @GetMapping("/employee/{employeeName}")
//    public CompletableFuture<ResponseEntity<EmployeeDTO>> getEmployeeDetails(@PathVariable String employeeName) {
//        return leaveRequestService.handleLeaveRequestForEmployeeAsync(employeeName)
//                .thenApply(employee -> ResponseEntity.ok(employee))
//                .exceptionally(ex -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
//    }

}

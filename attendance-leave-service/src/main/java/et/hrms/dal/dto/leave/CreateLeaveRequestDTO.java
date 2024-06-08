package et.hrms.dal.dto.leave;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

public class CreateLeaveRequestDTO  implements Serializable  {


    @Serial
    private static final long serialVersionUID = 3062671967791603386L;


    private LocalDate startDate;
    private LocalDate endDate;
    private Long leaveTypeId;
    private String leaveRequestReason;
    private Long employeeId;
    private Long taskId;
    private Long projectId;


    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Long getLeaveTypeId() {
        return leaveTypeId;
    }

    public void setLeaveTypeId(Long leaveTypeId) {
        this.leaveTypeId = leaveTypeId;
    }

    public String getLeaveRequestReason() {
        return leaveRequestReason;
    }

    public void setLeaveRequestReason(String leaveRequestReason) {
        this.leaveRequestReason = leaveRequestReason;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }
}


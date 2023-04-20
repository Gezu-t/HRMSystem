package et.hrms.dal.model.employee;


import et.hrms.dal.model.*;
import et.hrms.dal.model.attendance.Attendance;
import et.hrms.dal.model.education.Education;
import et.hrms.dal.model.leave.LeaveBalance;
import et.hrms.dal.model.leave.LeaveRequest;
import et.hrms.dal.model.leave.LeaveRequestApprove;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.*;


@Setter@Getter@NoArgsConstructor@AllArgsConstructor
@Entity
@Table(name = "employee")
public class Employee {

    @Id
    @SequenceGenerator(name = "employee_id_gen", sequenceName = "employee_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employee_id_gen")
    private Long id;
    private String employeeNumber;
    private String firstName;
    private String lastName;
    @Enumerated(EnumType.STRING)
    private GenderType genderStatus;
    @Enumerated(EnumType.STRING)
    private MaritalStatus maritalStatus;
    private LocalDate dateOfBirth;
    private LocalDate dateOfJoining;
    @Enumerated(EnumType.STRING)
    private EmployeeType employeeType;
    private LocalDate dateOfLeaving;
    private LocalDate dateOfResignation;
    private Boolean employeeStatus;
    private LocalDate employeeStatusDate;
    private Byte employeeProfileImage;

    @OneToOne(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true)
    private EmployeeAppearance employeeAppearance;
    @OneToOne(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true)
    private Family family;

    @OneToMany(mappedBy = "employee", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<EmployeePositionManagement> employeePositionManagements = new LinkedHashSet<>();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<EmployeeEvaluation> employeeEvaluation;

    @OneToMany(mappedBy = "employee", fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    private List<EmployeeAddress> employeeAddresses = new ArrayList<>();

    @OneToMany(mappedBy = "employee", fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    private List<EmployeeDetail> employeeDetails = new ArrayList<>();

    @OneToMany(mappedBy = "employee", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<EmployeePromotion> employeePromotion;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @OneToMany(mappedBy = "employee")
    private List<Attendance> attendance;
    @OneToMany(mappedBy = "employee")
    private List<Education> educations;
    @OneToMany(mappedBy = "employee")
    private List<LeaveBalance> leaveBalances;
    @OneToMany(mappedBy = "employee")
    private List<LeaveRequest> leaveRequests;
    @OneToMany(mappedBy = "employee")
    private List<LeaveRequestApprove> leaveRequestApproves;


}

package et.hrms.dal.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;


@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "employee", schema = "public")
public class Employee {

    @Id
    @SequenceGenerator(name = "employee_id_seq", sequenceName = "employee_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employee_id_seq")
    private Long id;
    private String firstName;
    private String middleName;
    private String lastName;

    private String dateOfBirth;
    private String dateOfJoining;
    private String dateOfLeaving;
    private String dateOfResignation;
    private String dateOfLastPromotion;
    private String dateOfLastIncrement;
    private String dateOfLastDecrement;
    private String dateOfLastSalaryChange;
    private String dateOfLastSalaryIncrement;
    private String dateOfLastSalaryDecrement;
    private String dateOfLastSalaryChangeReason;


    private String employeeStatus;
    private String employeeStatusDate;

    @ManyToMany (mappedBy = "employees")
    private List<Address> addresses;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    private Appearance appearance;
   @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "education_id")
    private Education education;
   @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "family_id")
    private Family family;
}

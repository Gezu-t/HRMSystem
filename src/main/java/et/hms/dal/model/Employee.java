package et.hms.dal.model;

import javax.persistence.CascadeType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;

public class Employee {

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
    @OneToMany (mappedBy = "employee", cascade = CascadeType.ALL)
    private Appearance appearance;
    @OneToMany (mappedBy = "employee" , cascade = CascadeType.ALL)
    private Education education;
    @OneToMany (mappedBy = "employee" , cascade = CascadeType.ALL)
    private Family family;
}

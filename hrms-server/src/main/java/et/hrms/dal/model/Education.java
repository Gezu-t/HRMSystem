package et.hrms.dal.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;


@Setter
@Getter
@ToString
@Entity
@Table(name = "education")
public class Education {

    @Id
    @SequenceGenerator(name = "education_id_gen",
            sequenceName = "education_id_sequence",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
    generator = "education_id_gen")
    private Long id;
    private String educationCompletionDate;
    private String educationLevel;
    private String institution;
    private String degree;
    private String educationMajor;
    private String educationMinor;
    private String educationGrade;
    private String educationGradePoint;
    private String educationGradePointAverage;
    private String educationGradePointAverageOutOf;
    private String educationGradePointAverageOutOfScale;
    private String academicYear;
    private String educationType;
    private String educationStatus;
    private LocalDate educationStartDate;
    private LocalDate educationEndDate;
    private String award;
    private LocalDate awardDate;
    private String awardDescription;


    // auditing
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;



    @OneToMany(mappedBy = "education", fetch = FetchType.LAZY)
    private Set<EmployeeEducationManagement> employeeEducationManagementSet;

}

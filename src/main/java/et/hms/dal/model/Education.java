package et.hms.dal.model;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Setter
@Getter
@ToString
@Entity
@Table(name = "education", schema = "public")
public class Education {

    @Id
    private Long id;
    private String educationCompletionDate;
    private String educationLevel;
    private String educationInstitution;
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
    private String educationStatusDate;
    private String educationStatusDescription;
    private String award;
    private String awardDate;
    private String awardDescription;

}

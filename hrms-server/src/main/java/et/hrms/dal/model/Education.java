package et.hrms.dal.model;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Year;


@AllArgsConstructor
@Setter
@Getter
@ToString
@Entity
@Table(name = "education")
@NoArgsConstructor
public class Education {

    @Id
    @SequenceGenerator(name = "education_id_gen",
            sequenceName = "education_id_sequence",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
    generator = "education_id_gen")
    private Long id;
    private LocalDate educationCompletionDate;
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
    private Year academicYear;
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



}

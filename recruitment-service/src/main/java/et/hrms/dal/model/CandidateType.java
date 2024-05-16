package et.hrms.dal.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter@Setter@NoArgsConstructor@AllArgsConstructor
@Entity
@Table(name = "candidate_type")
public class CandidateType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String candidateTypeName;
    @OneToMany(mappedBy = "candidateType")
    private List<Candidate> candidates;


}

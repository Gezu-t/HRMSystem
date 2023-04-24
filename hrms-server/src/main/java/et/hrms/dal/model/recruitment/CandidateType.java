package et.hrms.dal.model.recruitment;

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
    @GeneratedValue(generator = "candidate_type_id_gen", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "candidate_type_id_gen", sequenceName = "candidate_type_id_seq", allocationSize = 1)
    private Long id;
    private String candidateTypeName;
    @OneToMany(mappedBy = "candidateType")
    private List<Candidate> candidates;


}

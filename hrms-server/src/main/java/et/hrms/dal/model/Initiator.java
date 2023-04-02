package et.hrms.dal.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Initiator {

    @Id
    @SequenceGenerator(name = "initiator_id_gen",
            sequenceName = "initiator_id_seq",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "initiator_id_gen")
    private Long id;

    private String name;
    private String position;
    private String department;

    @OneToMany(mappedBy = "initiator")
    private List<Recruitment> recruitments;

    // Getters and setters
}

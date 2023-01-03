package et.hrms.dal.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "organization")
public class Organization {

    @Id
    @SequenceGenerator(name = "organization_id_sequence",
            sequenceName = "organization_id_sequence",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "organization_id_sequence")
    private Long id;

    private String organizationName;
    private String establishmentDate;

    private String ownerName;

    @OneToMany(mappedBy = "organization", fetch = FetchType.EAGER)
    private Set<OrganizationAddressManagement> organizationAddressManagements;

}

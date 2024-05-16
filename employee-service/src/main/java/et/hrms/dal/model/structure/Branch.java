package et.hrms.dal.model.structure;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "branch", schema = "public")
public class Branch {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String branchCode;

    private String branchName;

    @ManyToOne
    @JoinColumn(name = "organization_id")
    private Organization organization;

    @OneToMany(mappedBy = "branch", fetch = FetchType.LAZY)
    private Set<Department> departments = new LinkedHashSet<>();


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "organization_address_id")
    private OrganizationAddress organizationAddress;
}

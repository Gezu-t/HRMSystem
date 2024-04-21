package et.hrms.dal.model.structure;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "organization", schema = "public")
public class Organization {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String organizationName;


    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate establishmentDate;

    private String ownerName;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "organizationAddress_id")
    private OrganizationAddress organizationAddress;

    @OneToMany(mappedBy = "organization", cascade = CascadeType.ALL)
    private Set<Branch> branches = new LinkedHashSet<>();

    @OneToMany(mappedBy = "organization", cascade = CascadeType.ALL)
    private Set<Department> departments = new LinkedHashSet<>();

}

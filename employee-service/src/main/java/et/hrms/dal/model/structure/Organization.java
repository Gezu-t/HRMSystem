package et.hrms.dal.model.structure;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;


@Entity
@Table(name = "organization", schema = "public")
public class Organization implements Serializable {

    @Serial
    private static final long serialVersionUID = -3433023042841112893L;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public LocalDate getEstablishmentDate() {
        return establishmentDate;
    }

    public void setEstablishmentDate(LocalDate establishmentDate) {
        this.establishmentDate = establishmentDate;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public OrganizationAddress getOrganizationAddress() {
        return organizationAddress;
    }

    public void setOrganizationAddress(OrganizationAddress organizationAddress) {
        this.organizationAddress = organizationAddress;
    }

    public Set<Branch> getBranches() {
        return branches;
    }

    public void setBranches(Set<Branch> branches) {
        this.branches = branches;
    }

    public Set<Department> getDepartments() {
        return departments;
    }

    public void setDepartments(Set<Department> departments) {
        this.departments = departments;
    }
}

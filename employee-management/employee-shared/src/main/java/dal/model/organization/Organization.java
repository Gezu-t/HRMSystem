package dal.model.organization;

import dal.model.department.DepartmentUnderOrganization;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "organization", schema = "public")
public class Organization implements Serializable {

    @Serial
    private static final long serialVersionUID = -5895464065664543655L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false, length = 100)
    private String organizationName;

    @NotBlank
    @Column(nullable = false, length = 100)
    private String organizationCode;

    @Column(nullable = false)
    private LocalDate establishmentDate;

    @NotBlank
    @Column(nullable = false, length = 100)
    private String ownerName;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "organization_address_id", referencedColumnName = "id")
    private OrganizationAddress organizationAddress;

    @OneToMany(mappedBy = "organization", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<DepartmentUnderOrganization> departments;

    @PrePersist
    private void prePersist() {
        if (organizationAddress != null) {
            organizationAddress.setOrganization(this);
        }
    }

    @PreUpdate
    private void preUpdate() {
        if (organizationAddress != null) {
            organizationAddress.setOrganization(this);
        }
    }
}

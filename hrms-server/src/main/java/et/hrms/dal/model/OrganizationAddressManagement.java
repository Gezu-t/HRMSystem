package et.hrms.dal.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;


@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "address_organization_management")
public class OrganizationAddressManagement {

    @Id
    @SequenceGenerator(name = "address_org_management_gen", sequenceName = "address_org_management_seq", allocationSize = 1)
    @GeneratedValue(generator = "address_org_management_gen", strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;

    @ManyToOne
    @JoinColumn(name = "organization_id")
    private Organization organization;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

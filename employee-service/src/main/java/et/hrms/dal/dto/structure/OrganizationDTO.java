package et.hrms.dal.dto.structure;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.UniqueElements;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;


public class OrganizationDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = -8477895099029698859L;

    private Long organizationId;
    @NotNull
    @UniqueElements(message = "duplicate organization name exist")
    private String organizationName;
    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate establishmentDate;
    @NotNull
    private String ownerName;

    @NotNull
    private OrganizationAddressDTO organizationAddressDTO;

    public Long getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Long organizationId) {
        this.organizationId = organizationId;
    }

    public @NotNull @UniqueElements(message = "duplicate organization name exist") String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(@NotNull @UniqueElements(message = "duplicate organization name exist") String organizationName) {
        this.organizationName = organizationName;
    }

    public @NotNull LocalDate getEstablishmentDate() {
        return establishmentDate;
    }

    public void setEstablishmentDate(@NotNull LocalDate establishmentDate) {
        this.establishmentDate = establishmentDate;
    }

    public @NotNull String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(@NotNull String ownerName) {
        this.ownerName = ownerName;
    }

    public @NotNull OrganizationAddressDTO getOrganizationAddressDTO() {
        return organizationAddressDTO;
    }

    public void setOrganizationAddressDTO(@NotNull OrganizationAddressDTO organizationAddressDTO) {
        this.organizationAddressDTO = organizationAddressDTO;
    }
}

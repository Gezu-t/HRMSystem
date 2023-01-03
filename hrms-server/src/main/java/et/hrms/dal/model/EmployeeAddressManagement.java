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
@Table(name = "employee_address_management")
public class EmployeeAddressManagement {

    @Id
    @SequenceGenerator(name = "employee_address_manage_gen", sequenceName = "employee_address_manage_seq")
    @GeneratedValue(generator = "employee_address_manage_gen", strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private Boolean creationStatus;
    private Boolean updateStatus;




}

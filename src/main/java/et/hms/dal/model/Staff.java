package et.hms.dal.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "staff", schema = "public")
public class Staff  implements Serializable {


    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "staff_ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "staff_seq_gen")
    @SequenceGenerator(name = "staff_seq_gen", sequenceName = "staff_seq", allocationSize = 1)
    private Long id;

    @Column(name = "email_address")
    private String emailAddress;


    private String password;


    private String userName;

    private String address;

}

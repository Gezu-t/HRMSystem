package et.hms.dal.model;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

public class User implements Serializable {


    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "user_ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq_gen")
    @SequenceGenerator(name = "user_seq_gen", sequenceName = "user_seq", allocationSize = 1)
    private Long userId;

    @NotBlank
    @Size(min = 6, max = 45)
    private String userName;

    @NotBlank(message = "First name code is mandatory")
    @Size(min = 2, max = 45, message = "the length of first name is out of range")
    private String firstName;

    @NotBlank(message = "Last name code is mandatory")
    @Size(min = 1, max = 45, message = "the length of first name is out of range")
    private String lastName;

    @NotBlank(message = "Email code is mandatory")
    @Size(min = 10, max = 50, message = "the length of first name is out of range")
    @Email(message = "email isn't valid")
    private String email;

    @NotBlank(message = "Password code is mandatory")
    @Size(min = 6, max = 150, message = "the length of first name is out of range")
    private String password;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "role", nullable = false)
    private Role role;


}
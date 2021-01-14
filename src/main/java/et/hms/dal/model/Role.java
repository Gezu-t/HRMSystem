package et.hms.dal.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
@Table(name = "role", schema = "public")
public class Role implements Serializable {


    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @Column(name = "role_ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "role_seq_gen")
    @SequenceGenerator(name = "role_seq_gen", sequenceName = "role_seq", allocationSize = 1)
    private Long id;

    @NotBlank(message = "role name is mandatory")
    @Size(min = 2, max = 45, message = "the length of role name is out of range")
    @Column(name = "role_name", nullable = false)
    private String roleName;
    
    
    
    @OneToMany(mappedBy = "role")
    private List<User> users;
}

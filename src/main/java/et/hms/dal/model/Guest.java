package et.hms.dal.model;


import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * information registration for new guest to room
 */
@Data
@Entity
@Table(name = "guest", schema = "public")
public class Guest implements Serializable {

    @Id
    @Column(name = "IdGuest")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "guest_seq_gen")
    @SequenceGenerator(name = "guest_seq_gen", sequenceName = "guest_seq", allocationSize = 1)
    private Long id;




}

package et.hms.dal.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;


@Data
@Entity
@Table(name = "notification", schema = "public")
public class Notification implements Serializable {


    /**
     *
     */
    private static final long serialVersionUID = 1L;


    @Id
    @Column(name = "IdNotification")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "notification_seq_gen")
    @SequenceGenerator(name = "notification_seq_gen", sequenceName = "notification_seq", allocationSize = 1)
    private Long id;




}

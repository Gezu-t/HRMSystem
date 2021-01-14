package et.hms.dal.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;


@Data
@Entity
@Table(name = "notice", schema = "public")
public class Notice implements Serializable {


    /**
     *
     */
    private static final long serialVersionUID = 1L;


    @Id
    @Column(name = "Idnotice")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "notice_seq_gen")
    @SequenceGenerator(name = "notice_seq_gen", sequenceName = "notice_seq", allocationSize = 1)
    private Long id;



}

package et.hms.dal.model;

import java.io.Serializable;

import javax.persistence.*;

import lombok.Data;

@Data
@Entity
@Table(name = "request", schema = "public")
public class Request implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;


	@Id
	@Column(name = "IdRequest")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "request_seq_gen")
	@SequenceGenerator(name = "request_seq_gen", sequenceName = "request_seq", allocationSize = 1)
	private Long id;


}

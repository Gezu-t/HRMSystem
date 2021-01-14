package et.hms.dal.model;

import java.io.Serializable;

import javax.persistence.*;

import lombok.Data;

@Data
@Entity
@Table(name = "room", schema = "public")
public class Room implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "IdRoom")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "room_seq_gen")
	@SequenceGenerator(name = "room_seq_gen", sequenceName = "room_seq", allocationSize = 1)
	private Long id;
	
	private String roomNumber;
	private Long occupied;
	private Long capacity;
	private Double rental;
	
	

}

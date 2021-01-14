package et.hms.dal.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "request", schema = "public")
public class Request implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	

}

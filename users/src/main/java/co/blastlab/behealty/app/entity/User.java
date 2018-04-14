package co.blastlab.behealty.app.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class User {

	@Id
	@GeneratedValue
	private long id;

	private String login;
	private String password;

	private long endoId;
	private String cookie;
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastTimeUpdated;

	private int availableChests;
	private long gold;
	private long exp;
	private long lvl;
}

package co.blastlab.behealthy.quests.quest;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
public class Quest {

	@Id
	@GeneratedValue
	private long id;

	private String title;
	private String description;
	private String type;
	private int value;

	@Temporal(TemporalType.DATE)
	private Date date;

	@OneToMany(mappedBy = "quest")
	private List<QuestUser> users;
}

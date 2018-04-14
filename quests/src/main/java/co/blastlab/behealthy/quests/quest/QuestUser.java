package co.blastlab.behealthy.quests.quest;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
public class QuestUser {

	@Id
	@GeneratedValue
	private long id;

	private long userId;
	private Date completedAt;

	@ManyToOne
	@JoinColumn(name = "questId")
	private Quest quest;
}

package co.blastlab.behealthy.quests.quest;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class QuestDto {

	private long id;

	private String title;
	private String description;
	private String type;
	private int value;

	private Date date;

	private boolean done;
}

package co.blastlab.behealthy.quests.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Workout {
	private Double calories;
	private Short sport;
	private Double distance;
	private Double speed_avg;
	private Double duration;
	private String start_time;
	private String local_start_time;
}
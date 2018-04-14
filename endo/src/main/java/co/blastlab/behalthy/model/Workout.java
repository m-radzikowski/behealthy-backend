package co.blastlab.behalthy.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Workout {
    private Double calories;
    private Short sport;
    private Double distance;
    private Double speed_avg;
    private Double duration;
    private String start_time;
    private String local_start_time;
}

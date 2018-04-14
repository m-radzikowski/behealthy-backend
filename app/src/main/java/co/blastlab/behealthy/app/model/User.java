package co.blastlab.behealthy.app.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class User {
    private long id;
    private long endoId;
    private String cookie;
    private Date lastTimeUpdated;
    private long exp;
    private long lvl;
    private String login;
    private int availableChests;
}

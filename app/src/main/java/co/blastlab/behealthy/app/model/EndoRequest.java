package co.blastlab.behealthy.app.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EndoRequest {
    private long clientId;
    private String cookie;
    private Date after;
}

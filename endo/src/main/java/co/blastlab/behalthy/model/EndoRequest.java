package co.blastlab.behalthy.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;


@Getter
@Setter
public class EndoRequest {
    private Integer clientId;
    private String cookie;
    private Date after;
}

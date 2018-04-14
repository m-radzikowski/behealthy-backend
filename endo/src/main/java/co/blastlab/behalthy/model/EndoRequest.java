package co.blastlab.behalthy.model;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class EndoRequest {
    private Integer clientId;
    private String cookie;
    private String after;
}

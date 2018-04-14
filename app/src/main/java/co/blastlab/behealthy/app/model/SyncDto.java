package co.blastlab.behealthy.app.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SyncDto {
	private User user;
	private UserChanges changes;
}

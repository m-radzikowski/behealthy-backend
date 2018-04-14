package co.blastlab.behealthy.app;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

@Stateless
@Path("/user/{userId}/quest/daily")
public class QuestBean {

	@Inject
	private RestClient rc;

	@GET
	public Response daily(@PathParam("userId") String userId) {
		return rc.proxyGet(
			UriBuilder.fromUri(Consts.QUESTS_URL)
				.path("/user/{userId}/quest/daily")
				.resolveTemplate("userId", userId)
		);
	}

	@POST
	@Path("/{questId}/complete")
	public Response complete(@PathParam("userId") String userId, @PathParam("questId") String questId) {
		return rc.proxyPost(
			UriBuilder.fromUri(Consts.QUESTS_URL)
				.path("/user/{userId}/quest/daily/{questId}/complete")
				.resolveTemplate("userId", userId).resolveTemplate("questId", questId)
		);
	}

}

package co.blastlab.behealthy.app;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

@Stateless
@Path("/user/{userId}/chest")
public class ChestBean {

	@Inject
	private RestClient rc;

	@POST
	@Path("/open")
	public Response open(@PathParam("userId") String userId) {
		return rc.proxyPost(UriBuilder.fromUri(Consts.USERS_URL)
			.path("/user/{userId}/chest/open")
			.resolveTemplate("userId", userId));
	}

}

package co.blastlab.behealthy.app;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

@Stateless
@Path("/login")
public class LoginBean {

	@Inject
	private RestClient rc;

	@GET
	public Response login(@QueryParam("login") String login, @QueryParam("password") String password) {
		UriBuilder ub = UriBuilder.fromUri(Consts.USERS_URL).path("/login").queryParam("login", login).queryParam("password", password);
		return rc.proxyQuery(ub);
	}


}

package co.blastlab.behealthy.app;

import co.blastlab.behealthy.app.model.SyncDto;
import co.blastlab.behealthy.app.model.User;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import java.text.ParseException;

@Stateless
@Path("/login")
public class LoginBean {

	@Inject
	private SynchronizeBean synchronizeBean;

	@GET
	public Response login(@QueryParam("login") String login, @QueryParam("password") String password) throws ParseException {
		Client client = ClientBuilder.newClient();
		UriBuilder ub = UriBuilder.fromUri(Consts.USERS_URL).path("/login").queryParam("login", login).queryParam("password", password);
		Response r = client.target(ub)
			.request(MediaType.APPLICATION_JSON)
			.accept(MediaType.APPLICATION_JSON)
			.get(Response.class);

		if (r.getStatus() != 200) {
			return Response.status(r.getStatus()).entity(r.readEntity(String.class)).build();
		}

		long userId = r.readEntity(User.class).getId();
		SyncDto dto = synchronizeBean.sync(userId);

		return Response.ok(dto).build();
	}

}

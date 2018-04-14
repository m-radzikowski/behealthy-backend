package co.blastlab.behealthy.app;

import javax.ejb.Stateless;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

@Stateless
public class RestClient {

	public Response proxyGet(UriBuilder ub) {
		Client client = ClientBuilder.newClient();
		Response r = client.target(ub)
			.request(MediaType.APPLICATION_JSON)
			.accept(MediaType.APPLICATION_JSON)
			.get(Response.class);

		return Response.status(r.getStatus()).type(MediaType.APPLICATION_JSON).entity(r.readEntity(String.class)).build();
	}

	public Response proxyPost(UriBuilder ub, Entity entity) {
		Client client = ClientBuilder.newClient();
		Response r = client.target(ub)
			.request(MediaType.APPLICATION_JSON)
			.accept(MediaType.APPLICATION_JSON)
			.post(entity);

		return Response.status(r.getStatus()).type(MediaType.APPLICATION_JSON).entity(r.readEntity(String.class)).build();
	}

	public Response proxyPost(UriBuilder ub) {
		return proxyPost(ub, null);
	}

}

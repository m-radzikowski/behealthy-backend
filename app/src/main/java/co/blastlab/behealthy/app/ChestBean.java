package co.blastlab.behealthy.app;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.ejb.Stateless;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

@Stateless
@Path("/user/{userId}/chest")
public class ChestBean {

	@POST
	@Path("/open")
	public Response open(@PathParam("userId") String userId) {
		UriBuilder chestOpenUB = UriBuilder.fromUri(Consts.USERS_URL)
			.path("/user/{userId}/chest/open")
			.resolveTemplate("userId", userId);

		Client client = ClientBuilder.newClient();
		Response chestOpenResponse = client.target(chestOpenUB)
			.request(MediaType.APPLICATION_JSON)
			.accept(MediaType.APPLICATION_JSON)
			.post(null);

		if (chestOpenResponse.getStatus() >= 200 && chestOpenResponse.getStatus() < 300) {
			chestOpenResponse.close();

			UriBuilder rewardUB = UriBuilder.fromUri(Consts.REWARD_URL)
				.path("/chest/reward");
			Reward reward = client.target(rewardUB)
				.request(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.get(Reward.class);

			UriBuilder userUB = UriBuilder.fromUri(Consts.USERS_URL)
				.path("/user/{userId}/gold/add")
				.resolveTemplate("userId", userId)
				.queryParam("amount", reward.getGold());
			client.target(userUB)
				.request(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.post(null);

			return Response.ok(reward).build();
		} else {
			return Response.status(chestOpenResponse.getStatus()).entity(chestOpenResponse.readEntity(String.class)).build();
		}
	}

	@Getter
	@Setter
	@NoArgsConstructor
	public static class Reward {
		private int gold;
	}

}

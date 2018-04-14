package co.blastlab.behealthy.app;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

@Stateless
@Path("/user/{userId}/coupon")
public class CouponBean {

	@Inject
	private RestClient rc;

	@GET
	@Path("/available")
	public Response available(@PathParam("userId") long userId) {
		return rc.proxyGet(
			UriBuilder.fromUri(Consts.SHOP_URL)
				.path("/user/{userId}/coupon/available")
				.resolveTemplate("userId", userId)
		);
	}

	@GET
	@Path("/my")
	public Response my(@PathParam("userId") long userId) {
		return rc.proxyGet(
			UriBuilder.fromUri(Consts.SHOP_URL)
				.path("/user/{userId}/coupon/my")
				.resolveTemplate("userId", userId)
		);
	}

	@POST
	@Path("/{couponId}/buy")
	public Response buy(@PathParam("userId") long userId, @PathParam("couponId") long couponId) {
		UriBuilder couponUB = UriBuilder.fromUri(Consts.SHOP_URL)
			.path("/user/{userId}/coupon/{couponId}")
			.resolveTemplate("userId", userId)
			.resolveTemplate("couponId", couponId);

		Client client = ClientBuilder.newClient();
		Coupon coupon = client.target(couponUB)
			.request(MediaType.APPLICATION_JSON)
			.accept(MediaType.APPLICATION_JSON)
			.get(Coupon.class);

		UriBuilder userUB = UriBuilder.fromUri(Consts.USERS_URL)
			.path("/user/{userId}/gold/subtract")
			.resolveTemplate("userId", userId)
			.queryParam("amount", coupon.getGold());

		Response userResponse = client.target(userUB)
			.request(MediaType.APPLICATION_JSON)
			.accept(MediaType.APPLICATION_JSON)
			.post(null);

		if (userResponse.getStatus() != 200) {
			return Response.status(userResponse.getStatus()).entity(userResponse.readEntity(String.class)).build();
		}
		userResponse.close();

		UriBuilder addCouponUB = UriBuilder.fromUri(Consts.SHOP_URL)
			.path("/user/{userId}/coupon/my/{couponId}")
			.resolveTemplate("userId", userId)
			.resolveTemplate("couponId", coupon.getId());

		client.target(addCouponUB)
			.request(MediaType.APPLICATION_JSON)
			.accept(MediaType.APPLICATION_JSON)
			.post(null);

		return Response.ok(coupon).build();
	}

	@Getter
	@Setter
	@NoArgsConstructor
	public static class Coupon {

		private long id;
		private String title;
		private String description;
		private int gold;

	}

}

package co.blastlab.behealty.app;

import co.blastlab.behealty.app.entity.User;
import co.blastlab.behealty.app.exception.UserNotFoundException;
import co.blastlab.behealty.app.repository.UserRepository;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

@Stateless
@Path("/user/{userId}/chest")
public class ChestBean {

	@Inject
	private UserRepository userRepository;

	@POST
	@Path("/open")
	public Response open(@PathParam("userId") long userId) {
		User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);

		if (user.getAvailableChests() < 1) {
			return Response.status(Response.Status.BAD_REQUEST).entity("No chests to open").build();
		}

		user.setAvailableChests(user.getAvailableChests() - 1);

		return Response.ok(user).build();
	}
}

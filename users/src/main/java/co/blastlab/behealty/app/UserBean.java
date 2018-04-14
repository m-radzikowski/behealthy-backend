package co.blastlab.behealty.app;

import co.blastlab.behealty.app.entity.User;
import co.blastlab.behealty.app.exception.UserNotFoundException;
import co.blastlab.behealty.app.repository.UserRepository;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Stateless
@Path("/user/{userId}")
public class UserBean {

	@Inject
	private UserRepository userRepository;

	@POST
	@Path("/gold/add")
	public Response addGold(@PathParam("userId") long userId, @QueryParam("amount") int amount) {
		User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);

		user.setGold(user.getGold() + amount);

		return Response.ok().build();
	}

	@POST
	@Path("/gold/subtract")
	public Response subtractGold(@PathParam("userId") long userId, @QueryParam("amount") int amount) {
		User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);

		if (user.getGold() < amount) {
			throw new BadRequestException("Not enough gold");
		}

		user.setGold(user.getGold() - amount);

		return Response.ok().build();
	}

}

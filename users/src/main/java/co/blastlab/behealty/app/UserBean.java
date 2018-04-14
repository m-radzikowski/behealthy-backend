package co.blastlab.behealty.app;

import co.blastlab.behealty.app.entity.User;
import co.blastlab.behealty.app.exception.UserNotFoundException;
import co.blastlab.behealty.app.repository.UserRepository;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.Date;

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

    @GET
    @Path("/get")
    public Response get(@PathParam("userId") long userId) {
        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);

        System.out.println("users from db");
        System.out.println(user.getLastTimeUpdated());

        return Response.ok(user).build();
    }

    @POST
    @Path("/update")
    public Response update(@PathParam("userId") long userId, User update) {
        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        user.setExp(update.getExp());
        user.setLvl(update.getLvl());
        user.setLastTimeUpdated(update.getLastTimeUpdated());
        user = userRepository.save(user);
        return Response.ok(user).build();
    }
}

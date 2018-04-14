package co.blastlab.behealty.app;

import co.blastlab.behealty.app.entity.User;
import co.blastlab.behealty.app.repository.UserRepository;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import java.util.Optional;

@Stateless
@Path("/login")
public class LoginBean {

	@Inject
	private UserRepository userRepository;

	@GET
	public Response login(@QueryParam("login") String login, @QueryParam("password") String password) {
		Optional<User> user = userRepository.findByLoginAndPassword(login, password);

		if (!user.isPresent()) {
			return Response.status(Response.Status.UNAUTHORIZED).build();
		}

		return Response.ok(user.get()).build();
	}
}

package co.blastlab.behealty.app;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Stateless
@Path("/")
public class LoginBean {

	@PersistenceContext
	private EntityManager em;

	@GET
	public String login(@PathParam("login") String login, @PathParam("password") String password) {

		return "hello!";
	}
}

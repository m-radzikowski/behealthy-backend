package co.blastlab.behalthy;

import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Stateless
@Path("/")
public class SynchronizeBean {

	@GET
	public String sayHello() {
		return "hello!";
	}
}

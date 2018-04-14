package co.blastlab.behalthy;

import co.blastlab.behalthy.model.EndoRequest;
import co.blastlab.behalthy.model.Workout;

import javax.ejb.Stateless;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@Stateless
@Path("/")
public class SynchronizeBean {

	private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

	@POST
	public List<Workout> sync(EndoRequest endoRequest)  {
		return getWorkouts(endoRequest.getClientId(), LocalDateTime.parse(endoRequest.getAfter(), dateTimeFormatter), endoRequest.getCookie());
	}

	private List<Workout> getWorkouts(Integer userId, LocalDateTime after, String cookie) {
		Client client = ClientBuilder.newClient();
		UriBuilder ub = UriBuilder.fromUri("https://www.endomondo.com/rest/v1")
				.path(String.format("/users/%s/workouts", userId))
				.queryParam("before", LocalDateTime.now(ZoneId.of("Europe/Warsaw")).format(dateTimeFormatter))
				.queryParam("after", String.format("%s.000Z", after.format(dateTimeFormatter)));
		System.out.println(ub.build().toString());
		return client
				.target(ub)
				.request(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.cookie(new Cookie("USER_TOKEN", cookie))
				.get(new GenericType<List<Workout>>() {});
	}
}

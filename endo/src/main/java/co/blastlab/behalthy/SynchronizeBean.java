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
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@Stateless
@Path("/")
public class SynchronizeBean {

	DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");

	@POST
	public List<Workout> sync(EndoRequest endoRequest) throws UnsupportedEncodingException {
		System.out.println(getWorkouts(endoRequest.getClientId(), endoRequest.getAfter(), endoRequest.getCookie()));
		return getWorkouts(endoRequest.getClientId(), endoRequest.getAfter(), endoRequest.getCookie());
	}

	private List<Workout> getWorkouts(Integer userId, Date after, String cookie) throws UnsupportedEncodingException {
		Client client = ClientBuilder.newClient();
		UriBuilder ub = UriBuilder.fromUri("https://www.endomondo.com/rest/v1")
				.path(String.format("/users/%s/workouts", userId))
				.queryParam("before", df.format(new Date()))
				.queryParam("after", df.format(after));
		System.out.println(ub.build().toString());
		System.out.println(cookie);
//		System.out.println(URLEncoder.encode(cookie, "UTF-8"));
		return client
				.target(ub)
				.request(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.cookie(new Cookie("USER_TOKEN", URLDecoder.decode(cookie, "UTF-8")))
				.get(new GenericType<List<Workout>>() {});
	}
}

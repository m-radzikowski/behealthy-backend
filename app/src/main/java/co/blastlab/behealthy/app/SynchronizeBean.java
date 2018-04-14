package co.blastlab.behealthy.app;

import co.blastlab.behealthy.app.model.*;

import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Stateless
@Path("/user/{userId}/sync")
public class SynchronizeBean {

	@GET
	public SyncDto sync(@PathParam("userId") long id) throws ParseException {
		User user = getUser(id);
		List<Workout> workouts = getWorkouts(user);

		long addedExp = calculateExp(workouts);
		user.setExp(user.getExp() + addedExp);

		long newLevel = calculateLevel(user.getExp());
		long addedLvl = newLevel - user.getLvl();
		user.setLvl(newLevel);
		user.setAvailableChests(user.getAvailableChests() + 1);

		UserChanges changes = new UserChanges();
		changes.setAddedExp(addedExp);
		changes.setAddedLvl(addedLvl);

		updateUser(user, workouts);

		return new SyncDto(user, changes);
	}

	private User getUser(long id) {
		UriBuilder ub = UriBuilder.fromUri(Consts.USERS_URL).path("/user/{userId}/get").resolveTemplate("userId", id);
		Client client = ClientBuilder.newClient();
		return client.target(ub)
			.request(MediaType.APPLICATION_JSON)
			.accept(MediaType.APPLICATION_JSON)
			.get(User.class);
	}

	private List<Workout> getWorkouts(User user) {
		UriBuilder ub = UriBuilder.fromUri(Consts.ENDO_URL).path("/");
		Client client = ClientBuilder.newClient();
		return client.target(ub)
			.request(MediaType.APPLICATION_JSON)
			.accept(MediaType.APPLICATION_JSON)
			.post(
				Entity.entity(
					new EndoRequest(
						user.getEndoId(),
						user.getCookie(),
						user.getLastTimeUpdated()),
					MediaType.APPLICATION_JSON_TYPE),
				new GenericType<List<Workout>>() {
				});
	}

	private long calculateExp(List<Workout> workouts) {
		int exp = 0;
		for (Workout workout : workouts) {
			exp += workout.getCalories();
		}
		return exp;
	}

	private long calculateLevel(long exp) {
		return exp / 1000;
	}

	private void updateUser(User user, List<Workout> workouts) throws ParseException {
		UriBuilder ub = UriBuilder.fromUri(Consts.USERS_URL).path("/user/{userId}/update").resolveTemplate("userId", user.getId());
		Client client = ClientBuilder.newClient();
		if (workouts.size() > 0) {
			Workout last = workouts.get(0);
			DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
			Date lastTimeUpdated = formatter.parse(last.getStart_time());
			user.setLastTimeUpdated(lastTimeUpdated);
		}
		client.target(ub)
			.request(MediaType.APPLICATION_JSON)
			.accept(MediaType.APPLICATION_JSON)
			.post(Entity.entity(user, MediaType.APPLICATION_JSON));
	}

}

package co.blastlab.behealthy.quests.quest;

import co.blastlab.behealthy.quests.model.Workout;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
@Path("/user/{userId}/quest/daily")
public class QuestBean {

	@Inject
	private QuestRepository questRepository;

	@Inject
	private QuestUserRepository questUserRepository;

	@GET
	public List<QuestDto> dailyQuests(@PathParam("userId") long userId) {
		List<Quest> quests = questRepository.findByDate(new Date());
		return quests.stream().map(q -> {
			QuestDto dto = new QuestDto();
			dto.setId(q.getId());
			dto.setTitle(q.getTitle());
			dto.setDescription(q.getDescription());
			dto.setType(q.getType());
			dto.setValue(q.getValue());
			dto.setDate(q.getDate());
			dto.setExp(q.getExp());

			dto.setDone(q.getUsers().stream().anyMatch(u -> u.getUserId() == userId));

			return dto;
		}).collect(Collectors.toList());
	}

	@POST
	@Path("/complete")
	public Response checkAndComplete(@PathParam("userId") long userId, List<Workout> workouts) {
		List<Quest> quests = questRepository.findByDate(new Date());

		int exp = 0;

		for (Quest q : quests) {
			if (q.getUsers().stream().anyMatch(u -> u.getUserId() == userId)) {
				continue;
			}

			if (q.getType().equals("RUN") && workouts.stream().anyMatch(w -> w.getSport() == 0 && w.getDistance() >= q.getValue())) {
				markCompleted(userId, q);
				exp += q.getExp();
			} else if (q.getType().equals("BIKE") && workouts.stream().anyMatch(w -> w.getSport() == 1 && w.getDistance() >= q.getValue())) {
				markCompleted(userId, q);
				exp += q.getExp();
			} else if (q.getType().equals("KCAL") && workouts.stream().anyMatch(w -> w.getCalories() >= q.getValue())) {
				markCompleted(userId, q);
				exp += q.getExp();
			}
		}

		return Response.ok(exp).build();
	}

	private void markCompleted(long userId, Quest quest) {
		QuestUser qu = new QuestUser();
		qu.setCompletedAt(new Date());
		qu.setQuest(quest);
		qu.setUserId(userId);
		questUserRepository.save(qu);
	}

}

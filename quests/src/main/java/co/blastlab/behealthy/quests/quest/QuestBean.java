package co.blastlab.behealthy.quests.quest;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
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

			dto.setDone(q.getUsers().stream().anyMatch(u -> u.getUserId() == userId));

			return dto;
		}).collect(Collectors.toList());
	}

	@POST
	@Path("/{questId}/complete")
	public Response markCompleted(@PathParam("userId") long userId, @PathParam("questId") long questId) {
		Quest quest = questRepository.findByIdAndDate(questId, new Date()).orElseThrow(() -> new BadRequestException("Quest not found"));

		QuestUser qu = new QuestUser();
		qu.setCompletedAt(new Date());
		qu.setQuest(quest);
		qu.setUserId(userId);
		questUserRepository.save(qu);

		return Response.ok().build();
	}

}

package co.blastlab.behealthy.quests.quest;

import org.apache.deltaspike.data.api.EntityRepository;
import org.apache.deltaspike.data.api.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface QuestRepository extends EntityRepository<Quest, Long> {

	List<Quest> findByDate(Date date);

	Optional<Quest> findByIdAndDate(long id, Date date);

}

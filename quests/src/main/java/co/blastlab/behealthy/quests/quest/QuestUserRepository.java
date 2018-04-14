package co.blastlab.behealthy.quests.quest;

import org.apache.deltaspike.data.api.EntityRepository;
import org.apache.deltaspike.data.api.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface QuestUserRepository extends EntityRepository<QuestUser, Long> {

}

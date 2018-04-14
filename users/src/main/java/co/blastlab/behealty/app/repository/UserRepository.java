package co.blastlab.behealty.app.repository;

import co.blastlab.behealty.app.entity.User;
import org.apache.deltaspike.data.api.EntityRepository;
import org.apache.deltaspike.data.api.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends EntityRepository<User, Long> {

	Optional<User> findById(Long id);

	Optional<User> findByLoginAndPassword(String login, String password);

}

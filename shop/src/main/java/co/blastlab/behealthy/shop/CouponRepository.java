package co.blastlab.behealthy.shop;

import org.apache.deltaspike.data.api.EntityRepository;
import org.apache.deltaspike.data.api.Query;
import org.apache.deltaspike.data.api.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CouponRepository extends EntityRepository<Coupon, Long> {

	@Query("select c from Coupon c join c.users u where u.userId = ?1")
	List<Coupon> findByUserId(long userId);

	Optional<Coupon> findById(long id);

}

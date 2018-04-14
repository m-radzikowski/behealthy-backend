package co.blastlab.behealthy.shop;

import org.apache.deltaspike.data.api.EntityRepository;
import org.apache.deltaspike.data.api.Repository;

@Repository
public interface CouponUserRepository extends EntityRepository<CouponUser, Long> {

}

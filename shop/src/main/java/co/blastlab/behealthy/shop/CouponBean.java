package co.blastlab.behealthy.shop;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
@Path("/user/{userId}/coupon")
public class CouponBean {

	@Inject
	private CouponRepository couponRepository;

	@Inject
	private CouponUserRepository couponUserRepository;

	@GET
	@Path("/available")
	public List<Coupon> available(@PathParam("userId") long userId) {
		List<Coupon> userCoupons = couponRepository.findByUserId(userId);

		return couponRepository.findAll().stream()
			.filter(c -> userCoupons.stream().noneMatch(uc -> uc.getId() == c.getId()))
			.collect(Collectors.toList());
	}

	@GET
	@Path("/my")
	public List<Coupon> my(@PathParam("userId") long userId) {
		return couponRepository.findByUserId(userId);
	}

	@POST
	@Path("/my/{couponId}")
	public Response add(@PathParam("userId") long userId, @PathParam("couponId") long couponId) {
		Coupon coupon = couponRepository.findById(couponId).orElseThrow(() -> new BadRequestException("Coupon not found"));

		if (coupon.getUsers().stream().anyMatch(cu -> cu.getUserId() == userId)) {
			throw new BadRequestException("User already has this coupon");
		}

		CouponUser cu = new CouponUser();
		cu.setBoughtAt(new Date());
		cu.setCoupon(coupon);
		cu.setUserId(userId);
		couponUserRepository.save(cu);

		return Response.ok().build();
	}

	@GET
	@Path("/{couponId}")
	public Coupon get(@PathParam("couponId") long couponId) {
		return couponRepository.findBy(couponId);
	}

}

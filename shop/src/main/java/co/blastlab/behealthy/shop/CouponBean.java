package co.blastlab.behealthy.shop;

import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Stateless
@Path("/user/{userId}/coupon")
public class CouponBean {

	@GET
	@Path("/available")
	public List<Coupon> available() {
		return Stream.of(
			new Coupon(1, "Talon na balon", "Z tym kuponem możesz odebrać darmowy balon w każdym sklepie z balonami", 1500),
			new Coupon(2, "Kawa w BIOWAY", "Odbierz darmową kawę do posiłku w BIOWAY", 5300),
			new Coupon(3, "Lorem ipsum", "Pamiętaj o uzupełnieniu tego opisu przed demo", 4500)
		).collect(Collectors.toList());
	}

	@GET
	@Path("/my")
	public List<Coupon> my() {
		return Stream.of(
			new Coupon(1, "Talon na balon", "Z tym kuponem możesz odebrać darmowy balon w każdym sklepie z balonami", 1500)
		).collect(Collectors.toList());
	}

	@GET
	@Path("/{couponId}")
	public Coupon get() {
		return new Coupon(1, "Talon na balon", "Z tym kuponem możesz odebrać darmowy balon w każdym sklepie z balonami", 1500);
	}

}

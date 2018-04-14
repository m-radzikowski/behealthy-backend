package co.blastlab.behealthy.shop;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
public class CouponUser {

	@Id
	@GeneratedValue
	private long id;

	private long userId;
	private Date boughtAt;

	@ManyToOne
	@JoinColumn(name = "couponId")
	private Coupon coupon;
}

package co.blastlab.behealthy.shop;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Coupon {

	private long id;
	private String title;
	private String description;
	private int gold;

}

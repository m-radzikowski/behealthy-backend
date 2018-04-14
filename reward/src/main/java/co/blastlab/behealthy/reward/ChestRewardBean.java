package co.blastlab.behealthy.reward;

import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.util.Random;

@Stateless
@Path("/chest/reward")
public class ChestRewardBean {

	@GET
	public Reward reward() {
		return new Reward(getRandomValue(20, 40) * 10);
	}

	private int getRandomValue(int from, int to) {
		Random random = new Random();
		return random.nextInt(to - from + 1) + from;
	}

}

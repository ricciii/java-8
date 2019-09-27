import java.util.Random;
import java.util.Optional;

public class Horse {
	
	private String name;
	private boolean healthy;
	private String warcry;

	public Horse(String name, String warcry) {
		this.name = name;
		this.warcry = Optional.ofNullable(warcry).orElse(this.name);
		Random random = new Random();
		this.healthy = random.nextBoolean();
	}

	public Horse() {
		
	}

	public String getName() {
		return this.name;
	}

	public String getWarcry() {
		return this.warcry;
	}

	public boolean isHealthy() {
		return this.healthy;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setWarcry(String warcry) {
		this.warcry = warcry;
	}

	public void setHealthy(Boolean healthy) {
		this.healthy = healthy;
	}

}
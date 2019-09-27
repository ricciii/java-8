import java.util.Optional;
import java.util.concurrent.Phaser;
import java.util.Random;

public class RunnableHorse extends Horse {
	
	private int currentSpeed;
	private int distanceToTravel;
	final private int distanceToStartingLine = 10;
	private static Ranker ranker;
	private Phaser phaser;
	
	public RunnableHorse(String name, String warcry, int distanceToTravel, Phaser phaser) {
		super(name, warcry);
		this.distanceToTravel = distanceToTravel;
		this.phaser = phaser;
	}

	public RunnableHorse(String name, String warcry, int distanceToTravel) {
		super(name, warcry);
		this.distanceToTravel = distanceToTravel;
	}

	public RunnableHorse() {

	}

	public void setPhaser(Phaser phaser) {
		this.phaser = phaser;
	}

	public void setRanker(Ranker ranker) {
		this.ranker = ranker;
	}

	public void run() {
		this.phaser.register();
		int distanceTraveled = 0;
		boolean completed=false;
		Random random = new Random();
		int i = 1;
		try {
			while (completed == false) {
				if(distanceTraveled >= distanceToStartingLine) {
					System.out.printf("%-20s %-10s %-10s %-20s %-20s %-20s\n", super.getName(), "0", 
						"0", "Starting line", "", "i: " + (i++) + "-" + System.currentTimeMillis());
					completed=true;
				} else {
					System.out.printf("%-20s %-10s %-10s %-20s %-20s %-20s\n", super.getName(), 
						this.currentSpeed, this.distanceToStartingLine-distanceTraveled, "Running", "",
						"i: " + (i++) + "-" + System.currentTimeMillis());
					this.currentSpeed = 1 + random.nextInt(10);
					distanceTraveled += this.currentSpeed;
				}
			}
			i=1;
			this.currentSpeed=0;
			distanceTraveled=0;
			phaser.arriveAndAwaitAdvance();
			System.out.println(super.getName() + " has left the starting line!");
			//Thread.sleep(500);
			completed=false;
			while (completed == false) {
				if(distanceTraveled >= this.distanceToTravel) {
					completed=true;
					System.out.printf("%-20s %-10s %-10s %-20s %-20s %-20s\n", super.getName(), "0", 
						"0", "Arrived", super.getWarcry(), 
						"i: " + (i++) + "-" + System.currentTimeMillis());				
				} else {
					System.out.printf("%-20s %-10s %-10s %-20s %-20s %-20s\n", super.getName(), 
						this.currentSpeed, this.distanceToTravel-distanceTraveled, "Running", "", 
						"i: " + (i++) + "-" + System.currentTimeMillis());
					this.currentSpeed = 1 + random.nextInt(10);
					distanceTraveled += this.currentSpeed;
				}
				//Thread.sleep(500);
			}
			this.phaser.arriveAndDeregister();
			if(this.ranker!=null) {
				this.ranker.rank(this);
			}
        } catch(Exception exception) {
			System.out.println(exception);
		}
	}
}
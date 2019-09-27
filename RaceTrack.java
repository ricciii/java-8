import java.util.ArrayList;
import java.util.concurrent.Phaser;

public class RaceTrack {
	private ArrayList<RunnableHorse> raceHorses;
	private Phaser phaser = new Phaser();
	private Ranker ranker = new Ranker();

	public RaceTrack(ArrayList<RunnableHorse> raceHorses) {
		this.raceHorses = raceHorses;
		for(RunnableHorse horse: raceHorses) {
			horse.setRanker(this.ranker);
		}
	}

	public RaceTrack() {
		
	}
	
	public void race() {
		System.out.printf("%-20s %-10s %-10s %-20s %-20s %-20s\n", "Name:", "Speed:", "Remaining:",
			"Status:", "Warcry:", "Iteration - Time:\n");
		this.raceHorses.parallelStream()
			.filter(horse -> horse.isHealthy()==true)
			.forEach(horse -> horse.run());
		this.ranker.displayRanking();
	}

}
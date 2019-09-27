

public class App {

	public static void main(String[] args) {
		App app = new App();
		app.start();
	}

	public void start() {
		HorseNameGenerator nameGenerator = new HorseNameGenerator();
		WarcryGenerator warcryGenerator = new WarcryGenerator();
		nameGenerator.generate();
		warcryGenerator.generate();
		RacingHorseGenerator raceHorseGenerator = new RacingHorseGenerator(nameGenerator.getList(), warcryGenerator.getList());
		raceHorseGenerator.generate();
		RaceTrack raceTrack = new RaceTrack(raceHorseGenerator.getHorses());
		raceTrack.race(); 
	}
}
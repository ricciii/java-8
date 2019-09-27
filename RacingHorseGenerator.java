import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.concurrent.Phaser;

public class RacingHorseGenerator {

	private ArrayList<RunnableHorse> horses;
	private int maxHorses = Runtime.getRuntime().availableProcessors();
	private ArrayList<String> horseNameList;
	private ArrayList<String> warcryList; 
	private int numOfHorses;
	final private int maxDistance = 200;
	private Phaser phaser = new Phaser();

	public RacingHorseGenerator(ArrayList<String> horseNameList, ArrayList<String> warcryList) {
		this.horseNameList = horseNameList;
		this.warcryList = warcryList;
	}
	
	public void generate() {
		Scanner scanner = new Scanner(System.in);
		boolean done = false;
		int distanceToTravel;
		do {
			this.horses = new ArrayList<RunnableHorse>();
			try {
				System.out.print("How many horses do you want to race: ");
				this.numOfHorses = scanner.nextInt();
				if (this.numOfHorses>this.maxHorses) {
					System.out.println("Max number of horses is: " + this.maxHorses);
				} else {
					System.out.print("Please enter the distance: ");
					distanceToTravel = scanner.nextInt();
					if(distanceToTravel < maxDistance) {
						System.out.println("Distance must be greater than " + maxDistance +
							". Please try again.");
					} else {
						Collections.shuffle(this.horseNameList);
						Collections.shuffle(this.warcryList);
						int i=0;
						System.out.println();
						while(i<numOfHorses) {
							if((((i>=this.horseNameList.size())==true)||((i>=this.warcryList.size())==true))==true) {
								if((((i>=this.horseNameList.size())==true)&&((i>=this.warcryList.size())==true))==true) {
									this.horses.add(new RunnableHorse("Horse #"+i, "Warcry #"+(i+1), distanceToTravel, this.phaser));
									System.out.printf("%-20s %-20s %-20s\n","Horse "+(i+1)+": ", 
										"Horse #"+(i+1), "Healthy: "+this.horses.get(i).isHealthy());
								} else if((i>=this.horseNameList.size())==true) {
									this.horses.add(new RunnableHorse("Horse #"+i, this.warcryList.get(i), distanceToTravel, this.phaser));
									System.out.printf("%-20s %-20s %-20s\n","Horse "+(i+1)+": ", 
										"Horse #"+(i+1), "Healthy: "+this.horses.get(i).isHealthy());
								} else {
									this.horses.add(new RunnableHorse(this.horseNameList.get(i), "Warcry #"+(i+1), distanceToTravel, this.phaser));
									System.out.printf("%-20s %-20s %-20s\n","Horse "+(i+1)+": ", 
										this.horseNameList.get(i), "Healthy: "+this.horses.get(i).isHealthy());
								}
							} else {
								this.horses.add(new RunnableHorse(this.horseNameList.get(i), this.warcryList.get(i), distanceToTravel, this.phaser));
								System.out.printf("%-20s %-20s %-20s\n","Horse "+(i+1)+": ", 
									this.horseNameList.get(i), "Healthy: "+this.horses.get(i).isHealthy());
							}
							i++;
						}
						int numOfHealthyHorses = (int) horses.stream()
							.filter(horse -> horse.isHealthy()==true)
							.count();
						System.out.println("\nNumber of healthy horses: " + numOfHealthyHorses + "\n");
						if((numOfHealthyHorses>=2)==false) {
							System.out.println("There must be at least 2 healthy horses to start the race.\nPlease start again.\n");
						} else {
							done=true;
						}
					}
				}
			} catch(InputMismatchException inputMismatchError) {
				System.out.println(inputMismatchError);
			} catch(Exception exception) {
				System.out.println(exception);
			}
		} while(done==false);
	}

	public ArrayList<RunnableHorse> getHorses() {
		return this.horses;
	}
}
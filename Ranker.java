import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class Ranker {

	private List<String> ranking = Collections.synchronizedList(new ArrayList<String>());

	public void rank(RunnableHorse runnableHorse) {
		this.ranking.add(runnableHorse.getName());
	}

	public void displayRanking() {
		System.out.printf("\n%-20s %-20s\n","RANKING","NAME");
		int i=1;
		for(String name: ranking) {
			System.out.printf("%-20s %-20s\n", i++, name);
		}
	}
}
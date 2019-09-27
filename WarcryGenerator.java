import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileNotFoundException;

public class WarcryGenerator {
	private ArrayList<String> warcryList;

	public ArrayList<String> getList() {
		return this.warcryList;
	}

	public void generate() {
		this.warcryList = new ArrayList<String>();
		try {
			File warcries = new File("warcries.txt");
	 		FileReader fileReader = new FileReader(warcries);
	 		BufferedReader bufferedReader = new BufferedReader(fileReader);
	 		String line;
			this.warcryList.add(null);
			while((line = bufferedReader.readLine()) != null) {
	 			if(this.warcryList.contains(line) == false) {
	 				this.warcryList.add(line);
	 			}
	 		}
		} catch(FileNotFoundException fileNotFoundError) {
	 		System.out.println(fileNotFoundError);
		} catch(IOException ioError) {
			System.out.println(ioError);
		} catch(NullPointerException nullPointerError) {
			System.out.println(nullPointerError);
		}
	}
}
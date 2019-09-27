import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileNotFoundException;

public class HorseNameGenerator {
	private ArrayList<String> horseNameList; 

	public void generate() {
		this.horseNameList = new ArrayList<String>();
		try {
			File horseNames = new File("horseNames.txt");
	 		FileReader fileReader = new FileReader(horseNames);
	 		BufferedReader bufferedReader = new BufferedReader(fileReader);
	 		String line;
			while((line = bufferedReader.readLine()) != null) {
	 			if(this.horseNameList.contains(line) == false) {
	 				this.horseNameList.add(line);
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

	public ArrayList<String> getList() {
		return this.horseNameList;
	}
}
package textproc;
 
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import javax.swing.JFileChooser;
import javax.swing.JList;
import javax.swing.JScrollPane;
 
public class BookReaderApplication {

	public static void main(String[] args) throws FileNotFoundException {
		JFileChooser fc = new JFileChooser("~/Desktop/JavaFördjupning/javaKurs/edaa01-workspace/lab3");
		fc.showDialog(null, "Välj fil");
		File file = fc.getSelectedFile();
		

		Scanner scan = new Scanner(file);

		Set<String> stopWords = new HashSet<String>();
		
		while (scan.hasNext()) {
		String scanNext = scan.next();
		stopWords.add(scanNext);	
		}
		GeneralWordCounter f =  new GeneralWordCounter(stopWords);
		scan.close();
		
		Scanner s = new Scanner(new File("nilsholg.txt"));
		s.findWithinHorizon("\uFEFF", 1);
		s.useDelimiter("(\\s|,|\\.|:|;|!|\\?|'|\\\")+");
		while (s.hasNext()) {
			String word = s.next().toLowerCase();
			f.process(word);
		}
		s.close();
		
		
		BookReaderController controller = new BookReaderController(f);
		
		
		
		
		
		
		//controller.createWindow(f,"test",100,200);
	}
	
	
	
}

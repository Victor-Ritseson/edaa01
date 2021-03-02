package textproc;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Set;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class Holgersson {
	private static List<TextProcessor> lista = new ArrayList<TextProcessor>();
	
	public static final String[] REGIONS = { "blekinge", "bohuslän", "dalarna", "dalsland", "gotland", "gästrikland",
			"halland", "hälsingland", "härjedalen", "jämtland", "lappland", "medelpad", "närke", "skåne", "småland",
			"södermanland", "uppland", "värmland", "västerbotten", "västergötland", "västmanland", "ångermanland",
			"öland", "östergötland" };

	public static void main(String[] args) throws FileNotFoundException {
//tidsmätning
		
		long t0 = System.nanoTime();
		
		// nya
		Scanner scan = new Scanner(new File("undantagsord.txt"));
		Set<String> stopWords = new HashSet<String>();
		
		while (scan.hasNext()) {
		String scanNext = scan.next();
		stopWords.add(scanNext);
			
		}
		GeneralWordCounter f =  new GeneralWordCounter(stopWords);
		
		
		scan.close();
		
	
		
		TextProcessor p = new SingleWordCounter("nils");
		TextProcessor n = new SingleWordCounter("norge");
		MultiWordCounter m = new MultiWordCounter(REGIONS);
		
		
		lista.addAll(Arrays.asList(n,p));
		
		Scanner s = new Scanner(new File("nilsholg.txt"));
		s.findWithinHorizon("\uFEFF", 1);
		s.useDelimiter("(\\s|,|\\.|:|;|!|\\?|'|\\\")+"); // se handledning

		while (s.hasNext()) {
			String word = s.next().toLowerCase();

			f.process(word);
			
			m.process(word);
			
			lista.forEach(x -> x.process(word));
		}

		s.close();
		//lista.forEach(x -> x.report());
		//m.report();
		f.report();
		
		//tidsmätning
		long t1 = System.nanoTime();
		System.out.println("tid: " + (t1 - t0) / 1000000.0 + " ms");
		
	}
}
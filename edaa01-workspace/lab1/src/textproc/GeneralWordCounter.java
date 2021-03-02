package textproc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class GeneralWordCounter implements TextProcessor {
	private Map<String, Integer> ord = new TreeMap<String,Integer>();
	private Set<String> undantag = new HashSet<String>();
	
	public List<Map.Entry<String, Integer>> getWordList() {
		return new ArrayList<Map.Entry<String, Integer>>(ord.entrySet());
	}
	
	
	public GeneralWordCounter(Set<String> xs) {
		this.undantag = xs;
		
	}
	
	
	public void process(String w) {
		if(!undantag.contains(w)) {
			if(ord.containsKey(w)) {
			ord.put(w, ord.get(w) + 1);
			}
			else {
				ord.put(w, 1);
			
			}
		}

	}

	
	public void report() {
		
		Set<Map.Entry<String, Integer>> wordSet = ord.entrySet(); 
		List<Map.Entry<String, Integer>> wordList = new ArrayList<>(wordSet);
		
		wordList.sort(new WordCountComparator());
		
		for(int i = 0; i < 5; i++) {
			System.out.println(wordList.get(i));
			
		}


		
	}

}

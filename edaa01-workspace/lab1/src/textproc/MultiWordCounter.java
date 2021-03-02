package textproc;

import java.util.HashMap;
import java.util.Map;

public class MultiWordCounter implements TextProcessor {

private Map<String, Integer> ord = new HashMap<String,Integer>();
	
	
	public MultiWordCounter(String[] xs) {
		for(int i = 0; i < xs.length; i++) {
		ord.put(xs[i], 0);
	}
	}
	
	
	public void process(String w) {
		if(ord.keySet().contains(w)) {
			ord.put(w, ord.get(w) + 1);
		}

	}

	public void report() {
		for(String s : ord.keySet()) {
			System.out.println(s + " " + ord.get(s));
			
		}
	}

}

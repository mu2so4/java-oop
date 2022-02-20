package lab1;

import java.util.*;

public class Dictionary {
	private int count = 0;
	private final TreeMap<String, Integer> data = new TreeMap<>();
	
	public void insert(String word) {
		Integer current = data.get(word);
		if(current == null)
			current = 0;
		data.put(word, current + 1);
		count++;
	}
	
	public int getDictionarySize() {
		return count;
	}
	
	public TreeSet<Pair<Integer, String>> getDescendingDictionary() {
		TreeSet<Pair<Integer, String>> res = new TreeSet<>();


		for (Map.Entry<String, Integer> entry : data.entrySet()) {
			res.add(new Pair<>(entry.getValue(), entry.getKey()));
		}

		return res;
	}
}

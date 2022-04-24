package lab1;

import java.util.*;

public class Dictionary {
	private int wordsCount = 0;
	private final TreeSet<Word> words = new TreeSet<>();
	
	public void insert(String word) {
		if(word.equals(""))
			return;
		Word candidate = new Word(word), found = words.floor(candidate);
		if(found == null || !word.equals(found.getLexeme()))
			words.add(candidate);
		else
			found.setFrequency(found.getFrequency() + 1);
		wordsCount++;
	}

	public Word[] getDescendingDictionary() {
		Word[] desc = new Word[words.size()];
		words.toArray(desc);
		Arrays.sort(desc, (a, b) -> {
			int cmp1 = b.getFrequency() - a.getFrequency();
			if(0 != cmp1)
				return cmp1;
			return a.getLexeme().compareTo(b.getLexeme());
		});
		return desc;
	}

	public int getDictionarySize() {
		return wordsCount;
	}
}

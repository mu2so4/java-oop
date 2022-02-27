package lab1;

public class Word implements Comparable<Word> {
    private final String lexeme;
    private int frequency;

    public Word(String lexeme) {
        this.lexeme = lexeme;
        frequency = 1;
    }

    public String getLexeme() {
        return lexeme;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    @Override
    public int compareTo(Word word) {
        return lexeme.compareTo(word.lexeme);
    }
}

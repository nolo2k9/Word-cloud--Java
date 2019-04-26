package ie.gmit.svp;

public class WordFrequencys implements Comparable<WordFrequencys> {
	private String word;
	private int frequency;

	// word frequency object to populate queue
	public WordFrequencys(String word, int frequency) {

		this.word = word;
		this.frequency = frequency;
	}

	public int getFrequency() {
		return frequency;
	}

	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	// used to sort priority queue
	public int compareTo(WordFrequencys o) {

		return o.frequency - this.frequency;
	}
}

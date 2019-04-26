package ie.gmit.svp;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

import javax.imageio.ImageIO;

public class UpdateMap {
	private Map<String, Integer> words = new HashMap<String, Integer>();
	private Queue<WordFrequencys> q = new PriorityQueue<>();
	private PlaceWords pw = new PlaceWords();

	public UpdateMap() {

	}

	//O(n)
	// Checks if map contains key already, if so increment value else add with value
	// 1
	public void addWord(String word) {
		Integer count = words.get(word);
		if (count != null) {
			count = count + 1;
		} else {
			count = 1;
		}
		if (word != "")
			words.put(word, count);

	}

	// O(n^2)
	// Create the set of the keys in the hash map, then loops through the set and
	// adds word frequency objects
	// to queue
	public void populateQueue(String numOfWords, String name) {
		Set<String> keys = words.keySet();
		for (String key : keys) {
			q.offer(new WordFrequencys(key, words.get(key)));
		}
		pw.PlaceWordsImg(q, numOfWords, name);
	}

}
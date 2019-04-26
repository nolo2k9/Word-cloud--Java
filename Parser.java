package ie.gmit.svp;

import java.util.HashSet;
import java.util.Set;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Stream;

import javax.swing.text.Style;

public class Parser {

	private int wordsAmount = 20;
	Map<String, Integer> map;
	// Queue <WordFrequencys> q = new PriorityQueue<>();
	Set<String> ignoreWords;
	UpdateMap UDM = new UpdateMap();

	// On3
	// Creates a hashset from ignoreWords.txt
	// Uses a lambda for each to populate the set
	// Uses buffered reader to read input stream file or web connection
	// Loops through buffered reader, checks if word is not in ignorewords set and
	// if not-
	// calls method to add it to the hashmap
	// Then calls function to populate priority queue

	public void parse(InputStream input, String numOfWords, String name) throws Exception {
		//
		BufferedReader br = new BufferedReader(new InputStreamReader(input));
		ignoreWords = new HashSet<String>();
		Stream<String> stream = Files.lines(Paths.get("/ignorewords.txt"));
		stream.forEach(s -> ignoreWords.add(s.toUpperCase()));

		String next = null;
		while ((next = br.readLine()) != null) {
			String[] words = next.split(" ");
			for (String word : words) {
				if (!ignoreWords.contains(word.replaceAll("[^a-zA-Z_]", "").toUpperCase())) {
					UDM.addWord(word);
				}
			}
		}
		UDM.populateQueue(numOfWords, name);

	}

}

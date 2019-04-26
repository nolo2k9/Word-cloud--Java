package ie.gmit.svp;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;
import java.util.Scanner;

public class Menu {
	boolean keepGoing = true;
	private Scanner s = new Scanner(System.in);
	private String option;
	private String fileName;
	private String numOfWords;
	private String imgName;
	private String printImage;
	private String fileOrUrl;

	Parser p = new Parser();
	// O(n^3)
	// This method asks the user to select a file or url and puts it into s.next();
	// While keep going is "true" it continues to loop through the options

	public void show() throws Exception {
		System.out.println("Welcome to Word Cloud" + "\n");
		System.out.println("Select F for File or U URL" + "\n");
		fileOrUrl = s.next();
		while (keepGoing = true) {
			printMenu(fileOrUrl);
		}
	}

	// Depending on whether the user has chosen a file or url, it prompts them to
	// enter either
	// it then asks them to enter the number of words, img name, and if they want to
	// continue
	public void printMenu(String txt) {

		if (txt.equals("f")) {
			System.out.println("Option 1: Enter File Path" + "\n");
			option = s.next();
		} else if (txt.equals("u")) {
			System.out.println("Option 1: Enter URL" + "\n");
			option = s.next();
		} else {
			keepGoing = false;
			System.out.println("Not Valid");
		}
		System.out.println("Option 2: Enter number of words to display" + "\n");
		numOfWords = s.next();
		System.out.println("Option 3: Enter image name" + "\n");
		imgName = s.next();
		System.out.println("Do you want to Continue? Press Y for yes, and N to quit " + "\n");
		printImage = s.next();

		try {
			handle(option, printImage, imgName, numOfWords, fileName, txt);
		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	// o(n^2)
	// handles takes the user inputed data and passes it to the parser
	// if the user chooses not to continue they are told "Goodbye"
	public void handle(String option, String printImage, String imgName, String numOfWords, String fileName, String str)
			throws Exception {

		InputStream inputFile;
		if (printImage.equals("n") || printImage.equals("N")) {
			keepGoing = false;
			System.out.println("You have opted to exit, Goodbye");
		} else { // if user selects f sets input stream to file
			if (str.equals("f")) {
				File initalFile = new File(option);
				inputFile = new FileInputStream(initalFile);
				System.out.println("Check photos!!!!");
			} else {
				// if user chooses url Open connection to web and set input stream
				URL website = new URL(option);
				URLConnection connection = website.openConnection();
				inputFile = connection.getInputStream();
				System.out.println("Going to Web, Hang on....");
			}
			// parses functions to parser class
			p.parse(inputFile, numOfWords, imgName);
		}

	}

}

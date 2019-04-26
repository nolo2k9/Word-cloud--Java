package ie.gmit.svp;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Queue;
import java.util.concurrent.ThreadLocalRandom;

import javax.imageio.ImageIO;

public class PlaceWords {

	public int getRandom(int Min, int Max) {
		return ThreadLocalRandom.current().nextInt(Min, Max + 1);
	}

	// O(n)
	// used to set size of words in png
	public int Scale(int inVal, int inMin, int inMax) {

		int upper_Bound = 210;// largest font size
		int lower_bound = 20;// smallest font size

		int value = (inVal);
		int Max = (inMax);
		int Min = (inMin);

		return (upper_Bound - lower_bound) * (value - Min) / (Max - Min) + lower_bound;

	}

	// pulls word frequency object from queue and sets font size/colour for png
	public void PlaceWordsImg(Queue<WordFrequencys> q, String numOfWords, String name) {
		BufferedImage image = new BufferedImage(600, 800, BufferedImage.TYPE_4BYTE_ABGR);
		Graphics g = image.getGraphics();
		Iterator<WordFrequencys> i = q.iterator();
		int counter = 0;
		int max = Integer.MIN_VALUE;
		int min = Integer.MAX_VALUE;
		while (i.hasNext() && counter < Integer.parseInt(numOfWords)) {
			int j = i.next().getFrequency();
			if (j > max)
				max = j;
			if (j < min)
				min = j;
			counter++;

		}
		int[] Style = { Font.PLAIN, Font.BOLD, Font.ITALIC };
		String[] type = { Font.SERIF, Font.SANS_SERIF };
		counter = 0;
		while (!q.isEmpty() && counter < Integer.parseInt(numOfWords)) {
			WordFrequencys wf = q.poll();
			Font font = new Font(type[getRandom(0, type.length - 1)], Style[getRandom(0, Style.length - 1)],
					Scale(wf.getFrequency(), min, max));
			g.setFont(font);
			if (counter % 2 == 0)
				g.setColor(Color.ORANGE);
			else
				g.setColor(Color.PINK);
			g.drawString(wf.getWord(), getRandom(0, 600), getRandom(0, 800));
			counter++;

		}
		// draws png and opens
		g.dispose();
		try {
			ImageIO.write(image, "png", new File(name + ".png"));
			Desktop.getDesktop().open(new File(name + ".png"));
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

}

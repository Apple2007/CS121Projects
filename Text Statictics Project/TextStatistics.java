/**
 * Text Statistics Project - CS121
 *
 * TextStatistics.java contains the logic to get statistics from a text file inputed in ProcessText.java. It also contains all of the getters for the statistics, along with the toString() method.
 * "Statistics are everything. Without them, we couldn't figure out stuff." - Me
 *
 * @author Jack Shorenstein
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.math.*;

public class TextStatistics implements TextStatisticsInterface {

	/**
	 * Instance Variables for Text Statistics including DELIMETER
	 */
	private static final String DELIMITERS = "[\\W\\d_]+";

	private int charCount;
	private int wordCount;
	private int lineCount;
	private int[] letterCount = new int[26];
	private int[] wordLengthCount = new int[24];
	private double averageWordLength;
	private int totalCharInWords;
	private File fileScanned;

	/**
	 * Runs through the text file to get statistics on the file such as character count, word count, etc.
	 * @param nextFile file inputed in ProcessText.java
	 */
	public TextStatistics(File nextFile) {
		fileScanned = nextFile;

		/**
		 * Won't work if file doesn't exist, so we need a try/catch.
		 */
		try {
			Scanner fileScan = new Scanner(fileScanned);

			/**
			 * Get number of lines, number of characters, number of words, how many times a letter is used, the average word length, and more.
			 */
			while(fileScan.hasNextLine()) {
				lineCount++;

				String line = fileScan.nextLine();

				for (char c : line.toCharArray()) {
					if (Character.isLetter(c)) {
						c = Character.toLowerCase(c);
						letterCount[c - 'a']++;
					}
				}

				charCount = charCount + line.length() + 1;
				Scanner lineScan = new Scanner(line);
				lineScan.useDelimiter(DELIMITERS);

				while(lineScan.hasNext()) {
					String word = lineScan.next();
					wordCount++;
					totalCharInWords += word.length();

					if (word.length() < wordLengthCount.length) {
						wordLengthCount[word.length()]++;
					}
				}

				averageWordLength = Math.round((double) totalCharInWords / wordCount * 100.0) / 100.0;
				lineScan.close();
			}

			fileScan.close();
		} catch (FileNotFoundException e) {
			System.err.println("Sorry, it seems like we can't process your file. Try again later! ;)");
		}
	}

	// - - - - - - - - - - - - - - - - - - - - - -

	/**
	 * Returns the Character Count from charCount
	 * @return number of characters
	 */
	@Override
	public int getCharCount() {
		return charCount;
	}

	/**
	 * Returns the Word Count from wordCount
	 * @return number of words
	 */
	@Override
	public int getWordCount() {
		return wordCount;
	}

	/**
	 * Returns number of lines from lineCount
	 * @return number of lines
	 */
	@Override
	public int getLineCount() {
		return lineCount;
	}

	/**
	 * Returns times each letter is used in an array
	 * @return times a letter is used in an array
	 */
	@Override
	public int[] getLetterCount() {
		int[] letterCountNew = letterCount;
		return letterCountNew;
	}

	/**
	 * Returns length of each word from wordLengthCount
	 * @return length of each word
	 */
	@Override
	public int[] getWordLengthCount() {
		int[] wordLengthCountNew = wordLengthCount;
		return wordLengthCountNew;
	}

	/**
	 * Returns average word length from averageWordLength
	 * @return average word length
	 */
	@Override
	public double getAverageWordLength() {
		return averageWordLength;
	}

	/**
	 * Displays statistics in a pretty way! Look how nice it looks! :)
	 * @return statistics about a inputed text file in a nice format
	 */
	@Override
	public String toString() {
		String result = "Statistics for " + fileScanned.getName() + "\n" +
				"==========================================================\n" +
				getLineCount() + " lines\n" +
				getWordCount() + " words\n" +
				getCharCount() + " characters\n" +
				"------------------------------\n" +
				"a = " + getLetterCount()[0] + "         b = " + getLetterCount()[1] + "\n" +
				"c = " + getLetterCount()[2] + "         d = " + getLetterCount()[3] + "\n" +
				"e = " + getLetterCount()[4] + "         f = " + getLetterCount()[5] + "\n" +
				"g = " + getLetterCount()[6] + "         h = " + getLetterCount()[7] + "\n" +
				"i = " + getLetterCount()[8] + "         j = " + getLetterCount()[9] + "\n" +
				"k = " + getLetterCount()[10] + "         l = " + getLetterCount()[11] + "\n" +
				"m = " + getLetterCount()[12] + "         n = " + getLetterCount()[13] + "\n" +
				"o = " + getLetterCount()[14] + "         p = " + getLetterCount()[15] + "\n" +
				"q = " + getLetterCount()[16] + "         r = " + getLetterCount()[17] + "\n" +
				"s = " + getLetterCount()[18] + "         t = " + getLetterCount()[19] + "\n" +
				"u = " + getLetterCount()[20] + "         v = " + getLetterCount()[21] + "\n" +
				"w = " + getLetterCount()[22] + "         x = " + getLetterCount()[23] + "\n" +
				"y = " + getLetterCount()[24] + "         z = " + getLetterCount()[25] + "\n" +
				"------------------------------\n" +
				" length  frequency\n" +
				" ------  ---------\n";

		for (int i = 1; i < wordLengthCount.length && wordLengthCount[i] != 0; i++) {
			result += "      " + i + "         " + getWordLengthCount()[i] + "\n";
		}

		result += "\nAverage word length = " + averageWordLength + "\n" +
				"==========================================================";

		return result;
	}
}
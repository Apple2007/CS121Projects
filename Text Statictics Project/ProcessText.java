/**
 * Text Statistics Project - CS121
 *
 * ProcessText.java is the user interface side of the project, allowing a user to input files as arguments in the command line, and passing that input to TextStatisatics.java.
 * "Statistics are everything. Without them, we couldn't figure out stuff." - Me
 *
 * @author Jack Shorenstein
 */

import java.io.File;

public class ProcessText {
	public static void main(String[] args) {
		/**
		 * If no file is given, show the user how to do it
		 */
		if (args.length < 1) {
			System.err.println("Usage: java ProcessText file1 [file2 ...]");
			System.exit(1);
		}

		/**
		 * If multiple files are given, process them all!
		 */
		int numProcessedFiles = 0;
		while (args.length > numProcessedFiles) {
			File filePath = new File(args[numProcessedFiles]);

			/**
			 * If file doesn't exist, tell the user
			 */
			if (!filePath.exists() || !filePath.isFile()) {
				System.err.println("Invalid file path: " + args[numProcessedFiles]);
				numProcessedFiles++;
				continue;
			}

			/**
			 * Pass file to TextStatistics.java
			 */
			TextStatistics fileProcess = new TextStatistics(filePath);
			System.out.println(fileProcess);
			numProcessedFiles++;
		}
	}
}
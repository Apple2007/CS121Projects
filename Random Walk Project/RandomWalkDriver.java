/**
 * Random Walk Project - Boise State CS121
 *
 * RandomWalkDriver.java is the actual part of the program that the users will interact with.
 * "It's time to RANDOMPlay!" - Me (and possibly the Zenless Zone Zero team... lol)
 *
 * @author Jack Shorenstein
 */

import java.util.Scanner;

public class RandomWalkDriver {
	/**
	 * Takes user input for a grid size and seed and creates a RandomWalk object and calls the createWalk() method. It then returns the walk as a String.
	 * @param args  gridSize and seed
	 */
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		RandomWalk walker;
		int gridSize;
		long seed;

		/*
		 * User input for the grid size, but it will loop until a positive integer is entered
		 */
		while (true) {
			System.out.print("Please enter the grid size: ");
			gridSize = input.nextInt();

			if (gridSize > 0) {
				break;
			} else {
				System.err.println("Error: Grid size must be positive!");
			}
		}

		/*
		 * User input for the seed, but it will loop until a positive integer or 0 is entered
		 */
		while (true) {
			System.out.print("Enter random seed (0 for no seed): ");
			seed = input.nextLong();

			if (seed < 0) {
				System.err.println("Error: seed must be >= 0!");
			} else if (seed == 0 || seed > 0) {
				break;
			}
		}

		/*
		 * If seed is 0, the RandomWalk object is created without a seed. Otherwise, it is created with a seed. The path is then generared and printed in a [x, y] format
		 */
		if (seed > 0) {
			walker = new RandomWalk(gridSize, seed);
		} else {
			walker = new RandomWalk(gridSize);
		}
		walker.createWalk();
		System.out.println(walker.toString());
	}
}

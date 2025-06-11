/**
 * Random Walk Project - Boise State CS121
 *
 * RandomWalk.java is the main file for the Random Walk Project, having all of the logic behind the program.
 * "Randomness is key..." - Me (because I say so!)
 *
 * @author Jack Shorenstein
 */

import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

public class RandomWalk implements RandomWalkInterface {

	/*
	 * Instance Variables for RandomWalk
	 */
	private boolean done = false;
	private ArrayList<Point> path;
	private Point startPosition;
	private Point endPosition;
	private Point currentPosition;
	private int gridSize;
	Random randomNumGenerator;

	/**
	 * When run, the step() method generates a random next position on the grid, unless the boolean done is true,
	 * or if the east/west/north value is at its maximum
	 */
	public void step() {
		// Checks if done or not
		if (done) {
			return;
		}
		
		// Checks to see if the current position is the end position
		if (currentPosition.x == gridSize - 1 && currentPosition.y == 0) {
			done = true;
			return;
		}
		
		// Creates new point
		Point newPoint;
		int direction = randomNumGenerator.nextInt(2);
		
		// Checks to see which direction to go, and then assigns the new point
		if (currentPosition.x == gridSize - 1) {
			newPoint = new Point(currentPosition.x, currentPosition.y - 1);
		} else if (currentPosition.y == 0) {
			newPoint = new Point(currentPosition.x + 1, currentPosition.y);
		} else {
			if (direction == 0) {
				newPoint = new Point(currentPosition.x + 1, currentPosition.y);
			} else {
				newPoint = new Point(currentPosition.x, currentPosition.y - 1);
			}
		}
		
		// Adds the point to the path ArrayList
		currentPosition = new Point(newPoint.x, newPoint.y);
		path.add(new Point(currentPosition.x, currentPosition.y));
	}

	/**
	 * Creates the full walk by using the step() method internally
	 */
	public void createWalk() {
        // Starts the walk
		currentPosition = new Point(startPosition.x, startPosition.y);
        path.clear();
        path.add(new Point(currentPosition.x, currentPosition.y));
        done = false;
        
        // Won't stop walking until done
		while (!isDone()) {
            step();
        }
	}

	/**
	 * Returns the boolean done
	 * @return if program is done or not
	 */
	public boolean isDone() {
		return done;
	}

	/**
	 * Returns the grid size from the int gridSize
	 * @return the grid size
	 */
	public int getGridSize() {
		return gridSize;
	}

	/**
	 * Returns the starting point on the grid from the Point startPosition
	 * @return starting point on the grid
	 */
	public Point getStartPoint() {
		return startPosition;
	}

	/**
	 * Returns the end point on the grid from the Point endPosition
	 * @return the ending point on the grid
	 */
	public Point getEndPoint() {
		return endPosition;
	}

	/**
	 * Returns the current point on the grid from the Point currentPossition
	 * @return the current position on the grid
	 */
	public Point getCurrentPoint() {
		return currentPosition;
	}

	/**
	 * Returns the full path from the ArrayList path.
	 * @return the full path on the grid
	 */
	public ArrayList<Point> getPath() {
		ArrayList<Point> pathCopy = new ArrayList<Point>(this.path);
		return pathCopy;
	}
	
	// - - - - - - - - - - - - - - - - - - - - - -

	/*
	 * These methods are not in use (not doing the extra credit methods)
	 */
	
	public void stepEC() {
		// Not in use
	}

	public void createWalkEC() {
		// Not in use
	}

	// - - - - - - - - - - - - - - - - - - - - - -

	/**
	 * Sets the grid size specified by the user, generates a random number generator based on a random seed, and sets the starting and ending positions along with the path.
	 * @param gridSize  the side of the grid
	 */
	public RandomWalk(int gridSize) {
		this.gridSize = gridSize;
		startPosition = new Point(0, gridSize - 1);
		endPosition = new Point(gridSize - 1, 0);
		currentPosition = new Point(startPosition.x, startPosition.y);
		path = new ArrayList<Point>();
		path.add(new Point(currentPosition.x, currentPosition.y));
		randomNumGenerator = new Random();
	}

	/**
	 * Sets the grid size specified by the user, generates a random number generator based on a user-specified seed, and sets the starting and ending points along with the path.
	 * @param gridSize  the size of the grid
	 * @param seed  the user-specified seed value
	 */
	public RandomWalk(int gridSize, long seed) {
		this.gridSize = gridSize;
		startPosition = new Point(0, gridSize - 1);
		endPosition = new Point(gridSize - 1, 0);
		currentPosition = new Point(startPosition.x, startPosition.y);
		path = new ArrayList<Point>();
		path.add(new Point(currentPosition.x, currentPosition.y));
		randomNumGenerator = new Random(seed);
	}

	/**
	 * Returns the path as a string in the form [x1,y1] [x2,y2] [x3,y3] ...
	 * @return result  the string representation of the path ArrayList
	 */
	public String toString() {
		String result = "";
		
		// Creates the format for the string
		for (int i = 0; i < path.size(); i++) {
			result += "[" + path.get(i).x + "," + path.get(i).y + "]";
			if (i < path.size() - 1) {
				result += " ";
			}
		}
		return result;
	}
}

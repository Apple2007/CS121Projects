/**
 * CS 121 Project 0: Traffic Animation
 *
 * Animates a car moving with someone standing on the sidewalk. Sounds cool, right? Well, it was terrible to code. Enjoy ;(
 *
 * @author BSU CS 121 Instructors
 * @author Jack Shorenstein
 */

// Imports libraries needed for this project
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

@SuppressWarnings("serial")

public class TrafficAnimation extends JPanel {
	
	// Naming some extra constants
	private final Color ROAD_COLOR = new Color(239, 255, 70);
	Font TEXT_FONT = new Font("Segoe UI", Font.BOLD, 19);
	private final Color CAR_BODY_COLOR = new Color(228, 157, 28);
	private final Color BROWN_BENCH_COLOR = new Color(187, 128, 45);
	private final Color BROWN_LEG_COLOR = new Color(141, 96, 32);
	private final String TEXT_ON_SCREEN = "Hello Boss? Why is the driverless car doing this?!";
	
	// Refresh rate setup (DON'T CHANGE! says BSU)
	private final int DELAY = 100;

	// xOffset (needed for movement)
	private int xOffset = 0;

	// Step size for xOffset variable
	private int stepSize = 10;

	// Background color setup
	private final Color BACKGROUND_COLOR = new Color(83, 255, 109);

	// Drawing objects to the screen!
	public void paintComponent(Graphics g) {
		
		// Width and Height of Java window <---- BSU Code
		int width = getWidth();
		int height = getHeight();

		// Set the background color <---- BSU Code
		g.setColor(BACKGROUND_COLOR);
		g.fillRect(0, 0, width, height);

		// xOffset calculations <---- BSU Code
		xOffset  = (xOffset + stepSize) % width;

		// Draws sidewalk one
		int sidewalkH = height;
		int sidewalkW = width / 10;
		double sidewalkX = width * 0.1;
		int sidewalkY = 0;
		
		g.setColor(Color.gray);
		g.fillRect((int) sidewalkX, sidewalkY, sidewalkW, sidewalkH);
		
		// Draws sidewalk two
		int sidewalkTwoH = height;
		int sidewalkTwoW = width / 10;
		double sidewalkTwoX = width * 0.8;
		int sidewalkTwoY = 0;
		
		g.setColor(Color.gray);
		g.fillRect((int) sidewalkTwoX, sidewalkTwoY, sidewalkTwoW, sidewalkTwoH);
		
		// Draw the yellow road
		int roadH = height / 3;
		int roadW = width; 
		int roadX = 0;
		int roadY = height / 5;
		
		g.setColor(ROAD_COLOR);
		g.fillRect(roadX, roadY, roadW, roadH);
		
		// Draw some text (and center it using FontMetrics)
		FontMetrics metrics = g.getFontMetrics(TEXT_FONT);
		int textWidth = metrics.stringWidth(TEXT_ON_SCREEN);
		double textX = (width - textWidth) / 2;
		double textY = height * 0.1;
		
		g.setFont(TEXT_FONT);
		g.setColor(Color.blue);
		g.drawString(TEXT_ON_SCREEN, (int) textX, (int) textY);
		
		// Car Body draw
		int carBodyH = height / 7;
		int carBodyW = width / 5; 
		int carBodyX = xOffset;
		double carBodyY = roadY + (roadH / 2) - (carBodyH / 3);
		
		g.setColor(CAR_BODY_COLOR);
		g.fillRect(carBodyX, (int) carBodyY, carBodyW, carBodyH);
		
		// Draw Car Window (it doesn't look like a window, but whatever)
		int carWindowH = height / 15;
		int carWindowW = carWindowH;
		double carWindowX = carBodyX + (carBodyW / 1.3) - carWindowW / 2;
		double carWindowY = carBodyY + (carBodyH / 3) - (carWindowH / 2);
		
		g.setColor(Color.white);
		g.fillRect((int) carWindowX, (int) carWindowY, carWindowW, carWindowH);
				
		// Draw Wheels (one and two)
		int wheelsH = height / 12;
		int wheelsW = wheelsH;
		double wheelOneX = carBodyX + (carBodyW / 1.3) - wheelsW / 2;
		double wheelOneY = carBodyY + carBodyH - wheelsH / 2;
		double wheelTwoX = (carBodyX + carBodyW / 6) - wheelsW / 2;
		double wheelTwoY = carBodyY + carBodyH - wheelsH / 2;
		
		g.fillOval((int) wheelOneX, (int) wheelOneY, wheelsH, wheelsW);
		g.fillOval((int) wheelTwoX, (int) wheelTwoY, wheelsH, wheelsW);
		
		// Draws the character
        int stickFigureH = height / 5;
        int stickFigureW = sidewalkW / 2;
        int stickFigureX = (int) (sidewalkX + (sidewalkW - stickFigureW) / 2);
        double stickFigureY = (height - stickFigureH) / 1.4;

        int headDiameter = stickFigureW / 1;
        int headX = stickFigureX + (stickFigureW - headDiameter) / 2;
        int headY = (int) stickFigureY;

        g.fillOval(headX, headY, headDiameter, headDiameter);

        int bodyY = headY + headDiameter;
        int bodyHeight = stickFigureH / 2;

        g.drawLine(headX + headDiameter / 2, bodyY, headX + headDiameter / 2, bodyY + bodyHeight);
        
        int armY = bodyY + bodyHeight / 3;

        g.drawLine(headX + headDiameter / 2, armY, headX, armY);
        g.drawLine(headX + headDiameter / 2, armY, headX + headDiameter, armY);
        
        int legY = bodyY + bodyHeight;
        
        g.drawLine(headX + headDiameter / 2, legY, headX, legY + bodyHeight);
        g.drawLine(headX + headDiameter / 2, legY, headX + headDiameter, legY + bodyHeight);
        
        // Draws the eyes and mouth for the character
        int eyesW = stickFigureW / 3;
        double eyesH = stickFigureW * 0.1;
        int startAngle = 0;
        int arcAngle = 300;
        double eyesX = (headX + (headX * 0.1)) - eyesW / 2;
        double eyesY = headY + (headY * 0.04);
        double eyesTwoX = (headX + (headX * 0.25)) - eyesW / 2;
        double eyesTwoY = headY + (headY * 0.04);
        
        g.setColor(Color.black);
        g.fillArc((int) eyesX, (int) eyesY, eyesW, (int) eyesH, startAngle, arcAngle);
        g.drawArc((int) eyesTwoX, (int) eyesTwoY, eyesW, (int) eyesH, startAngle, arcAngle);
        
        int mouthW = stickFigureW / 2;
        double mouthH = stickFigureW * 0.2;
        int startAngleMouth = 0;
        int arcAngleMouth = 150;
        double mouthX = (headX + (headX * 0.12)) - eyesW / 2;
        double mouthY = headY + (headY * 0.08);
        g.drawArc((int) mouthX, (int) mouthY, mouthW, (int) mouthH, startAngleMouth, arcAngleMouth);
        
        // Draw bench and legs
        int benchW = width / 3;
        int benchH = height / 12;
        double benchX = width * 0.35;
        double benchY = height * 0.6;
        int legsW = benchW / 9;
        double legsH = benchH - (benchH * 0.6);
        double legOneX = benchX;
        double legBenchY = benchY + (benchY * 0.12);
        double legTwoX = benchX + (benchX * 0.845);
        
        g.setColor(BROWN_BENCH_COLOR);
        g.fillRect((int) benchX, (int) benchY, benchW, benchH);
        g.setColor(BROWN_LEG_COLOR);
        g.fillRect((int) legOneX, (int) legBenchY, legsW, (int) legsH);
        g.fillRect((int) legTwoX, (int) legBenchY, legsW, (int) legsH);
        
        
		// "Put your code above this line. This makes the drawing smoother." <--- This comment is also from BSU!
		Toolkit.getDefaultToolkit().sync();
	}

	// EVERYTHING BELOW HERE IS DONE BY THE CS121 PEOPLE OVER AT BOISE STATE UNIVERITY! I REPEAT... NOT MY CODE BELOW! (EXCEPT FOR THE DIMENTIONS OF THE SCREEN... SET TO 800x600) ;)
	
	public static void main (String[] args)
	{
		// DO NOT MODIFY THIS CODE.
		JFrame frame = new JFrame ("Traffic Animation");
		frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(new TrafficAnimation());
		frame.pack();
		frame.setVisible(true);
	}

	public TrafficAnimation()
	{
		// Do not initialize larger than 800x600. I won't be able to
		// grade your project if you do.
		int initWidth = 800;
		int initHeight = 600;
		setPreferredSize(new Dimension(initWidth, initHeight));
		this.setDoubleBuffered(true);

		//Start the animation - DO NOT REMOVE
		startAnimation();
	}

	private void startAnimation()
	{
		ActionListener timerListener = new TimerListener();
		Timer timer = new Timer(DELAY, timerListener);
		timer.start();
	}

	private class TimerListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) {
			repaint();
		}
	}
}
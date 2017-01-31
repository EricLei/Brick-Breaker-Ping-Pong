//package com.edu4java.minitennis1;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Game2 extends JPanel {

	
	Ball ball = new Ball(this);
	Racquet racquet = new Racquet(this);
	ArrayList<Brick> bricks; 
	int score = 0;
	
	private int getScore() {
		return score;
	}
	
	public Game2() {
		
		bricks = new ArrayList<Brick>();
		bricks.add(new Brick(this, 250, 250));
		bricks.add(new Brick(this, 250, 100));
		
		addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) {
			}
			
			public void keyReleased(KeyEvent e) {
				racquet.keyReleased(e);
			}
			
			public void keyPressed(KeyEvent e) {
				racquet.keyPressed(e);
			}
		});
		setFocusable(true);
	}
	
	private void moveBall() {
		ball.move();
		racquet.move();
	}	
	
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		ball.paint(g2d);
		racquet.paint(g2d);
		for(int i = 0; i < bricks.size(); i++) {
			bricks.get(i).paint(g2d);
		}

		g2d.setColor(Color.GRAY);
		g2d.setFont(new Font("Verdana", Font.BOLD, 30));
		g2d.drawString(String.valueOf(getScore()), 10, 30);
	}	
	
	public void gameOver() {
		JOptionPane.showMessageDialog(this, "your score is: " + getScore(), "Game Over", JOptionPane.YES_NO_OPTION);
		System.exit(ABORT);
	}
	
	public static void main(String[] args) throws InterruptedException {
		JFrame frame = new JFrame("Mini Tennis");
		Game2 game = new Game2();
		frame.add(game);
		frame.setSize(750, 750);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		while(true) {
			game.moveBall();
			game.repaint();
			Thread.sleep(10);
		}
	}
}
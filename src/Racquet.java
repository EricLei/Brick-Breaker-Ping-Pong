import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

public class Racquet {
	
	private static final int Y = 650;
	private static final int WIDTH = 60;
	private static final int HEIGHT = 10;
	double xPos = 300;
	double xSpeed = 0;
	double xAccel = 0;
	boolean keyReleased = true;
	private Game2 game;

	public Racquet(Game2 game) {
		this.game= game;
	}

	public void move() {
		xSpeed = xSpeed + xAccel;
		if(xSpeed > 0) {
			xSpeed = Math.min(5,xSpeed); // Maximum speed
			if(keyReleased) {
				xSpeed = xSpeed - 0.3; // Deceleration
				if(Math.abs(xSpeed) <= 0.2) {
					xSpeed = 0;
					xAccel = 0; // Makes the racquet stop if it is close enough to zero
				}
			}
		}
		if(xSpeed < 0) {
			xSpeed = Math.max(-5, xSpeed); // Minimum speed
			if(keyReleased) {
				xSpeed = xSpeed + 0.3; // Deceleration
				if(Math.abs(xSpeed) <= 0.2) {
					xSpeed = 0;
					xAccel = 0; // Makes the racquet stop if it is close enough to zero
				}
			}
		}
		
		if (xPos + xSpeed > 0 && xPos + xSpeed < game.getWidth()-60)
			xPos = xPos + xSpeed;
		
	}

	public void paint(Graphics2D g) {
		int xPosInt = (int)xPos;
		g.fillRect(xPosInt, Y, 60, 10);
	}

	public void keyReleased(KeyEvent e) {
		keyReleased = true;
		xAccel = 0;
	}

	public void keyPressed(KeyEvent e) {
		keyReleased = false;
		if (e.getKeyCode() == KeyEvent.VK_LEFT)
			xAccel = -0.3;
			if(xSpeed > -2 && xAccel == -0.3) {
				xSpeed = -2; // Starts out at 2 speed when switching directions
			}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT)
			xAccel = 0.3;
			if(xSpeed < 2 && xAccel == 0.3) {
				xSpeed = 2; // Starts out at 2 speed when switching directions
			}
	}

	public Rectangle getBounds() {
		int xPosInt = (int)xPos;
		return new Rectangle(xPosInt, Y, WIDTH, HEIGHT);
	}

	public int getTopY() {
		return Y;
	}
	
	public double getSpeed() {
		return xSpeed;
	}
}
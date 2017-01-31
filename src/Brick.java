import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

public class Brick {
	
	int xPos;
	int yPos;
	boolean visible;
	private Game2 game;

	public Brick (Game2 game, int x, int y) {
		xPos = x;
		yPos = y;
		this.game = game;
		visible = true;
	}
	
	void move() { //checks if the ball hit the brick on the sides or top or bottom
		/*if(collision()) {
			if(Math.abs(xPos-game.ball.x)<=1) {
				game.ball.xa = -game.ball.xa;
			} else if(Math.abs(yPos-game.ball.ya)<=1) {
				game.ball.ya = -game.ball.ya;
			}
			
			game.bricks.remove(this);
			game.score += 5;
			
		}*/
	}

	public void paint(Graphics2D g) {
		g.fillRect(xPos, yPos, 40, 15);
	}

	public boolean collision() {
		return game.ball.getBounds().intersects(getBounds());
	}
	
	public Rectangle getBounds() {
		return new Rectangle(xPos, yPos, 40, 15);
	}
}

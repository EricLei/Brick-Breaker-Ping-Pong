import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Color;

public class Ball {

	private static final int DIAMETER = 25;
	double x = 0;
	int y = 0;
	double xa = 2;
	int ya = 2;
	Brick tempBrick;
	private Game2 game;
	
	public Ball(Game2 game) {
		this.game = game;
	}
	
	void move() {
		if (x + xa < 0)
			xa = -xa;
		if (x + xa > game.getWidth() - 30)
			xa = -xa;
		if (y + ya < 0)
			ya = 1;
		if (y + ya > game.getHeight() - 30)
			game.gameOver();
		if (collision()){
			ya = -1;
			xa = xa - game.racquet.xSpeed/3;
			y = game.racquet.getTopY() - DIAMETER;
		}
		if (brickCollision()) {
			if(Math.abs(tempBrick.xPos - this.x)<=1) {
				game.ball.xa = -game.ball.xa;
			} else if(Math.abs(tempBrick.yPos-this.y)<=1) {
				game.ball.ya = -game.ball.ya;
			}
			tempBrick.visible = false;
			game.bricks.remove(tempBrick);
			game.score += 5;
		}
		
		x = x + xa;
		y = y + ya;
	}

	private boolean collision() {
		return game.racquet.getBounds().intersects(getBounds());
	}
	
	private boolean brickCollision() {
		for(int i = 0; i < game.bricks.size(); i++) {
			Brick x = game.bricks.get(i);
			if(x.getBounds().intersects(getBounds()) && x.visible == true) {
				tempBrick = x;
				return true;
			}
		}
		return false;
	}
	
	public void paint(Graphics g) {
		g.setColor(Color.RED);
		g.fillOval((int)x, y, DIAMETER, DIAMETER);
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int)x, y, DIAMETER, DIAMETER);
	}
}

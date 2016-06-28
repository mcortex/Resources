package com.edu4java.minitennis6;

import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Ball implements Sprite {
	private static final int DIAMETER = 30;
	private int posX;
	private int posY;
	private int deltaX;
	private int deltaY;
	private Game game; // Obtiene los limites del JPanel (lienzo)

	public Ball(Game game) {
		this.posX=0;
		this.posY=0;
		this.deltaX = 1;
		this.deltaY = 1;
		this.game= game;
	}

	/* (non-Javadoc)
	 * @see com.edu4java.minitennis6.Sprite#move()
	 */
	@Override
	public void move() {
		if (posX + deltaX < 0)
			deltaX = 1;
		if (posX + deltaX > game.getWidth() - DIAMETER)
			deltaX = -1;
		if (posY + deltaY < 0)
			deltaY = 1;
		if (posY + deltaY > game.getHeight() - DIAMETER)
			game.gameOver();
		if (collision()){
			deltaY = -1;
			posY = game.racket.getTopY() - DIAMETER;
		}
		posX = posX + deltaX;
		posY = posY + deltaY;
	}

	private boolean collision() {
		return game.racket.getBounds().intersects(getBounds());
	}

	/* (non-Javadoc)
	 * @see com.edu4java.minitennis6.Sprite#paint(java.awt.Graphics2D)
	 */
	@Override
	public void paint(Graphics2D g) {
		g.fillOval(posX, posY, DIAMETER, DIAMETER);
	}
	
	/* (non-Javadoc)
	 * @see com.edu4java.minitennis6.Sprite#getBounds()
	 */
	@Override
	public Rectangle getBounds() {
		return new Rectangle(posX, posY, DIAMETER-5, DIAMETER);

		//return new Circle(x, y, DIAMETER, DIAMETER);
	}
}
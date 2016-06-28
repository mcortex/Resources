package com.edu4java.minitennis6;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

public class Racket implements Sprite {
	private static final int Y = 330; // La posicion Y es fija para la raqueta
	private static final int WIDTH = 60;
	private static final int HEIGHT = 10;
	private int posX;
	private int posY;
	private int deltaX;
	private int deltaY;
	private Game game; // Obtiene los limites del JPanel (lienzo)

	public Racket(Game game) {
		this.posX=0;
		this.posY=Y;
		this.deltaX = 0;
		this.deltaY = 0;
		this.game = game;
	}

	public void move() {
		if (posX + deltaX > 0 && posX + deltaX < game.getWidth() - WIDTH)
			posX = posX + deltaX;
	}

	public void paint(Graphics2D g) {
		g.fillRect(posX, posY, WIDTH, HEIGHT);
	}

	public void keyReleased(KeyEvent e) {
		deltaX = 0;
	}

	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_LEFT)
			deltaX = -1;
		if (e.getKeyCode() == KeyEvent.VK_RIGHT)
			deltaX = 1;
	}

	public Rectangle getBounds() {
		return new Rectangle(posX, posY, WIDTH, HEIGHT);
	}

	public int getTopY() {
		return Y - HEIGHT;
	}
}
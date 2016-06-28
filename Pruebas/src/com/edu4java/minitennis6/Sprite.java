package com.edu4java.minitennis6;

import java.awt.Graphics2D;
import java.awt.Rectangle;

public interface Sprite {

	void move();

	void paint(Graphics2D g);

	Rectangle getBounds();

}
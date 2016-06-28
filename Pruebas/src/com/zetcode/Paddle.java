package com.zetcode;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;

public class Paddle extends Sprite implements Commons {

    private int dx;

    public Paddle() {

//        ImageIcon ii = new ImageIcon("paddle.png");
//        image = ii.getImage();
//
//        i_width = image.getWidth(null);
//        i_heigth = image.getHeight(null);
    	
    	this.i_width = 40;
        this.i_heigth = 10;

        resetState();
    }
    


    public void move() {

        x += dx;

        if (x <= 0) {
            x = 0;
        }

        if (x >= WIDTH - i_width) {
            x = WIDTH - i_width;
        }
    }
    
    public void paint(Graphics2D g) {
		g.fillRect(x, y, i_width, i_heigth); // CREAMOS UNA BARRA CON EL TAMAÑO DE LA RAQUETA
	}

    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            dx = -1;
        }

        if (key == KeyEvent.VK_RIGHT) {
            dx = 1;
        }
    }

    public void keyReleased(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            dx = 0;
        }

        if (key == KeyEvent.VK_RIGHT) {
            dx = 0;
        }
    }

    private void resetState() {

        x = INIT_PADDLE_X;
        y = INIT_PADDLE_Y;
    }
}
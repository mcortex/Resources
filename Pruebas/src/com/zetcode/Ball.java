package com.zetcode;

import java.awt.Graphics2D;

import javax.swing.ImageIcon;

public class Ball extends Sprite implements Commons {

    private int xdir;
    private int ydir;

    public Ball() {

        this.xdir = 1;
        this.ydir = -1;

//        ImageIcon ii = new ImageIcon("ball.png");
//        image = ii.getImage();
//
//        i_width = image.getWidth(null);
//        i_heigth = image.getHeight(null);
        
        this.i_width = 10;
        this.i_heigth = 10;

        resetState();
    }

    public void move() {
        
        x += xdir;
        y += ydir;

        if (x == 0) {
            setXDir(1);
        }

        if (x == WIDTH - i_width) {
            setXDir(-1);
        }

        if (y == 0) {
            setYDir(1);
        }
    }
    
    public void paint(Graphics2D g) {
		g.fillOval(x, y, i_width, i_heigth); // CREAMOS UNA BARRA CON EL TAMAÑO DE LA RAQUETA
	}

    private void resetState() {
        
        x = INIT_BALL_X;
        y = INIT_BALL_Y;
    }

    public void setXDir(int x) {
        xdir = x;
    }

    public void setYDir(int y) {
        ydir = y;
    }

    public int getYDir() {
        return ydir;
    }
}
package com.zetcode;

import java.awt.Graphics2D;

import javax.swing.ImageIcon;

public class Brick extends Sprite {

    private boolean destroyed;

    public Brick(int x, int y) {
        
        this.x = x;
        this.y = y;

//        ImageIcon ii = new ImageIcon("brick.png");
//        image = ii.getImage();
//
//        i_width = image.getWidth(null);
//        i_heigth = image.getHeight(null);
        
        this.i_width = 35;
        this.i_heigth = 9;
        
        this.destroyed = false;
    }

    public void paint(Graphics2D g) {
		g.fillRect(x, y, i_width, i_heigth); // CREAMOS UNA BARRA CON EL TAMA�O DE LA RAQUETA
	}
    
    public boolean isDestroyed() {
        
        return destroyed;
    }
    

    public void setDestroyed(boolean val) {
        
        destroyed = val;
    }
}
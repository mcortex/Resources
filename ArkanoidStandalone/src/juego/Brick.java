package juego;

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
        
        this.width = 35;
        this.heigth = 9;
        
        this.destroyed = false;
    }

    public void paint(Graphics2D g) {
		g.fillRect(x, y, width, heigth); // CREAMOS UNA BARRA CON EL TAMAÑO DE LA RAQUETA
	}
    
    public boolean isDestroyed() {
        
        return destroyed;
    }
    

    public void setDestroyed(boolean val) {
        
        destroyed = val;
    }
}
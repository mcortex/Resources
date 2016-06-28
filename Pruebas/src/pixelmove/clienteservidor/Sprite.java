package pixelmove.clienteservidor;

import java.awt.Graphics2D;
//import java.awt.Image;
//import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
//import javax.swing.ImageIcon;

public class Sprite {

    private int dx;
    private int dy;
    private int x;
    private int y;
//    private Image image;

    public Sprite() {
        
        initCraft();
		
    }
    
    
    private void initCraft() {
       
    	x = 40;
        y = 60;
        
//        ImageIcon ii = new ImageIcon("craft.png");
//        image = ii.getImage();       
    }


    public void move() {        
        this.x+=this.dx;
        this.y+=this.dy;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    
    public int getDX() {
        return dx;
    }

    public int getDY() {
        return dy;
    }
    
    public void setDX(int dx) {
        this.dx=dx;
    }

    public void setDY(int dy) {
        this.dy=dy;
    }

//    public Image getImage() {
//        return image;
//    }

    
    public void paint(Graphics2D g) {
		g.fillOval(this.x, this.y, 20, 20); // CREAMOS UNA BARRA CON EL TAMAÑO DE LA RAQUETA
	}
    
    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            dx = -1;
        }

        if (key == KeyEvent.VK_RIGHT) {
            dx = 1;
        }

        if (key == KeyEvent.VK_UP) {
            dy = -1;
        }

        if (key == KeyEvent.VK_DOWN) {
            dy = 1;
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

        if (key == KeyEvent.VK_UP) {
            dy = 0;
        }

        if (key == KeyEvent.VK_DOWN) {
            dy = 0;
        }
    }
}
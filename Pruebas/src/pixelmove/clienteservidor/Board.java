package pixelmove.clienteservidor;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
//import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JPanel;
import javax.swing.Timer;


public class Board extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	
	private Timer timer;
    private Sprite pixel;
    private final int DELAY = 5;

    public Board() {

        initBoard();
    }
    
    private void initBoard() {
        
        addKeyListener(new TAdapter());
        setFocusable(true);
        setBackground(Color.WHITE);

        this.pixel = new Sprite();

        this.timer = new Timer(DELAY, this);
        this.timer.start();        
    }


    @Override
    public void paintComponent(Graphics g) {
//        super.paintComponent(g);
//        doDrawing(g);
//        Toolkit.getDefaultToolkit().sync();
    	
		super.paintComponent(g);
		doDrawing(g);
		
		
    }

    private void doDrawing(Graphics g) {
        
//        Graphics2D g2d = (Graphics2D) g;
//        g2d.drawImage(craft.getImage(), craft.getX(), craft.getY(), this);
    	
    	Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		this.pixel.paint(g2d);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    	if (this.pixel.getX() + this.pixel.getDX() > 0 && this.pixel.getX() + this.pixel.getDX() < getWidth() - 10 && this.pixel.getY() + this.pixel.getDY() > 0 && this.pixel.getY() + this.pixel.getDY() < getHeight() - 10)
    	{
    	pixel.move();
        repaint();}  
    }

    private class TAdapter extends KeyAdapter {

        @Override
        public void keyReleased(KeyEvent e) {
            pixel.keyReleased(e);
        }

        @Override
        public void keyPressed(KeyEvent e) {
            pixel.keyPressed(e);
        }
    }
}
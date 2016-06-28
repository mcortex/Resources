package juego;

import java.awt.EventQueue;
import javax.swing.JFrame;

public class GameFrame extends JFrame {

    public GameFrame() {
        
        initUI();
    }
    
    private void initUI() {
        
        add(new Board());
        setTitle("Arkanoid");
        
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(Commons.WIDTH, Commons.HEIGTH);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

    public static void main(String[] args) {
        
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {                
                GameFrame game = new GameFrame();
                game.setVisible(true);                
            }
        });
    }
}
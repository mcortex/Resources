package addnotify.test;

import java.awt.BorderLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AddNotifyTest extends JFrame {
    public AddNotifyTest() {
//        System.out.println("Contructing.....");
        JPanel p1 = new JPanel() {
            @Override
            public void addNotify() {
                System.out.println("p1....");
                super.addNotify();
            }
        };
        
        JPanel p2 = new JPanel() {
            @Override
            public void addNotify() {
                System.out.println("p2....");
                super.addNotify();
            }
        };        
        
        JTextField tf = new JTextField (20) {
            @Override
            public void addNotify() {
                System.out.println("tf....");
                super.addNotify();
            }
        };
        
        p1.setLayout(new BorderLayout());
        p2.setLayout(new BorderLayout());
        p1.add(tf);
        p2.add(p1);
        setContentPane(p2);
        
        tf.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e){
                if (e.getKeyCode() == KeyEvent.VK_F1){
                    System.out.println("F1 pressed....");
                }
            }
        });
        
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(300, 200);
    }
    
    @Override
    public void addNotify() {
        System.out.println("JFrame....");
        super.addNotify();
    }

    public static void main(String[] args){
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                AddNotifyTest at = new AddNotifyTest();
                at.setVisible(true);
            }
        });
    }

}
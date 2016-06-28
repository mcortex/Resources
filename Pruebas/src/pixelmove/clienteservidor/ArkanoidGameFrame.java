package pixelmove.clienteservidor;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class ArkanoidGameFrame extends JFrame
{
    AnimationPanel panel;
    JMenuBar menuBar;
    JMenu gameMenu;
    JMenuItem playItem;
    
    GameBall ball;
    GamePad padTop, padBottom;
    AnimationThread animThread;
    
    final static int SLOW = 9;
    static final int NORMAL = 5;//
    static final int FAST = 3;
    
    ArrayBlockingQueue<Integer> q;
    ServerSocket serverSock;
    Socket sock;
    boolean isServer;
    int speed;
    
    SenderThread sendr;
    RecvrThread recvr;
    
    public ArkanoidGameFrame() throws IOException
    {
        this.setTitle("Arkanoid-Network - Kapil Zad");
    
        //this.setSize(700, 600);
        this.setSize(700, 652);
        this.setResizable(false);        

        q = new ArrayBlockingQueue<Integer>(100);
        
        panel = new AnimationPanel();
        panel.setBackground(Color.WHITE);
        
        this.speed = NORMAL;
        
        this.padTop = new GamePad(0, 300, 20, 400, Color.ORANGE, this);
        this.padBottom = new GamePad(580, 300, 600, 400, Color.ORANGE, this);        

        this.menuBar = new JMenuBar();        
        this.gameMenu = new JMenu("Game");
        this.gameMenu.setEnabled(false);
        this.menuBar.add(gameMenu);        
        this.playItem = new JMenuItem("Play");
        this.gameMenu.add(playItem);

        this.playItem.addActionListener(new ActionListener() {            
            @Override
            public void actionPerformed(ActionEvent e)
            {
                newGame();
                q.add(999999);
            }
        });
        this.setJMenuBar(menuBar);
        
        getContentPane().add(panel, BorderLayout.CENTER);
        
        panel.add(padTop);
        panel.add(padBottom);
        
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) 
            {                
                int keyCode = e.getKeyCode();
                
                if(isServer)
                {
                    switch(keyCode)
                    {
                    case 39:
                        padBottom.moveRight();
                        q.add(keyCode);
                        break;
                    case 37:
                        padBottom.moveLeft();
                        q.add(keyCode);
                        break;
                    }
                }
                else
                {
                    switch(keyCode)
                    {
                    case 68:
                        padTop.moveRight();
                        q.add(keyCode);
                        break;
                    case 65:
                        padTop.moveLeft();
                        q.add(keyCode);
                        break;
                    }
                }
            }
        });
        
        Thread t = new Thread(){
            public void run()
            {
                try
                {
                    setConnection();
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                    System.exit(1);
                }
            }
        };
        
        this.addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent we)
            {
                try
                {
                    if(serverSock != null)
                        serverSock.close();
                    if(sock != null)
                        sock.close();
                } 
                catch (IOException e)
                {                    
                }
                finally
                {
                    System.exit(1);
                }
            }
        });
        
        this.setVisible(true);
        t.start();
    }
    
    protected void newGame()
    {
        panel.remove(ball);
        Random r = new Random();
        //ball = new GameBall(new Point(r.nextInt(650)+10, 100), Color.RED, 1, 1, 10, panel, padTop, padBottom);
        ball = new GameBall(new Point(50, 100), Color.RED, 1, 1, 10, panel, padTop, padBottom);
        panel.add(ball);        
        animThread = new AnimationThread(ball, speed, "GameThread");        
    }
    
    private void setConnection() throws IOException
    {        
        if(JOptionPane.showConfirmDialog(null, "Start as server?", "Arkanoid - Network", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION)
        {
            this.isServer = true;
            JOptionPane.showMessageDialog(this, "Server will listen on port: "+3001, "Arkanoid - Network", JOptionPane.INFORMATION_MESSAGE);
            this.serverSock = new ServerSocket(3001, 5);            
            
            JOptionPane.showMessageDialog(this, "Press OK to wait for connection from player2.", "Arkanoid - Network", JOptionPane.INFORMATION_MESSAGE);
            this.setTitle(this.getTitle()+" - listening on:"+3001+" .. waiting for connection from player2 ...");
            sock = serverSock.accept();
            
            this.sendr = new SenderThread(sock, q);
            this.recvr = new RecvrThread(sock, padTop, this);    
        }
        else
        {
            this.isServer = false;
            String input = JOptionPane.showInputDialog(null, "Enter server address:port ", "Arkanoid - Network", JOptionPane.INFORMATION_MESSAGE);
            if (input == null)
            {                
                System.exit(1);
            }
            String addr = input.substring(0, input.indexOf(':'));
            int port = Integer.parseInt(input.substring(input.indexOf(':')+1, input.length()));
            sock = new Socket(addr, port);            
            
            this.sendr = new SenderThread(sock, q);
            this.recvr = new RecvrThread(sock, padBottom, this);
        }
        
        this.setTitle("Arkanoid-Network - [Connected]");
        this.gameMenu.setEnabled(true);
    }

    /**
     * @param args
     * @throws IOException 
     */
    public static void main(String[] args) throws IOException
    {
        ArkanoidGameFrame f = new ArkanoidGameFrame();
    }

}
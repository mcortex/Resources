package arkanoidnetwork;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

public class RecvrThread extends Thread
{
    Socket sock;
    ObjectInputStream in;
    
    ArkanoidGameFrame frame;
    GamePad pad;
    
    public RecvrThread(Socket sock, GamePad pad, ArkanoidGameFrame frame) throws IOException
    {
        this.sock = sock;
        this.in = new ObjectInputStream(sock.getInputStream());
        this.pad = pad;
        this.frame = frame;
        this.start();
    }
    
    @Override
    public void run()
    {
        System.out.println("recvr thread started");
        try
        {
            while(true)
            {
                int i = in.readInt();                
                
                switch(i)
                {
                case 999999:
                    frame.newGame();
                    break;
                case 39:
                    pad.moveRight();
                    break;
                case 37:
                    pad.moveLeft();
                    break;
                case 68:
                    pad.moveRight();
                    break;
                case 65:
                    pad.moveLeft();
                    break;
                }
            }
        } 
        catch (IOException e)
        {
            e.printStackTrace();
        }        
        System.out.println("Recvr thread finished.");
    }
}
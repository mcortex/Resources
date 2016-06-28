package pixelmove.clienteservidor;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;

public class SenderThread extends Thread
{    
    Socket sock;
    ObjectOutputStream out;
    boolean exit;
    
    ArrayBlockingQueue<Integer> q;
    
    public SenderThread(Socket sock, ArrayBlockingQueue<Integer> q) throws IOException
    {
        this.sock = sock;
        this.out = new ObjectOutputStream(sock.getOutputStream());
        this.q =q;
        this.start();
    }

    @Override
    public void run()
    {
        System.out.println("sender thread started");
        try
        {
            while(true)
            {            
                int i = q.take();
                out.writeInt(i);
                out.flush();                
            } 
                    
        }
        catch (IOException e)
        {
            System.out.println(e.getClass().getName() + " : " + e.getMessage());
        } 
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }    
        
        System.out.println("Sndr thread finished.");
    }

}
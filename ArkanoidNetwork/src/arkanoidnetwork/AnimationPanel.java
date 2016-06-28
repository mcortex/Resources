package arkanoidnetwork;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.JPanel;

public class AnimationPanel extends JPanel
{
   private List<Paintable> list = Collections.synchronizedList( new ArrayList<Paintable>() );
    
    public void add(Paintable obj)
    {
        this.list.add(obj);
         
    }
    
    public void remove(Paintable obj)
    {
        if(list.contains(obj))
            list.remove(obj);
    }
    
    @Override
    public void paintComponent(Graphics graphics)
    {
        super.paintComponent(graphics);
        
        Graphics2D g = (Graphics2D) graphics;
        
        synchronized(list)
        {        
            for(Paintable obj : list)
            {
                obj.paint(g);
            }
        }
         
    }

    public int getCount()
    {
        this.list.size();
        return 0;
    }
}
package arkanoidnetwork;

import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics2D;

public class GamePad implements Paintable
{
    int top;
    int left;
    int bottom;
    int right;
    Color clr;
    Container parent;    
    
    final int dx = 12;
    
    public GamePad(int top, int left, int bottom, int right, Color clr, Container parent)
    {
        super();
        this.top = top;
        this.left = left;
        this.bottom = bottom;
        this.right = right;
        this.clr = clr;
        this.parent = parent;
    }

    public synchronized void moveLeft()
    {
        if(left - dx < 0)
            return;
        
        left -= dx;
        right -= dx;
    }
    
    public synchronized void moveRight()
    {
        if(right + dx > parent.getWidth())
            return;
        
        left += dx;
        right += dx;
    }

    @Override
    public void paint(Graphics2D g)
    {
        g.setColor(this.clr);
        g.fillRoundRect(left, top, right-left, bottom-top, 40, 8);
        g.setColor(Color.GRAY);
        g.drawRoundRect(left, top, right-left, bottom-top, 40, 8);        
    }

}
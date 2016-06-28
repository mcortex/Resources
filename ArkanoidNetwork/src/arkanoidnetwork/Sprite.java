package arkanoidnetwork;

import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics2D;
import java.awt.Point;

public abstract class Sprite implements Paintable, Movable
{
    Point p;
    Color c;    
    int dx;
    int dy;    
    int width;
    int height;
    Container parent;
    
    Sprite(Point p, Color c, int dx, int dy, int width, int height, Container parent)
    {
        this.p = p;
        this.c = c;
        this.dx= dx;
        this.dy = dy;
        this.width = width;
        this.height = height;
        this.parent = parent;
    }
    
    @Override
    public void move()
    {
        if(dy < 0)
        {
            if(p.y+dy < 0)
                dy = -dy;
        }
        else
        {
            if(p.y+dy > parent.getHeight()-parent.getInsets().bottom-height)
                dy = -dy;
        }
        
        if(dx < 0)
        {
            if(p.x+dx < 0)
                dx = -dx;
        }
        else
        {
            if(p.x+dx > parent.getWidth()-parent.getInsets().right-width)
                dx = -dx;
        }
        
        p.x += dx;
        p.y += dy;
    }

    @Override
    abstract public void paint(Graphics2D g);

    @Override
    public String toString()
    {
        return "Shape [p=" + p + ", c=" + c + ", dx=" + dx + ", dy=" + dy
                + ", width=" + width + ", height=" + height + ", parent="
                + parent + "]";
    }    
}
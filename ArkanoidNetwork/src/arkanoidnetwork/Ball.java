package arkanoidnetwork;

import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Ellipse2D;

public class Ball extends Sprite
{    
    Ball(Point p, Color c, int dx, int dy, int diameter, Container parent)
    {
        super(p, c, dx, dy, diameter, diameter, parent);
    }
    
    @Override
    public void paint(Graphics2D g)
    {
        g.setColor(this.c);
        g.fill(new Ellipse2D.Double(p.x, p.y, width, width));
        g.setColor(Color.GRAY);
        g.drawOval(p.x, p.y, width, width);
    }
}
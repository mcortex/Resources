package arkanoidnetwork;

import java.awt.Color;
import java.awt.Container;
import java.awt.Point;

public class GameBall extends Ball
{
    GamePad padTop, padBottom;
    
    GameBall(Point p, Color c, int dx, int dy, int diameter, Container parent, GamePad padTop, GamePad padBottom)
    {
        super(p, c, dx, dy, diameter, parent);
        this.padTop = padTop;
        this.padBottom = padBottom;
    }
    
    // USADA PARA CHEQUEO DE COLISIONES ENTRE LA BOLA Y LA PALETA
    
    @Override
    public void move()
    {    
        if(dy < 0) // ball moving up
        {
            if(p.y+dy < padTop.bottom)
            {
                if(p.x > padTop.left && p.x < padTop.right)
                    dy = -dy;
            }
            if(p.y+dy < 0)
            {
                //dy = -dy;
                throw new IllegalStateException();
            }
        }
        else // ball moving down
        {
            if(p.y+height+dy > padBottom.top)
            {
                if(p.x > padBottom.left && p.x < padBottom.right)
                    dy = -dy;
            }
            if(p.y+height+dy > parent.getHeight()-parent.getInsets().bottom-height)
            {
                //dy = -dy;
                throw new IllegalStateException();
            }
        }
        
        if(dx < 0) // ball moving left
        {
            if(p.x+dx < 0)
                dx = -dx;
        }
        else // ball moving right
        {
            if(p.x+dx > parent.getWidth()-parent.getInsets().right-width)
                dx = -dx;
        }
        
        p.x += dx;
        p.y += dy;
    }

}
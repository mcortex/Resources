package arkanoidnetwork;

public class AnimationThread extends Thread
{
    Sprite shape;
    int speed;

    public AnimationThread(Sprite shape, int speed, String name)
    {
        super(name);
        this.shape = shape;
        this.speed = speed;
        this.start();
    }
    
    public void setSpeed(int speed)
    {
        this.speed = speed;
    }
    
    @Override
    public void run()
    {
        try
        {
            while(true)
            {
                shape.parent.repaint();
                shape.move();
                try
                {
                    Thread.sleep(speed);
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                }
            }
        }
        catch(IllegalStateException excp)
        {
            
        }
    }
}
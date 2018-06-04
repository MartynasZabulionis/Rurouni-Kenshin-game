import greenfoot.*;

public class Caption extends Actor
{
    Color[] colors = { Color.CYAN, Color.ORANGE, Color.RED, Color.GREEN };
    int colorNum = 0;
    int counter = 1;
    String caption;
    
    public Caption(String cap)
    {
        caption = cap;
    }
    public void addedToWorld(World world)
    {
        act();
    }

    public void act()
    {
        if (--counter == 0)
        {
            colorNum = ++colorNum % 4;
            updateImage();
            counter = 20;
        }
    }
    
    public void updateImage()
    {
        setImage(new GreenfootImage(caption, 96, colors[colorNum], new Color(0, 0, 0, 0)));
    }
}

import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MainPage extends World
{
    GifImage stage = new GifImage("stage1.gif");
    public Bar KenshinHPbar = new Bar("", "", 100, 100);
    public Bar ShishioHPbar = new Bar("", "", 100, 100);
    
    public Bar KenshinSPbar = new Bar("", "", 100, 100);
    public Bar ShishioSPbar = new Bar("", "", 100, 100);
    
    public Caption GetReady = new Caption("GET\nREADY!");
    public Caption KO = new Caption("K.O.!");
    
    public boolean GameEnded = false;
    
    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    public MainPage()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(800, 336, 1);
        setBackground(stage.getCurrentImage());
        
        addObject(GetReady, getWidth() / 2, getHeight() / 2);
        
        addObject(KenshinHPbar, 135, 20);
        KenshinHPbar.setBarHeight(20);
        KenshinHPbar.setBarWidth(250);
        KenshinHPbar.setDangerColor(new Color(255, 00, 0));
        KenshinHPbar.setSafeColor(new Color(255, 100, 0));
        KenshinHPbar.setBreakLow(true);
        KenshinHPbar.setTextColor(new Color(255, 255, 255));
        
        addObject(ShishioHPbar, 665, 20);
        ShishioHPbar.setMirrorHorizontally();
        ShishioHPbar.setBarHeight(20);
        ShishioHPbar.setBarWidth(250);
        ShishioHPbar.setDangerColor(new Color(255, 00, 0));
        ShishioHPbar.setSafeColor(new Color(100, 100, 255));
        ShishioHPbar.setBreakLow(true);
        ShishioHPbar.setTextColor(new Color(255, 255, 255));
        
        addObject(KenshinSPbar, 100, 42);
        KenshinSPbar.setBarHeight(10);
        KenshinSPbar.setBarWidth(180);
        KenshinSPbar.setDangerColor(new Color(255, 255, 0));
        KenshinSPbar.setSafeColor(new Color(0, 255, 0));
        KenshinSPbar.setBreakLow(true);
        KenshinSPbar.setTextColor(new Color(255, 255, 255));
        KenshinSPbar.setShowTextualUnits(false);
        
        addObject(ShishioSPbar, 700, 42);
        ShishioSPbar.setMirrorHorizontally();
        ShishioSPbar.setBarHeight(10);
        ShishioSPbar.setBarWidth(180);
        ShishioSPbar.setDangerColor(new Color(255, 255, 0));
        ShishioSPbar.setSafeColor(new Color(0, 255, 0));
        ShishioSPbar.setBreakLow(true);
        ShishioSPbar.setTextColor(new Color(255, 255, 255));
        ShishioSPbar.setShowTextualUnits(false);
        
        addObject(new Shishio(), 700, 300);
        addObject(new Kenshin(), 100, 300);
        
    }
    public void SetKO()
    {
        addObject(KO, getWidth() / 2, getHeight() / 2);
    }
    public void started()
    {
        removeObject(GetReady);
    }
    public void act()
    {
        //ShishioSPbar.subtract(1);
        setBackground(stage.getCurrentImage());
    }
}
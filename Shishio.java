import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Shishio here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

public class Shishio extends Actor
{
    // Controls
    private final String MOVE_LEFT = "left";
    private final String MOVE_RIGHT = "right";
    private final String JUMP = "up";
    private final String GUARD = "down";
    private final String ATTACK = "5";
    private final String ATTACK_UP = "8";
    private final String KAGUZUCHI = "6";
    private final String HOMURADAMA = "7";
    private final String HOMURADAMA2 = "4";
    private final String SPECIAL = "9";
    
    // Damages
    private final int SIMPLE_DAMAGE = 1;
    
    private final int SPECIAL1_DAMAGE = 20;
    private final int SPECIAL2_DAMAGE = 30;
    private final int KUZURYUSEN_DAMAGE = 40;
    private final int SOURYUSEN_DAMAGE = 50;
    
    // Constants
    private final int SPECIAL1_SP = 40;
    private final int KAGUZUCHI_SP = 60;
    private final int HOMURADAMA1_SP = 30;
    private final int HOMURADAMA2_SP = 20;
    
    private final int WALK_SPEED[] = {-4, 4};
    private final int HURT_MOVE_SPEED[] = {-1, 1};
    private final int DODGE_MOVE_SPEED[] = {-3, 3};
    private final int HURT_SPECIAL_MOVE_SPEED[] = {-10, 10};
    private final int BIGGER_IMAGE = 20;
    private final int SP_ADD_FREQ = 20;
    
    private GreenfootImage shishio_standup[][] = {new GreenfootImage[4], new GreenfootImage[4]};
    private GreenfootImage shishio_fall[][] = {new GreenfootImage[4], new GreenfootImage[4]};
    private GreenfootImage shishio_hurt[][] = {new GreenfootImage[3], new GreenfootImage[3]};
    
    private GreenfootImage shishio_stance[] = new GreenfootImage[2];
    
    private GreenfootImage shishio_move[][] = {new GreenfootImage[4], new GreenfootImage[4]};
    
    private GreenfootImage shishio_jump[][] = {new GreenfootImage[8], new GreenfootImage[8]};
    
    private GreenfootImage shishio_guard[][] = {new GreenfootImage[3], new GreenfootImage[3]};
   
    private GreenfootImage shishio_attack[][][] = {{new GreenfootImage[6], new GreenfootImage[6]}, {new GreenfootImage[6], new GreenfootImage[6]}};
    
    private GreenfootImage shishio_attack_aerial[][] = {new GreenfootImage[4], new GreenfootImage[4]};
    private GreenfootImage shishio_attack_upwards[][] = {new GreenfootImage[7], new GreenfootImage[7]};
    
    private GreenfootImage shishio_specialattack1[][] = {new GreenfootImage[13], new GreenfootImage[13]};
    private GreenfootImage shishio_homuradama[][] = {new GreenfootImage[13], new GreenfootImage[13]};
    private GreenfootImage shishio_homuradama2[][] = {new GreenfootImage[14], new GreenfootImage[14]};
    private GreenfootImage shishio_kaguzuchi[][] = {new GreenfootImage[25], new GreenfootImage[25]};
    
    private GreenfootImage shishio_flame_disappear[][] = {new GreenfootImage[2], new GreenfootImage[2]};
    
    private int ImageCounter = 0;
    private int ChangeFrame = 0;
    private int TurnedRight = 0;
    private boolean Jumping = false;
    private boolean TimeToJump = false;
    private int JumpMomentum = 0;
    private int JumpSpeed = 0;
    private int JumpHorizontal = 0;
    private int ChangeJumpPos = 0;
    private boolean Guarding = false;
    private boolean Attacking = false;
    private boolean AttackingUpwards = false;
    private boolean Attacking_Aerial = false;
    private int SpecialAttack = 0;
    private int AttackType = 0;
    private boolean BeingHit = false;
    private boolean BeingHitSpecial = false;
    private int BeingHitSpecialNum = 0;
    private boolean AlreadyHit = false;
    private int Hurt = 0;
    private int HurtSpecial = 0;
    private int HurtDir = 0;
    
    private int HP = 100;
    private int SP = 100;
    private int SP_change = 0;
    private int SP_start = 0;
    
    public static GreenfootImage mirrorHorizontally(GreenfootImage original)
    {
        GreenfootImage mirrored = new GreenfootImage(original.getWidth(), original.getHeight());
        for (int y=0; y<original.getHeight(); y++) for (int x=0; x<original.getWidth(); x++)
        {
            Color color = original.getColorAt(x, y);
            int n = original.getWidth()-x-1;
            mirrored.setColorAt(n, y, color);
        }
        return mirrored;
    }

    public Shishio()
    {
        
        shishio_stance[1] = new GreenfootImage("Shishio/Stance/1.png");
        shishio_stance[0] = mirrorHorizontally(shishio_stance[1]);
        
        shishio_stance[1].scale(shishio_stance[1].getWidth() + BIGGER_IMAGE, shishio_stance[1].getHeight() + BIGGER_IMAGE);
        shishio_stance[0].scale(shishio_stance[0].getWidth() + BIGGER_IMAGE, shishio_stance[0].getHeight() + BIGGER_IMAGE);
        
        String image_str;
        for (int i = 0; i < 4; ++i)
        {
            image_str = String.format("Shishio/Walk/%d.png", i + 1);
            shishio_move[1][i] = new GreenfootImage(image_str);
            shishio_move[0][i] = mirrorHorizontally(shishio_move[1][i]);
            
            shishio_move[1][i].scale(shishio_move[1][i].getWidth() + BIGGER_IMAGE, shishio_move[1][i].getHeight() + BIGGER_IMAGE);
            shishio_move[0][i].scale(shishio_move[0][i].getWidth() + BIGGER_IMAGE, shishio_move[0][i].getHeight() + BIGGER_IMAGE);
        }
        
        for (int i = 0; i < 8; ++i)
        {
            image_str = String.format("Shishio/Jump/%d.png", i + 1);
            shishio_jump[1][i] = new GreenfootImage(image_str);
            shishio_jump[0][i] = mirrorHorizontally(shishio_jump[1][i]);
            
            shishio_jump[1][i].scale(shishio_jump[1][i].getWidth() + BIGGER_IMAGE, shishio_jump[1][i].getHeight() + BIGGER_IMAGE);
            shishio_jump[0][i].scale(shishio_jump[0][i].getWidth() + BIGGER_IMAGE, shishio_jump[0][i].getHeight() + BIGGER_IMAGE);
        }
        
        for (int i = 0; i < 3; ++i)
        {
            image_str = String.format("Shishio/Guard/%d.png", i + 1);
            shishio_guard[1][i] = new GreenfootImage(image_str);
            shishio_guard[0][i] = mirrorHorizontally(shishio_guard[1][i]);
            
            shishio_guard[1][i].scale(shishio_guard[1][i].getWidth() + BIGGER_IMAGE, shishio_guard[1][i].getHeight() + BIGGER_IMAGE);
            shishio_guard[0][i].scale(shishio_guard[0][i].getWidth() + BIGGER_IMAGE, shishio_guard[0][i].getHeight() + BIGGER_IMAGE);
        }
        
        for (int i = 0; i < 6; ++i)
        {
            for (int y = 0; y < 2; ++y)
            {
                image_str = String.format("Shishio/Attack%d/%d.png", y + 1, i + 1);
                shishio_attack[1][y][i] = new GreenfootImage(image_str);
                shishio_attack[0][y][i] = mirrorHorizontally(shishio_attack[1][y][i]);
                
                shishio_attack[1][y][i].scale(shishio_attack[1][y][i].getWidth() + BIGGER_IMAGE, shishio_attack[1][y][i].getHeight() + BIGGER_IMAGE);
                shishio_attack[0][y][i].scale(shishio_attack[0][y][i].getWidth() + BIGGER_IMAGE, shishio_attack[0][y][i].getHeight() + BIGGER_IMAGE);
            }
        }
        for (int i = 0; i < 4; ++i)
        {
            image_str = String.format("Shishio/Attack_Aerial/%d.png", i + 1);
            shishio_attack_aerial[1][i] = new GreenfootImage(image_str);
            shishio_attack_aerial[0][i] = mirrorHorizontally(shishio_attack_aerial[1][i]);
            
            shishio_attack_aerial[1][i].scale(shishio_attack_aerial[1][i].getWidth() + BIGGER_IMAGE, shishio_attack_aerial[1][i].getHeight() + BIGGER_IMAGE);
            shishio_attack_aerial[0][i].scale(shishio_attack_aerial[0][i].getWidth() + BIGGER_IMAGE, shishio_attack_aerial[0][i].getHeight() + BIGGER_IMAGE);
        }
        for (int i = 0; i < 7; ++i)
        {
            image_str = String.format("Shishio/Attack_Upwards/%d.png", i + 1);
            shishio_attack_upwards[1][i] = new GreenfootImage(image_str);
            shishio_attack_upwards[0][i] = mirrorHorizontally(shishio_attack_upwards[1][i]);
            
            shishio_attack_upwards[1][i].scale(shishio_attack_upwards[1][i].getWidth() + BIGGER_IMAGE, shishio_attack_upwards[1][i].getHeight() + BIGGER_IMAGE);
            shishio_attack_upwards[0][i].scale(shishio_attack_upwards[0][i].getWidth() + BIGGER_IMAGE, shishio_attack_upwards[0][i].getHeight() + BIGGER_IMAGE);
        }
        for (int i = 0; i < 13; ++i)
        {
            image_str = String.format("Shishio/Special1/%d.png", i + 1);
            shishio_specialattack1[1][i] = new GreenfootImage(image_str);
            shishio_specialattack1[0][i] = mirrorHorizontally(shishio_specialattack1[1][i]);
            
            shishio_specialattack1[1][i].scale(shishio_specialattack1[1][i].getWidth() + BIGGER_IMAGE, shishio_specialattack1[1][i].getHeight() + BIGGER_IMAGE);
            shishio_specialattack1[0][i].scale(shishio_specialattack1[0][i].getWidth() + BIGGER_IMAGE, shishio_specialattack1[0][i].getHeight() + BIGGER_IMAGE);
        }
        for (int i = 0; i < 2; ++i)
        {
            image_str = String.format("Shishio/Special1/0%d.png", i);
            shishio_flame_disappear[1][i] = new GreenfootImage(image_str);
            shishio_flame_disappear[0][i] = mirrorHorizontally(shishio_flame_disappear[1][i]);
            
            shishio_flame_disappear[1][i].scale(shishio_flame_disappear[1][i].getWidth() + BIGGER_IMAGE, shishio_flame_disappear[1][i].getHeight() + BIGGER_IMAGE);
            shishio_flame_disappear[0][i].scale(shishio_flame_disappear[0][i].getWidth() + BIGGER_IMAGE, shishio_flame_disappear[0][i].getHeight() + BIGGER_IMAGE);
        }
        for (int i = 0; i < 8; ++i)
        {
            shishio_homuradama[1][i] = shishio_homuradama2[1][i] = shishio_specialattack1[1][i];
            shishio_homuradama[0][i] = shishio_homuradama2[0][i] = shishio_specialattack1[0][i];
        }
        for (int i = 8; i < 13; ++i)
        {
            image_str = String.format("Shishio/Homuradama/%d.png", i + 1);
            shishio_homuradama[1][i] = new GreenfootImage(image_str);
            shishio_homuradama[0][i] = mirrorHorizontally(shishio_homuradama[1][i]);
            
            shishio_homuradama[1][i].scale(shishio_homuradama[1][i].getWidth() + BIGGER_IMAGE, shishio_homuradama[1][i].getHeight() + BIGGER_IMAGE);
            shishio_homuradama[0][i].scale(shishio_homuradama[0][i].getWidth() + BIGGER_IMAGE, shishio_homuradama[0][i].getHeight() + BIGGER_IMAGE);
        }
        for (int i = 8; i < 14; ++i)
        {
            image_str = String.format("Shishio/Homuradama2/%d.png", i + 1);
            shishio_homuradama2[1][i] = new GreenfootImage(image_str);
            shishio_homuradama2[0][i] = mirrorHorizontally(shishio_homuradama2[1][i]);
            
            shishio_homuradama2[1][i].scale(shishio_homuradama2[1][i].getWidth() + BIGGER_IMAGE, shishio_homuradama2[1][i].getHeight() + BIGGER_IMAGE);
            shishio_homuradama2[0][i].scale(shishio_homuradama2[0][i].getWidth() + BIGGER_IMAGE, shishio_homuradama2[0][i].getHeight() + BIGGER_IMAGE);
        }
        for (int i = 0; i < 25; ++i)
        {
            image_str = String.format("Shishio/Kaguzuchi/%d.png", i + 1);
            shishio_kaguzuchi[1][i] = new GreenfootImage(image_str);
            shishio_kaguzuchi[0][i] = mirrorHorizontally(shishio_kaguzuchi[1][i]);
            
            shishio_kaguzuchi[1][i].scale(shishio_kaguzuchi[1][i].getWidth() + BIGGER_IMAGE, shishio_kaguzuchi[1][i].getHeight() + BIGGER_IMAGE);
            shishio_kaguzuchi[0][i].scale(shishio_kaguzuchi[0][i].getWidth() + BIGGER_IMAGE, shishio_kaguzuchi[0][i].getHeight() + BIGGER_IMAGE);
        }
        for (int i = 0; i < 3; ++i)
        {
            image_str = String.format("Shishio/Hurt/%d.png", i + 1);
            shishio_hurt[1][i] = new GreenfootImage(image_str);
            shishio_hurt[0][i] = mirrorHorizontally(shishio_hurt[1][i]);
            
            shishio_hurt[1][i].scale(shishio_hurt[1][i].getWidth() + BIGGER_IMAGE, shishio_hurt[1][i].getHeight() + BIGGER_IMAGE);
            shishio_hurt[0][i].scale(shishio_hurt[0][i].getWidth() + BIGGER_IMAGE, shishio_hurt[0][i].getHeight() + BIGGER_IMAGE);
        }
        for (int i = 0; i < 4; ++i)
        {
            image_str = String.format("Shishio/Fall/%d.png", i + 1);
            shishio_fall[1][i] = new GreenfootImage(image_str);
            shishio_fall[0][i] = mirrorHorizontally(shishio_fall[1][i]);
            
            shishio_fall[1][i].scale(shishio_fall[1][i].getWidth() + BIGGER_IMAGE, shishio_fall[1][i].getHeight() + BIGGER_IMAGE);
            shishio_fall[0][i].scale(shishio_fall[0][i].getWidth() + BIGGER_IMAGE, shishio_fall[0][i].getHeight() + BIGGER_IMAGE);
        }
        for (int i = 0; i < 4; ++i)
        {
            image_str = String.format("Shishio/StandUP/%d.png", i + 1);
            shishio_standup[1][i] = new GreenfootImage(image_str);
            shishio_standup[0][i] = mirrorHorizontally(shishio_standup[1][i]);
            
            shishio_standup[1][i].scale(shishio_standup[1][i].getWidth() + BIGGER_IMAGE, shishio_standup[1][i].getHeight() + BIGGER_IMAGE);
            shishio_standup[0][i].scale(shishio_standup[0][i].getWidth() + BIGGER_IMAGE, shishio_standup[0][i].getHeight() + BIGGER_IMAGE);
        }
    }
    public void addedToWorld(World world)
    {
        setImage(shishio_stance[TurnedRight]);
    }

    /**
     * Act - do whatever the Shishio wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    
    public void act() 
    {
        IncreaseSP();
        if (BeingHit && (SpecialAttack == 0 || SpecialAttack % 2 != 0))
        {
            if (Guarding && (TurnedRight != (((Kenshin) getWorld().getObjects(Kenshin.class).get(0)).GetTurnedRight())))
            {
                BeingHit = false;
                setLocation(getX() + DODGE_MOVE_SPEED[HurtDir], getY());
                return;
            }
            DecreaseHP(SIMPLE_DAMAGE);
            Hurt = 1;
            BeingHit = false;
            act();
            return;
        }
        BeingHit = false;
        if (Hurt != 0)
        {
            if (Hurt == 5)
            {
                Hurt = 0;
                //BeingHit = false;
                return;
            }
            setLocation(getX() + HURT_MOVE_SPEED[HurtDir], getY());
            if (Hurt++ == 1)
                setImage(shishio_hurt[TurnedRight][Greenfoot.getRandomNumber(3)]);
            return;
        }
        if (BeingHitSpecial)
        {
            switch (BeingHitSpecialNum)
            {
                case 2: DecreaseHP(SPECIAL1_DAMAGE); break;
                case 10: DecreaseHP(SOURYUSEN_DAMAGE); break;
                case 6: DecreaseHP(KUZURYUSEN_DAMAGE); break;
                case 8: DecreaseHP(SPECIAL2_DAMAGE); break;
            }
            SpecialAttack = 0;
            
            HurtSpecial = 1;
            BeingHitSpecial = false;
            ChangeFrame = ImageCounter = 0;
            act();
            return;
        }
        if (HurtSpecial != 0)
        {
            if (HurtSpecial > 3)
            {
                --HurtSpecial;
                return;
            }
            if (HurtSpecial == 3)
            {
                if (((MainPage) getWorld()).GameEnded)
                    return;
                    
                ChangeFrame = ImageCounter = 0;
                HurtSpecial = 2;
                act();
                return;
            }
            if (HurtSpecial == 2)
            {
                if (ImageCounter == 4)
                {
                    HurtSpecial = ChangeFrame = ImageCounter = 0;
                    return;
                }
                if (ChangeFrame++ % 10 == 0)
                    setImage(shishio_standup[TurnedRight][ImageCounter++ % 4]);
                return;
            }
            
            if (ImageCounter == 4)
            {
                HurtSpecial = 43;
                //BeingHit = false;
                return;
            }
            setLocation(getX() + HURT_SPECIAL_MOVE_SPEED[HurtDir], Jumping ? (getY() + 2 > 300 ? 300 : getY() + 2) : getY());
            if (ChangeFrame++ % 10 == 0)
                setImage(shishio_fall[TurnedRight][ImageCounter++ % 4]);
            return;
        }
        if (SpecialAttack < -5)
        {
            if (ImageCounter == 8)
                setImage(shishio_flame_disappear[TurnedRight][0]);
            else
                setImage(shishio_specialattack1[TurnedRight][1]);
            ++SpecialAttack;
            return;
        }
        if (SpecialAttack < 0)
        {
            if (ImageCounter == 8)
                setImage(shishio_flame_disappear[TurnedRight][1]);
            else
                setImage(shishio_specialattack1[TurnedRight][0]);
            if (++SpecialAttack == 0 && ImageCounter == 7)
            {
                SpecialAttack = -10;
                ImageCounter = 0;
            }
            return;
        }
        
        if (Attacking && !Attacking_Aerial)
        {
            AttackNotAerial();
            return;
        }
        
        if (Guarding && !Greenfoot.isKeyDown(GUARD))
        {
            setImage(shishio_guard[TurnedRight][0]);
            Guarding = false;
            return;
        }
        if (Greenfoot.isKeyDown(GUARD) && SpecialAttack == 0 && !Jumping)
        {
            Guard();
            return;
        }
        if (!TimeToJump && Greenfoot.isKeyDown(JUMP) && SpecialAttack == 0)
        {
            if (JumpMomentum > -4)
                JumpMomentum -= 1;
            else
                TimeToJump = true;
            
            ImageCounter = ChangeFrame = 0;
            Jumping = true;
            setImage(shishio_jump[TurnedRight][0]);
        }
        else if (Jumping)
        {
            JumpJumpJump();
            return;
        }
        
        if (Greenfoot.isKeyDown(ATTACK) && SpecialAttack == 0)
        {
            Attacking = true;
            ChangeFrame = ImageCounter = 0;
            AttackType = Greenfoot.getRandomNumber(2);
            setImage(shishio_attack[TurnedRight][AttackType][0]);
            return;
        }
        if (Greenfoot.isKeyDown(ATTACK_UP) && SpecialAttack == 0)
        {
            Attacking = AttackingUpwards = true;
            ChangeFrame = ImageCounter = 0;
            setImage(shishio_attack_upwards[TurnedRight][0]);
            return;
        }
        
        
        if (SpecialAttack == 2 || (SpecialAttack < 2 && Greenfoot.isKeyDown(HOMURADAMA)))
        {
            Homuradama1();    
            return;
        }
        if (SpecialAttack == 4 || (Greenfoot.isKeyDown(KAGUZUCHI) && (SpecialAttack == 0 || SpecialAttack == 3)))
        {
            Kaguzuchi();   
            return;
        }
        if (SpecialAttack == 6 || (Greenfoot.isKeyDown(SPECIAL) && (SpecialAttack == 0 || SpecialAttack == 5)))
        {
            SpecialAttack1();  
            return;
        }
        if (SpecialAttack == 8 || SpecialAttack == 9 || (Greenfoot.isKeyDown(HOMURADAMA2) && (SpecialAttack == 0 || SpecialAttack == 7)))
        {
            Homuradama2();   
            return;
        }
        if (SpecialAttack > 0)
        {
            SpecialAttack = -10;
            return;
        }
        if(Greenfoot.isKeyDown(MOVE_RIGHT))
        {
            MoveRight();
        }
        else if(Greenfoot.isKeyDown(MOVE_LEFT))
        {
            MoveLeft();
        }
        else
        {
            Stance();
        }

    }
    
    public boolean SetNewSpecial(int num, GreenfootImage im)
    {
        if (SpecialAttack != 0  || SP < 2)
            return false;
            
        SpecialAttack = num;
        ChangeFrame = ImageCounter = 0;
        setImage(im);
        return true;
        
    }
    
    public void AttackNotAerial()
    {
        if (AttackingUpwards)
        {
            if (ImageCounter == 7)
            {
                Attacking = AttackingUpwards = AlreadyHit = false;
                ImageCounter = ChangeFrame = JumpHorizontal = 0;
                return;
            }
            CheckForHit_Simple();
            if (ChangeFrame++ % 3 == 0)
                    setImage(shishio_attack_upwards[TurnedRight][ImageCounter++ % 7]);
            return;
        }
        if (ImageCounter == 6)
        {
            Attacking = AlreadyHit = false;
            ImageCounter = ChangeFrame = JumpHorizontal = 0;
            return;
        }
        CheckForHit_Simple();
        if (ChangeFrame++ % 3 == 0)
                setImage(shishio_attack[TurnedRight][AttackType][ImageCounter++ % 6]);
        
        if (Greenfoot.isKeyDown(MOVE_LEFT))
            setLocation(getX() - 2, getY());
        else if (Greenfoot.isKeyDown(MOVE_RIGHT))
            setLocation(getX() + 2, getY());
        return;
    }
    public void JumpJumpJump()
    {
        TimeToJump = true;
        
        if (getY() + JumpSpeed > 300)
        {
            setLocation(getX(), 300);
            Jumping = TimeToJump = false;
            JumpMomentum = JumpSpeed = ChangeFrame = ImageCounter = 0;
            Attacking_Aerial = Attacking = false;
            return;
        }
        if (JumpMomentum < 0)
        {
            JumpSpeed += JumpMomentum;
            JumpMomentum += 1;
        }
        else if (ChangeJumpPos++ % 5 == 0)
        {
            JumpSpeed += JumpMomentum;
            JumpMomentum += 1;
        }
        //setLocation(getX() + WALK_SPEED[TurnedRight], getY() + JumpSpeed);
        
        if (Greenfoot.isKeyDown(MOVE_RIGHT))
            setLocation(getX() + WALK_SPEED[1], getY() + JumpSpeed);
        else if (Greenfoot.isKeyDown(MOVE_LEFT))
            setLocation(getX() + WALK_SPEED[0], getY() + JumpSpeed);
        else
            setLocation(getX(), getY() + JumpSpeed);
        
        if (Attacking_Aerial)
        {
            if (ImageCounter == 4)
            {
                Attacking = Attacking_Aerial = AlreadyHit = false;
                ImageCounter = 2;
                ChangeFrame = 0;
                return;
            }
            CheckForHit_Simple();
            if (ChangeFrame++ % 5 == 0)
                setImage(shishio_attack_aerial[TurnedRight][ImageCounter++ % 4]);
                
            return;
        }
        
        if (!Attacking_Aerial && Greenfoot.isKeyDown(ATTACK))
        {
            Attacking_Aerial = true;
            Attacking = true;
            ChangeFrame = ImageCounter = 0;
            return;
        }
        
        if (ChangeFrame++ % 9 == 0)
            setImage(shishio_jump[TurnedRight][ImageCounter++ % 8]);
    }
    public void Guard()
    {
        if (!Guarding)
        {
            setImage(shishio_guard[TurnedRight][0]);
            Guarding = true;
            return;
        }
        setImage(shishio_guard[TurnedRight][1]);
        return;
    }
    public void Homuradama1()
    {
        if (SetNewSpecial(1, shishio_homuradama[TurnedRight][0]))
                return;
        
        if (SpecialAttack == 2)
            CheckForHit_Special(2);
            
        if (ImageCounter == 13)
        {
            SpecialAttack = 0;
            ImageCounter = ChangeFrame = 0;
            AlreadyHit = false;
            return;
        }
        if (ImageCounter >= 8)
        {
            if (ImageCounter == 8 && CheckForSP2(HOMURADAMA1_SP))
                return;
                
            SpecialAttack = 2;
            
            if (ChangeFrame++ % 25 == 0)
                setImage(shishio_homuradama[TurnedRight][ImageCounter++ % 13]);
            return;
        }
        if (CheckForSP(10))
            return;
            
        if (ChangeFrame++ % 7 == 0)
                setImage(shishio_homuradama[TurnedRight][ImageCounter++ % 13]);
    }
    public void Kaguzuchi()
    {
        if (SetNewSpecial(3, shishio_kaguzuchi[TurnedRight][0]))
                return;
             
        if (SpecialAttack == 4 && ImageCounter >= 14)
            CheckForHit_Special(4);
            
        if (ImageCounter == 25)
        {
            SpecialAttack = 0;
            ImageCounter = ChangeFrame = 0;
            AlreadyHit = false;
            return;
        }
        if (ImageCounter >= 8)
        {
            if (ImageCounter == 8 && CheckForSP2(KAGUZUCHI_SP))
                return;
                
            SpecialAttack = 4;
            
            if (ChangeFrame++ % 10 == 0)
                setImage(shishio_kaguzuchi[TurnedRight][ImageCounter++ % 25]);
            return;
        }
        if (CheckForSP(15))
            return;
        
        if (ChangeFrame++ % 7 == 0)
                setImage(shishio_kaguzuchi[TurnedRight][ImageCounter++ % 25]);
    }
    public void SpecialAttack1()
    {
        if (SetNewSpecial(5, shishio_specialattack1[TurnedRight][0]))
                return;
            
        if (ImageCounter == 13)
        {
            SpecialAttack = 0;
            ImageCounter = ChangeFrame = 0;
            AlreadyHit = false;
            return;
        }
        if (SpecialAttack == 6)
        {
            CheckForHit_Special(6);
            
            if (ChangeFrame++ % 7 == 0)
                setImage(shishio_specialattack1[TurnedRight][ImageCounter++ % 13]);
            if (TurnedRight == 1)
                setLocation(getX() + 8, getY());
            else
                setLocation(getX() - 8, getY());
                
            return;
        }
        else if (CheckForSP(15))
            return;
        if (ImageCounter >= 8)
        {
            if (Greenfoot.isKeyDown(TurnedRight == 1 ? MOVE_RIGHT : MOVE_LEFT))
            {
                if (CheckForSP2(SPECIAL1_SP))
                    return;
                    
                SpecialAttack = 6;
                act();
                return;
            }
            return;
        }
            
        if (ChangeFrame++ % 7 == 0)
                setImage(shishio_specialattack1[TurnedRight][ImageCounter++ % 13]);
    }
    public void Homuradama2()
    {
        if (SetNewSpecial(7, shishio_homuradama2[TurnedRight][0]))
                return;
                
        if (SpecialAttack == 9 && !Greenfoot.isKeyDown(HOMURADAMA2))
        {
            SpecialAttack = 8;
            ChangeFrame = 0;
            if (Greenfoot.isKeyDown(TurnedRight == 1 ? MOVE_RIGHT : MOVE_LEFT))
            {
                setLocation(getX() + WALK_SPEED[TurnedRight] * 5, getY());
            }
            act();
            return;
        }
        if (SpecialAttack == 8)
        {
            if (ImageCounter == 7 && CheckForSP2(HOMURADAMA2_SP))
                return;
                
            CheckForHit_Special(8);
            if (ImageCounter == 14)
            {
                SpecialAttack = 0;
                AlreadyHit = false;
                ImageCounter = ChangeFrame = 0;
                return;
            }
            if (ChangeFrame++ % 7 == 0)
                    setImage(shishio_homuradama2[TurnedRight][++ImageCounter % 14]);
            return;
        }
        if (ImageCounter == 7)
        {
            if (CheckForSP(15))
                return;
            SpecialAttack = 9;
            if (Greenfoot.isKeyDown(TurnedRight == 1 ? MOVE_RIGHT : MOVE_LEFT))
            {
                setImage(shishio_homuradama2[TurnedRight][9]);
                setLocation(getX() + WALK_SPEED[TurnedRight], getY());
            }
            else
                setImage(shishio_homuradama2[TurnedRight][8]);
            return;
        }
        if (CheckForSP(10))
            return;
            
        if (ChangeFrame++ % 7 == 0)
                setImage(shishio_homuradama2[TurnedRight][ImageCounter++ % 14]);
    }
    public void MoveRight()
    {
        TurnedRight = 1;
        setLocation(getX() + WALK_SPEED[1], getY());
        
        if (Jumping)
            return;
        
        if (ChangeFrame++ % 10 == 0)
            ImageCounter++;
        setImage(shishio_move[TurnedRight][ImageCounter % 4]);
            
        
        //JumpHorizontal = WALK_SPEED;
    }
    public void MoveLeft()
    {
        TurnedRight = 0;
        setLocation(getX() + WALK_SPEED[0], getY());
        
        if (Jumping)
            return;
            
        if (ChangeFrame++ % 10 == 0)
            ImageCounter++;
            
       setImage(shishio_move[TurnedRight][ImageCounter % 4]);
        
        
        //JumpHorizontal = -WALK_SPEED;
    }
    public void Stance()
    {
        ImageCounter = JumpHorizontal = 0;
        setImage(shishio_stance[TurnedRight]);
    }
    public int GetTurnedRight()
    {
        return TurnedRight;
    }
    public void SetBeingHit()
    {
        BeingHit = true;
    }
    public void SetBeingHitSpecial()
    {
        BeingHitSpecial = true;
    }
    public void SetBeingHitSpecialNum(int n)
    {
        BeingHitSpecialNum = n;
    }
    public void SetHurtDir(int d)
    {
        HurtDir = d;
    }
    public void CheckForHit_Simple()
    {
        if (!isTouching(Kenshin.class) || AlreadyHit)
            return;
            
        if (TurnedRight == 1 && (((Kenshin) getWorld().getObjects(Kenshin.class).get(0)).getX() - getX() > 2))
        {
            ((Kenshin) getWorld().getObjects(Kenshin.class).get(0)).SetHurtDir(1);
            ((Kenshin) getWorld().getObjects(Kenshin.class).get(0)).SetBeingHit();
            AlreadyHit = true;
            return;
        }
        if (TurnedRight == 0 && (((Kenshin) getWorld().getObjects(Kenshin.class).get(0)).getX() - getX() < -2))
        {
            ((Kenshin) getWorld().getObjects(Kenshin.class).get(0)).SetHurtDir(0);
            ((Kenshin) getWorld().getObjects(Kenshin.class).get(0)).SetBeingHit();
            AlreadyHit = true;
        }
    }
    public void CheckForHit_Special(int atk_num)
    {
        if (!isTouching(Kenshin.class) || AlreadyHit)
            return;
            
        if (TurnedRight == 1 && (((Kenshin) getWorld().getObjects(Kenshin.class).get(0)).getX() - getX() > 2))
        {
            ((Kenshin) getWorld().getObjects(Kenshin.class).get(0)).SetHurtDir(1);
            ((Kenshin) getWorld().getObjects(Kenshin.class).get(0)).SetBeingHitSpecial();
            ((Kenshin) getWorld().getObjects(Kenshin.class).get(0)).SetBeingHitSpecialNum(atk_num);
            AlreadyHit = true;
            return;
        }
        if (TurnedRight == 0 && (((Kenshin) getWorld().getObjects(Kenshin.class).get(0)).getX() - getX() < -2))
        {
            ((Kenshin) getWorld().getObjects(Kenshin.class).get(0)).SetHurtDir(0);
            ((Kenshin) getWorld().getObjects(Kenshin.class).get(0)).SetBeingHitSpecial();
            ((Kenshin) getWorld().getObjects(Kenshin.class).get(0)).SetBeingHitSpecialNum(atk_num);
            AlreadyHit = true;
        }
    }
    public void DecreaseHP(int dmg)
    {
        HP -= dmg;
        if (HP <= 0)
            KO();
        ((MainPage) getWorld()).ShishioHPbar.subtract(dmg);
    }
    public void IncreaseSP()
    {
        if (SP == 100 || SP_change++ % SP_ADD_FREQ != 0 || SpecialAttack != 0)
            return;
            
        ++SP;
        ((MainPage) getWorld()).ShishioSPbar.add(1);
    }
    public boolean CheckForSP(int n)
    {
        if (SP == 0)
        {
            SpecialAttack = -10;
            return true;
        }
        if (SP_start++ % n == 0)
            DecreaseSP(1);
            
        return false;
    }
    public boolean CheckForSP2(int sp)
    {
        if (SP - sp < 0)
        { 
            SpecialAttack = -10;
            return true;
        }
        DecreaseSP(sp);
        return false;
    }
    public void DecreaseSP(int n)
    {
        SP -= n;
        ((MainPage) getWorld()).ShishioSPbar.subtract(n);
    }
    public void KO()
    {
        ((MainPage) getWorld()).GameEnded = true;
        BeingHit = false;
        BeingHitSpecial = true;
        ((MainPage) getWorld()).SetKO();
    }
}

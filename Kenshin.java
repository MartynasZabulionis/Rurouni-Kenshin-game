import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Kenshin here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

public class Kenshin extends Actor
{
    // Controls
    private final String MOVE_LEFT = "a";
    private final String MOVE_RIGHT = "d";
    private final String JUMP = "w";
    private final String GUARD = "s";
    private final String ATTACK = "space";
    private final String ATTACK_UP = "b";
    private final String SPECIAL1 = "n";
    private final String SPECIAL2 = "m";
    private final String KUZURYUSEN = ",";
    private final String SOURYUSEN = ".";
    
    // Damages
    private final int SIMPLE_DAMAGE = 1;
    
    private final int SPECIAL1_DAMAGE = 50;
    private final int KAGUZUCHI_DAMAGE = 70;
    private final int HOMURADAMA1_DAMAGE = 40;
    private final int HOMURADAMA2_DAMAGE = 30;
    
    // Constants
    private final int SPECIAL1_SP = 50;
    private final int SPECIAL2_SP = 40;
    private final int KUZURYUSEN_SP = 50;
    private final int SOURYUSEN_SP = 70;
    
    private final int WALK_SPEED[] = {-4, 4};
    private final int HURT_MOVE_SPEED[] = {-1, 1};
    private final int DODGE_MOVE_SPEED[] = {-3, 3};
    private final int HURT_SPECIAL_MOVE_SPEED[] = {-7, 7};
    private final int BIGGER_IMAGE = 20;
    private final int SP_ADD_FREQ = 20;
    
    private GreenfootImage kenshin_standup[][] = {new GreenfootImage[3], new GreenfootImage[3]};
    private GreenfootImage kenshin_fall[][] = {new GreenfootImage[7], new GreenfootImage[7]};
    private GreenfootImage kenshin_hurt[][] = {new GreenfootImage[4], new GreenfootImage[4]};
    
    private GreenfootImage kenshin_stance[] = new GreenfootImage[2];
    
    private GreenfootImage kenshin_move[][] = {new GreenfootImage[8], new GreenfootImage[8]};
    
    private GreenfootImage kenshin_jump[][] = {new GreenfootImage[8], new GreenfootImage[8]};
    
    private GreenfootImage kenshin_guard[][] = {new GreenfootImage[4], new GreenfootImage[4]};
   
    private GreenfootImage kenshin_attack[][][] = {{new GreenfootImage[5], new GreenfootImage[5]}, {new GreenfootImage[5], new GreenfootImage[5]}};
    
    private GreenfootImage kenshin_attack_aerial[][] = {new GreenfootImage[7], new GreenfootImage[7]};
    private GreenfootImage kenshin_attack_upwards[][] = {new GreenfootImage[8], new GreenfootImage[8]};
    
    private GreenfootImage kenshin_specialattack1[][] = {new GreenfootImage[14], new GreenfootImage[14]};
    private GreenfootImage kenshin_specialattack2[][] = {new GreenfootImage[16], new GreenfootImage[16]};
    private GreenfootImage kenshin_kuzuryusen[][] = {new GreenfootImage[13], new GreenfootImage[13]};
    private GreenfootImage kenshin_souryusen[][] = {new GreenfootImage[16], new GreenfootImage[16]};
   
    private int ImageCounter = 0;
    private int ChangeFrame = 0;
    private int TurnedRight = 1;
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

    public Kenshin()
    {
        
        kenshin_stance[1] = new GreenfootImage("Kenshin/Stance/1.png");
        kenshin_stance[0] = mirrorHorizontally(kenshin_stance[1]);
        
        kenshin_stance[1].scale(kenshin_stance[1].getWidth() + BIGGER_IMAGE, kenshin_stance[1].getHeight() + BIGGER_IMAGE);
        kenshin_stance[0].scale(kenshin_stance[0].getWidth() + BIGGER_IMAGE, kenshin_stance[0].getHeight() + BIGGER_IMAGE);
        
        String image_str;
        for (int i = 0; i < 8; ++i)
        {
            image_str = String.format("Kenshin/Walk/%d.png", i + 1);
            kenshin_move[1][i] = new GreenfootImage(image_str);
            kenshin_move[0][i] = mirrorHorizontally(kenshin_move[1][i]);
            
            kenshin_move[1][i].scale(kenshin_move[1][i].getWidth() + BIGGER_IMAGE, kenshin_move[1][i].getHeight() + BIGGER_IMAGE);
            kenshin_move[0][i].scale(kenshin_move[0][i].getWidth() + BIGGER_IMAGE, kenshin_move[0][i].getHeight() + BIGGER_IMAGE);
        }
        
        for (int i = 0; i < 8; ++i)
        {
            image_str = String.format("Kenshin/Jump/%d.png", i + 1);
            kenshin_jump[1][i] = new GreenfootImage(image_str);
            kenshin_jump[0][i] = mirrorHorizontally(kenshin_jump[1][i]);
            
            kenshin_jump[1][i].scale(kenshin_jump[1][i].getWidth() + BIGGER_IMAGE, kenshin_jump[1][i].getHeight() + BIGGER_IMAGE);
            kenshin_jump[0][i].scale(kenshin_jump[0][i].getWidth() + BIGGER_IMAGE, kenshin_jump[0][i].getHeight() + BIGGER_IMAGE);
        }
        
        for (int i = 0; i < 4; ++i)
        {
            image_str = String.format("Kenshin/Guard/%d.png", i + 1);
            kenshin_guard[1][i] = new GreenfootImage(image_str);
            kenshin_guard[0][i] = mirrorHorizontally(kenshin_guard[1][i]);
            
            kenshin_guard[1][i].scale(kenshin_guard[1][i].getWidth() + BIGGER_IMAGE, kenshin_guard[1][i].getHeight() + BIGGER_IMAGE);
            kenshin_guard[0][i].scale(kenshin_guard[0][i].getWidth() + BIGGER_IMAGE, kenshin_guard[0][i].getHeight() + BIGGER_IMAGE);
        }
        
        for (int i = 0; i < 5; ++i)
        {
            for (int y = 0; y < 2; ++y)
            {
                image_str = String.format("Kenshin/Attack%d/%d.png", y + 1, i + 1);
                kenshin_attack[1][y][i] = new GreenfootImage(image_str);
                kenshin_attack[0][y][i] = mirrorHorizontally(kenshin_attack[1][y][i]);
                
                kenshin_attack[1][y][i].scale(kenshin_attack[1][y][i].getWidth() + BIGGER_IMAGE, kenshin_attack[1][y][i].getHeight() + BIGGER_IMAGE);
                kenshin_attack[0][y][i].scale(kenshin_attack[0][y][i].getWidth() + BIGGER_IMAGE, kenshin_attack[0][y][i].getHeight() + BIGGER_IMAGE);
            }
        }
        for (int i = 0; i < 7; ++i)
        {
            image_str = String.format("Kenshin/Attack_Aerial/%d.png", i + 1);
            kenshin_attack_aerial[1][i] = new GreenfootImage(image_str);
            kenshin_attack_aerial[0][i] = mirrorHorizontally(kenshin_attack_aerial[1][i]);
            
            kenshin_attack_aerial[1][i].scale(kenshin_attack_aerial[1][i].getWidth() + BIGGER_IMAGE, kenshin_attack_aerial[1][i].getHeight() + BIGGER_IMAGE);
            kenshin_attack_aerial[0][i].scale(kenshin_attack_aerial[0][i].getWidth() + BIGGER_IMAGE, kenshin_attack_aerial[0][i].getHeight() + BIGGER_IMAGE);
        }
        for (int i = 0; i < 8; ++i)
        {
            image_str = String.format("Kenshin/Attack_Upwards/%d.png", i + 1);
            kenshin_attack_upwards[1][i] = new GreenfootImage(image_str);
            kenshin_attack_upwards[0][i] = mirrorHorizontally(kenshin_attack_upwards[1][i]);
            
            kenshin_attack_upwards[1][i].scale(kenshin_attack_upwards[1][i].getWidth() + BIGGER_IMAGE, kenshin_attack_upwards[1][i].getHeight() + BIGGER_IMAGE);
            kenshin_attack_upwards[0][i].scale(kenshin_attack_upwards[0][i].getWidth() + BIGGER_IMAGE, kenshin_attack_upwards[0][i].getHeight() + BIGGER_IMAGE);
        }
        for (int i = 0; i < 14; ++i)
        {
            image_str = String.format("Kenshin/Special1/%d.png", i + 1);
            kenshin_specialattack1[1][i] = new GreenfootImage(image_str);
            kenshin_specialattack1[0][i] = mirrorHorizontally(kenshin_specialattack1[1][i]);
            
            kenshin_specialattack1[1][i].scale(kenshin_specialattack1[1][i].getWidth() + BIGGER_IMAGE, kenshin_specialattack1[1][i].getHeight() + BIGGER_IMAGE);
            kenshin_specialattack1[0][i].scale(kenshin_specialattack1[0][i].getWidth() + BIGGER_IMAGE, kenshin_specialattack1[0][i].getHeight() + BIGGER_IMAGE);
        }
        for (int i = 0; i < 16; ++i)
        {
            image_str = String.format("Kenshin/Special2/%d.png", i + 1);
            kenshin_specialattack2[1][i] = new GreenfootImage(image_str);
            kenshin_specialattack2[0][i] = mirrorHorizontally(kenshin_specialattack2[1][i]);
            
            kenshin_specialattack2[1][i].scale(kenshin_specialattack2[1][i].getWidth() + BIGGER_IMAGE, kenshin_specialattack2[1][i].getHeight() + BIGGER_IMAGE);
            kenshin_specialattack2[0][i].scale(kenshin_specialattack2[0][i].getWidth() + BIGGER_IMAGE, kenshin_specialattack2[0][i].getHeight() + BIGGER_IMAGE);
        }
        for (int i = 0; i < 13; ++i)
        {
            image_str = String.format("Kenshin/KuzuRyuSen/%d.png", i + 1);
            kenshin_kuzuryusen[1][i] = new GreenfootImage(image_str);
            kenshin_kuzuryusen[0][i] = mirrorHorizontally(kenshin_kuzuryusen[1][i]);
            
            kenshin_kuzuryusen[1][i].scale(kenshin_kuzuryusen[1][i].getWidth() + BIGGER_IMAGE, kenshin_kuzuryusen[1][i].getHeight() + BIGGER_IMAGE);
            kenshin_kuzuryusen[0][i].scale(kenshin_kuzuryusen[0][i].getWidth() + BIGGER_IMAGE, kenshin_kuzuryusen[0][i].getHeight() + BIGGER_IMAGE);
        }
        for (int i = 0; i < 16; ++i)
        {
            image_str = String.format("Kenshin/SouRyuSen/%d.png", i + 1);
            kenshin_souryusen[1][i] = new GreenfootImage(image_str);
            kenshin_souryusen[0][i] = mirrorHorizontally(kenshin_souryusen[1][i]);
            
            kenshin_souryusen[1][i].scale(kenshin_souryusen[1][i].getWidth() + BIGGER_IMAGE, kenshin_souryusen[1][i].getHeight() + BIGGER_IMAGE);
            kenshin_souryusen[0][i].scale(kenshin_souryusen[0][i].getWidth() + BIGGER_IMAGE, kenshin_souryusen[0][i].getHeight() + BIGGER_IMAGE);
        }
        for (int i = 0; i < 4; ++i)
        {
            image_str = String.format("Kenshin/Hurt/%d.png", i + 1);
            kenshin_hurt[1][i] = new GreenfootImage(image_str);
            kenshin_hurt[0][i] = mirrorHorizontally(kenshin_hurt[1][i]);
            
            kenshin_hurt[1][i].scale(kenshin_hurt[1][i].getWidth() + BIGGER_IMAGE, kenshin_hurt[1][i].getHeight() + BIGGER_IMAGE);
            kenshin_hurt[0][i].scale(kenshin_hurt[0][i].getWidth() + BIGGER_IMAGE, kenshin_hurt[0][i].getHeight() + BIGGER_IMAGE);
        }
        for (int i = 0; i < 7; ++i)
        {
            image_str = String.format("Kenshin/Fall/%d.png", i + 1);
            kenshin_fall[1][i] = new GreenfootImage(image_str);
            kenshin_fall[0][i] = mirrorHorizontally(kenshin_fall[1][i]);
            
            kenshin_fall[1][i].scale(kenshin_fall[1][i].getWidth() + BIGGER_IMAGE, kenshin_fall[1][i].getHeight() + BIGGER_IMAGE);
            kenshin_fall[0][i].scale(kenshin_fall[0][i].getWidth() + BIGGER_IMAGE, kenshin_fall[0][i].getHeight() + BIGGER_IMAGE);
        }
        for (int i = 0; i < 3; ++i)
        {
            image_str = String.format("Kenshin/StandUP/%d.png", i + 1);
            kenshin_standup[1][i] = new GreenfootImage(image_str);
            kenshin_standup[0][i] = mirrorHorizontally(kenshin_standup[1][i]);
            
            kenshin_standup[1][i].scale(kenshin_standup[1][i].getWidth() + BIGGER_IMAGE, kenshin_standup[1][i].getHeight() + BIGGER_IMAGE);
            kenshin_standup[0][i].scale(kenshin_standup[0][i].getWidth() + BIGGER_IMAGE, kenshin_standup[0][i].getHeight() + BIGGER_IMAGE);
        }
    }
    public void addedToWorld(World world)
    {
        setImage(kenshin_stance[TurnedRight]);
    }

    /**
     * Act - do whatever the Kenshin wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    
    public void act() 
    {
        IncreaseSP();
        if (BeingHit && (SpecialAttack == 0 || SpecialAttack % 2 != 0))
        {
            if (Guarding && (TurnedRight != (((Shishio) getWorld().getObjects(Shishio.class).get(0)).GetTurnedRight())))
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
                setImage(kenshin_hurt[TurnedRight][Greenfoot.getRandomNumber(4)]);
            return;
        }
        if (BeingHitSpecial)
        {
            switch (BeingHitSpecialNum)
            {
                case 2: DecreaseHP(HOMURADAMA1_DAMAGE); break;
                case 4: DecreaseHP(KAGUZUCHI_DAMAGE); break;
                case 6: DecreaseHP(SPECIAL1_DAMAGE); break;
                case 8: DecreaseHP(HOMURADAMA2_DAMAGE); break;
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
                if (ImageCounter == 3)
                {
                    HurtSpecial = ChangeFrame = ImageCounter = 0;
                    return;
                }
                if (ChangeFrame++ % 10 == 0)
                    setImage(kenshin_standup[TurnedRight][ImageCounter++ % 3]);
                return;
            }
            
            if (ImageCounter == 7)
            {
                HurtSpecial = 43;
                //BeingHit = false;
                return;
            }
            setLocation(getX() + HURT_SPECIAL_MOVE_SPEED[HurtDir], Jumping ? (getY() + 2 > 300 ? 300 : getY() + 2) : getY());
            if (ChangeFrame++ % 7 == 0)
                setImage(kenshin_fall[TurnedRight][ImageCounter++ % 7]);
            return;
        }
        if (SpecialAttack < -5)
        {
            setImage(kenshin_specialattack1[TurnedRight][1]);
            ++SpecialAttack;
            return;
        }
        if (SpecialAttack < 0)
        {
            setImage(kenshin_specialattack1[TurnedRight][0]);
            ++SpecialAttack;
            return;
        }
        
        if (Attacking && !Attacking_Aerial)
        {
            AttackNotAerial();
            return;
        }
        
        if (Guarding && !Greenfoot.isKeyDown(GUARD))
        {
            setImage(kenshin_guard[TurnedRight][0]);
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
            setImage(kenshin_jump[TurnedRight][0]);
            //return;
        }
        else if (Jumping)
        {
            JumpJumpJump();
            return;
        }
        
        if (!Attacking && SpecialAttack == 0 && Greenfoot.isKeyDown(ATTACK))
        {
            Attacking = true;
            ChangeFrame = ImageCounter = 0;
            AttackType = Greenfoot.getRandomNumber(2);
            setImage(kenshin_attack[TurnedRight][AttackType][0]);
            return;
        }
        if (!Attacking && SpecialAttack == 0 && Greenfoot.isKeyDown(ATTACK_UP))
        {
            Attacking = AttackingUpwards = true;
            ChangeFrame = ImageCounter = 0;
            setImage(kenshin_attack_upwards[TurnedRight][0]);
            return;
        }
        
        
        if (SpecialAttack == 2 || (SpecialAttack < 2 && Greenfoot.isKeyDown(SPECIAL1)))
        {
            SpecialAttack1();  
            
            return;
        }
        if (SpecialAttack == 4 || SpecialAttack == 10 || (Greenfoot.isKeyDown(SOURYUSEN) && (SpecialAttack == 0 || SpecialAttack == 3)))
        {
            Souryusen();   
            return;
        }
        if (SpecialAttack == 6 || (Greenfoot.isKeyDown(KUZURYUSEN) && (SpecialAttack == 0 || SpecialAttack == 5)))
        {
            Kuzuryusen();    
            return;
        }
        if (SpecialAttack == 8 || SpecialAttack == 9 || (Greenfoot.isKeyDown(SPECIAL2) && (SpecialAttack == 0 || SpecialAttack == 7)))
        {
            SpecialAttack2();   
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
        if (SpecialAttack != 0)
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
            if (ImageCounter == 8)
            {
                Attacking = AttackingUpwards = AlreadyHit = false;
                ImageCounter = ChangeFrame = JumpHorizontal = 0;
                return;
            }
            CheckForHit_Simple();
            if (ChangeFrame++ % 4 == 0)
                    setImage(kenshin_attack_upwards[TurnedRight][ImageCounter++ % 8]);
            return;
        }
        if (ImageCounter == 5)
        {
            Attacking = AlreadyHit = false;
            ImageCounter = ChangeFrame = JumpHorizontal = 0;
            return;
        }
        CheckForHit_Simple();
        if (ChangeFrame++ % 3 == 0)
                setImage(kenshin_attack[TurnedRight][AttackType][ImageCounter++ % 5]);
        
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
            if (ImageCounter == 7)
            {
                Attacking = Attacking_Aerial = AlreadyHit = false;
                ImageCounter = 2;
                ChangeFrame = 0;
                return;
            }
            CheckForHit_Simple();
            if (ChangeFrame++ % 5 == 0)
                setImage(kenshin_attack_aerial[TurnedRight][ImageCounter++ % 7]);
                
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
            setImage(kenshin_jump[TurnedRight][ImageCounter++ % 8]);
    }
    public void Guard()
    {
        if (!Guarding)
        {
            setImage(kenshin_guard[TurnedRight][0]);
            Guarding = true;
            return;
        }
        setImage(kenshin_guard[TurnedRight][2]);
        return;
    }
    public void SpecialAttack1()
    {
        if (SetNewSpecial(1, kenshin_specialattack1[TurnedRight][0]))
                return;
        
        if (SpecialAttack == 2)
        {
            CheckForHit_Special(2);
        }
        if (ImageCounter == 14)
        {
            SpecialAttack = 0;
            ImageCounter = ChangeFrame = 0;
            AlreadyHit = false;
            return;
        }
        if (ImageCounter >= 2)
        {
            if (ImageCounter == 2 && ChangeFrame != 0)
                ChangeFrame = 0;
                
            if (ImageCounter == 2 && CheckForSP2(SPECIAL1_SP))
                return;
                
            SpecialAttack = 2;
            
            if (ChangeFrame++ % 7 == 0)
                setImage(kenshin_specialattack1[TurnedRight][ImageCounter++ % 14]);
            return;
        }
        if (CheckForSP(3))
            return;
        if (ChangeFrame++ % 7 == 0)
                setImage(kenshin_specialattack1[TurnedRight][ImageCounter++ % 14]);
    }
    public void Souryusen()
    {
        if (SetNewSpecial(3, kenshin_souryusen[TurnedRight][0]))
                return;
                
        
        if (ImageCounter == 7)
        {
            if (CheckForSP2(SPECIAL1_SP))
                return;
                
            SpecialAttack = 4;
            setImage(kenshin_souryusen[TurnedRight][7]);
            
            ChangeFrame = ImageCounter = 0;
            
            if (isTouching(Shishio.class))
            {
                SpecialAttack = 10;
                ChangeFrame = 0;
                ImageCounter = 8;
                act();
                return;
            }
                
            return;
        }
        if (SpecialAttack == 4)
        {
            if (isTouching(Shishio.class))
            {
                SpecialAttack = 10;
                ChangeFrame = 0;
                ImageCounter = 8;
                act();
                return;
            }
            else if (isAtEdge())
            {
                SpecialAttack = 0;
                ImageCounter = ChangeFrame = 0;
                return;
            }
            setLocation(getX() + WALK_SPEED[TurnedRight] * 2, getY());
            return;
        }
        if (SpecialAttack == 10)
        {
            if (ImageCounter == 16)
            {
                SpecialAttack = 0;
                ImageCounter = ChangeFrame = 0;
                AlreadyHit = false;
                return;
            }
            
            CheckForHit_Special(10);
            
            if (ChangeFrame++ % 10 == 0)
                setImage(kenshin_souryusen[TurnedRight][ImageCounter++ % 16]);
            setLocation(getX() + WALK_SPEED[TurnedRight], getY());
        }
        if (CheckForSP(5))
            return;
            
        if (ChangeFrame++ % 10 == 0)
                setImage(kenshin_souryusen[TurnedRight][ImageCounter++ % 16]);
    }
    public void Kuzuryusen()
    {
        if (SetNewSpecial(5, kenshin_kuzuryusen[TurnedRight][0]))
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
                    setImage(kenshin_kuzuryusen[TurnedRight][ImageCounter++ % 13]);
                
            if (ImageCounter >= 10)
                return;
                
            if (TurnedRight == 1)
                setLocation(getX() + 8, getY());
            else
                setLocation(getX() - 8, getY());
                
            return;
        }
        else if (CheckForSP(10))
            return;    
        
        if (ImageCounter >= 6)
        {
            if (Greenfoot.isKeyDown(TurnedRight == 1 ? MOVE_RIGHT : MOVE_LEFT))
            {
                if (CheckForSP2(KUZURYUSEN_SP))
                    return;
                    
                SpecialAttack = 6;
                act();
                return;
            }
            return;
        }
        /*if (CheckForSP(5))
            return;*/
        if (ChangeFrame++ % 10 == 0)
                setImage(kenshin_kuzuryusen[TurnedRight][ImageCounter++ % 13]);
    }
    public void SpecialAttack2()
    {
        if (SetNewSpecial(7, kenshin_specialattack2[TurnedRight][0]))
                return;
                
        if (SpecialAttack == 9 && !Greenfoot.isKeyDown(SPECIAL2))
        {
            SpecialAttack = 8;
            ChangeFrame = 0;
            ImageCounter = 4;
        }
        if (SpecialAttack == 8)
        {
            if (ImageCounter == 4 && CheckForSP2(SPECIAL2_SP))
                return;
                
            CheckForHit_Special(8);
            if (ImageCounter == 16)
            {
                SpecialAttack = /*ImageCounter =*/ 0;
                ImageCounter = ChangeFrame = 0;
                AlreadyHit = false;
                return;
            }
            setLocation(getX() + WALK_SPEED[TurnedRight] / 2, getY());
            if (ChangeFrame++ % 7 == 0)
                    setImage(kenshin_specialattack2[TurnedRight][++ImageCounter % 16]);
            return;
        }
        if (ImageCounter == 3 || ImageCounter == 4)
        {
            if (CheckForSP(15))
                return;
                
            SpecialAttack = 9;
            if (Greenfoot.isKeyDown(TurnedRight == 1 ? MOVE_RIGHT : MOVE_LEFT))
            {
                if (ChangeFrame++ % 10 == 0)
                {
                    if (ImageCounter == 3)
                        ++ImageCounter;
                    else
                        --ImageCounter;
                }
                setImage(kenshin_specialattack2[TurnedRight][ImageCounter]);
                setLocation(getX() + WALK_SPEED[TurnedRight], getY());
            }
            else
                setImage(kenshin_specialattack2[TurnedRight][2]);
            return;
        }
        if (CheckForSP(5))
            return;

        if (ChangeFrame++ % 14 == 0)
                setImage(kenshin_specialattack2[TurnedRight][ImageCounter++ % 16]);
    }
    public void MoveRight()
    {
        TurnedRight = 1;
        setLocation(getX() + WALK_SPEED[1], getY());
        
        if (Jumping)
            return;
        
        if (ChangeFrame++ % 10 == 0)
            ImageCounter++;
        setImage(kenshin_move[TurnedRight][ImageCounter % 4]);
            
        
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
        setImage(kenshin_move[TurnedRight][ImageCounter % 4]);
        
        
        //JumpHorizontal = -WALK_SPEED;
    }
    public void Stance()
    {
        ImageCounter = JumpHorizontal = 0;
        setImage(kenshin_stance[TurnedRight]);
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
        if (!isTouching(Shishio.class) || AlreadyHit)
            return;
            
        if (TurnedRight == 1 && (((Shishio) getWorld().getObjects(Shishio.class).get(0)).getX() - getX() > 2))
        {
            ((Shishio) getWorld().getObjects(Shishio.class).get(0)).SetHurtDir(1);
            ((Shishio) getWorld().getObjects(Shishio.class).get(0)).SetBeingHit();
            AlreadyHit = true;
            return;
        }
        if (TurnedRight == 0 && (((Shishio) getWorld().getObjects(Shishio.class).get(0)).getX() - getX() < -2))
        {
            ((Shishio) getWorld().getObjects(Shishio.class).get(0)).SetHurtDir(0);
            ((Shishio) getWorld().getObjects(Shishio.class).get(0)).SetBeingHit();
            AlreadyHit = true;
        }
    }
    public void CheckForHit_Special(int atk_num)
    {
        if (!isTouching(Shishio.class) || AlreadyHit)
            return;
            
        if (TurnedRight == 1 && (((Shishio) getWorld().getObjects(Shishio.class).get(0)).getX() - getX() > 2))
        {
            ((Shishio) getWorld().getObjects(Shishio.class).get(0)).SetHurtDir(1);
            ((Shishio) getWorld().getObjects(Shishio.class).get(0)).SetBeingHitSpecial();
            ((Shishio) getWorld().getObjects(Shishio.class).get(0)).SetBeingHitSpecialNum(atk_num);
            AlreadyHit = true;
            return;
        }
        if (TurnedRight == 0 && (((Shishio) getWorld().getObjects(Shishio.class).get(0)).getX() - getX() < -2))
        {
            ((Shishio) getWorld().getObjects(Shishio.class).get(0)).SetHurtDir(0);
            ((Shishio) getWorld().getObjects(Shishio.class).get(0)).SetBeingHitSpecial();
            ((Shishio) getWorld().getObjects(Shishio.class).get(0)).SetBeingHitSpecialNum(atk_num);
            AlreadyHit = true;
        }
    }
    public void DecreaseHP(int dmg)
    {
        HP -= dmg;
        if (HP <= 0)
            KO();
        ((MainPage) getWorld()).KenshinHPbar.subtract(dmg);
    }
    public void IncreaseSP()
    {
        if (SP == 100 || SP_change++ % SP_ADD_FREQ != 0 || SpecialAttack != 0)
            return;
            
        ++SP;
        ((MainPage) getWorld()).KenshinSPbar.add(1);
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
        ((MainPage) getWorld()).KenshinSPbar.subtract(n);
    }
    public void KO()
    {
        ((MainPage) getWorld()).GameEnded = true;
        BeingHit = false;
        BeingHitSpecial = true;
        ((MainPage) getWorld()).SetKO();
    }
}

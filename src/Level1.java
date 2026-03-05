// imports Actor, World, Greenfoot, GreenfootImage
import greenfoot.*;

/**
 * @author SAE
 * @version 1.0
 */
public class Level1 extends World
{
    
    /**
     * Erzeuge eine Welt.
     */
    public Level1()
    {
        super(10, 10,60);
        setBackground("cell.jpg");
        setPaintOrder(Star.class, Player.class, GoldNugget.class, Carrot.class, Rock.class);
        Monster monster =  new Monster();
        addObject(monster, 8, 3);
        Merchant merchant = new Merchant();
        addObject(merchant, 0, 0);
        Player player =  new Player(100);
        addObject(player, 3, 3);
        Rock rock = new Rock(10);
        addObject(rock,5,  5);
        Carrot carrot=new Carrot();
        addObject(carrot,1,  5);
        Carrot carrot1 = new Carrot(3);
        addObject(carrot1,1,  6);
        for(int i = 0; i<8;i++){
            addObject(new Rock(10),7,  i);
        }
    }




}
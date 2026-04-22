// imports Actor, World, Greenfoot, GreenfootImage
import greenfoot.*;

import java.util.List;

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
        setPaintOrder(HotbarSelector.class,InventorySlot.class, Star.class, Player.class,IronFence.class, Lever.class, GoldNugget.class, Edible.class, Rock.class);
        Monster monster =  new Monster();
        addObject(monster, 8, 3);
        Merchant merchant = new Merchant();
        addObject(merchant, 0, 0);
        Player player =  new Player(100);
        addObject(player, 3, 3);
        Rock rock = new Rock(10, 15);
        addObject(rock,5,  5);
        Edible carrot=new Edible("Carrot", 5);
        addObject(carrot,1,  5);
        Edible carrot1 = new Edible("Carrot", 3);
        addObject(carrot1,1,  6);
        Sword diaSword = new Sword("diamond");
        addObject(diaSword,2,  5);
        Edible apple = new Edible("Apple", 5);
        addObject(apple,2,  6);
        Edible banana = new Edible("Banana", 20);
        addObject(banana,2,  7);

        for(int i = 0; i<8;i++){
            addObject(new Rock(10, 15),7,  i);
        }
        for(int i = 0; i< getHeight()-1;i++){
            addObject(new IronFence(), 9 ,i);
        }
        Lever lever = new Lever(false);
        addObject(lever,8,5);

    }
    public void act(){
        checkForLevelEnd();
    }
    public void checkForLevelEnd() {
        List<Lever> leverList = getObjects(Lever.class);
        if (!leverList.isEmpty()) {
            Lever lever = leverList.get(0);
            if (lever.getState() == true) {
                for (int i =0; i<getHeight()-1;i++){
                    lever.removeUndestructable(9, i);
                }
            }
        }
    }





}
import greenfoot.*;



public class Level2 extends World{

    public Level2(Player Player1){
        super(20,15,60);
        setBackground("cell.jpg");
        setPaintOrder(HotbarSelector.class,InventorySlot.class, Star.class, Player.class,Tree.class,IronFence.class, Lever.class, GoldNugget.class, Edible.class, Rock.class);
        addObject(Player1, 0, 5);
        Potion healingPortion = new Potion();
        addObject(healingPortion, 3, 3);
        Rock rock = new Rock(10, 20);
        for(int i = 0; i<4;i++){
            addObject(new Rock(10, 15), 4, i);
        }
        for (int i= 4; i<10; i++){
            addObject(new Rock(10, 15), i , 4);
        }
        addObject(new Tree(100), 10,10);
        addObject(new Monster(), 8,8);
        addObject(new Monster(), 9,13);



    }
}

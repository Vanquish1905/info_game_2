import greenfoot.World;

public class MerchantWorld extends World{
    public MerchantWorld(Player player1){
        super(15,15,60);
        setBackground("cell.jpg");
        setPaintOrder(Star.class, Player.class, Carrot.class, Rock.class);
        addObject(player1, 1, 5);
        addObject(new Gold25Preis(), 3, 4);
        addObject(new FullLive(), 5, 4);
        addObject(new Gold20Preis(), 3, 6);
        addObject(new more5Damage(), 5, 6);
        //Level2 nextLevel = new Level2(player1); // Removed immediate instantiation
        Teleporter tp = new Teleporter(Level2.class);
        addObject(tp, 9, 5);
        for(int i = 2; i<15;i++){
            if (i != 9) { // Don't spawn rock on teleporter
                addObject(new Rock(0),i,  5);
            }
        }
        for(int i = 0; i<15;i++){
            addObject(new Rock(0),i,  3);
        }
        for(int i = 0; i<15;i++){
            addObject(new Rock(0),i,  7);
        }

    }

}

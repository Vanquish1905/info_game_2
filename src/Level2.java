import greenfoot.*;



public class Level2 extends World{

    public Level2(Player Player1){
        super(20,20,60);
        setBackground("cell.jpg");
        setPaintOrder(Star.class, Player.class, Carrot.class, Rock.class);
        addObject(Player1, 0, 5);



    }
}

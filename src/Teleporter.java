import greenfoot.*;

public class Teleporter extends ImprovedActor {
    private Class<? extends World> destinationClass;


    public Teleporter(Class<? extends World> destinationClass) {
        this.destinationClass = destinationClass;
        setImage("Teleporter.png");
    }

    public void act() {
        checkCollision();
    }

    private void checkCollision() {

        Player player = (Player) getOneIntersectingObject(Player.class);

        if (player != null) {
            try {
                World nextLevel = destinationClass.getConstructor(Player.class).newInstance(player);
                Greenfoot.setWorld(nextLevel);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

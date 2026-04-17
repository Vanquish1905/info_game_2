import greenfoot.Greenfoot;

public class Tree extends WorldBuildingObject implements Destructible, NotWalkthroughObject {
    public Tree(int hardness){
        setImage("Tree.png");
        setLife(hardness);
    }
    public Tree(){
        setImage("Tree.png");
        setLife(100);
    }

    //Methoden


    @Override
    public void onDestruction(MovingActor trigger){
        getWorld().addObject(new Star(), getX(), getY()); //Bei einem Treffer wird kurz ein Stern eingeblendet
        setLife(getLife()-trigger.getDestructableDamage());
        if(getLife()<=0){
            getWorld().removeObject(this);
        }
    }
}

import greenfoot.Greenfoot;

public class Potion extends Item implements Pickable, Usable{
    public Potion(){
        setImage("potion.png");
    }
    @Override
    public Item onPick(MovingActor trigger){
        getWorld().removeObject(this);
        if(Greenfoot.getRandomNumber(5)==1){
            Greenfoot.playSound("glassBreaking.mp3");
            return null;
        }
        return this;
    }
    @Override
    public void onUse(MovingActor trigger){
        trigger.setLife(100);
        getWorld().removeObject(this);
    }

}

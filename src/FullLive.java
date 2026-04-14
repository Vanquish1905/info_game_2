public class FullLive extends Item implements Pickable {
    public FullLive(){
        setImage("FullLive.png");
        getImage().scale(60, 60);

    }
    @Override
    public Item onPick(MovingActor trigger){
        trigger.setLife(100);
        getWorld().removeObject(this);
        return null;
    }

}

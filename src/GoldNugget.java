public class GoldNugget extends Item implements Pickable{

    public GoldNugget(){
        setImage("GoldNugget.gif");
        getImage().scale(20, 20);

    }
    @Override
    public Item onPick(MovingActor trigger){
        trigger.setGold(trigger.getGold()+1);
        getWorld().removeObject(this);
        return null;
    }


}

public class more5Damage extends Item implements Pickable {
    public more5Damage(){
        setImage("+5 Damage.png");
        getImage().scale(60, 60);

    }
    @Override
    public Item onPick(MovingActor trigger){
        trigger.setDamage(trigger.getDamage()+5);
        trigger.setStartingDamage(trigger.getStartingDamage()+5);
        getWorld().removeObject(this);
        return null;
    }
}

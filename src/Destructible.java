public class Destructible extends ImprovedActor{
    public int getHardness() {
        return hardness;
    }

    public void setHardness(int hardness) {
        this.hardness = hardness;
    }

    private int hardness;








    public void hit(MovingActor hitter){
        getWorld().addObject(new Star(), getX(), getY()); //Bei einem Treffer wird kurz ein Stern eingeblendet
        setHardness(getHardness()-hitter.getDestructableDamage());
        if(getHardness()<=0){
            getWorld().removeObject(this);
        }
    }
}

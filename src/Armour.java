public class Armour extends Item implements Pickable, Usable{
    private boolean Picked;
    private int Durability;
    private int Protection;
    private ArmourType currentType;

    public int getProtection() {
        return Protection;
    }
    public void setProtection(int protection) {
        Protection = protection;
    }
    public int getDurability(){return Durability;}
    public void setDurability(int durability){Durability=durability;}
    public boolean isPicked() {
        return Picked;
    }
    public void setPicked(boolean picked) {
        Picked = picked;
    }


    @Override
    public Item onPick(MovingActor trigger){
        getWorld().removeObject(this);
        setPicked(true);
        return this;
    }
    @Override
    public void onUse(MovingActor trigger){
        if (!isPicked()){
            getWorld().removeObject(this);
        }
        trigger.setCurrentArmour(this);
    }
    public Armour(ArmourType type){
        setArmourType(type);
    }
    public void setArmourType(ArmourType type) {
        this.currentType = type;

        switch (type) {
            case Diamond:
                //setImage();
                //getImage().scale();
                setDurability(500);
                setProtection(30);
                break;
            case Iron:
                //setImage();
                //getImage().scale();
                setDurability(350);
                setProtection(20);
                break;
            case Leather:
                //setImage();
                //getImage().scale();
                setDurability(180);
                setProtection(10);
                break;
        }
    }
}

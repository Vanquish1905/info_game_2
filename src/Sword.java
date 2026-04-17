public class Sword extends Item implements Pickable, Usable {

    private int extradamage;
    private String swordType;
    public int damageBefore;


    public String getSwordtype() {
        return swordType;
    }
    public int getDamageBefore() {
        return damageBefore;
    }
    public void setDamageBefore(int damageBefore) {
        this.damageBefore = damageBefore;
    }
    public void setSwordType(String swordType) {
        this.swordType = swordType;
    }
    public int getExtradamage() {
        return extradamage;
    }

    public void setExtradamage(int damage) {
        this.extradamage = damage;
    }
    public Sword(String swordType){
        switch (swordType) {
            case "diamond":
                setImage("diamondSword.png");
                getImage().scale(60,60);
                setSwordType("diamond");
                setExtradamage(30);
                break;
            case "iron":
                setImage("ironSword.png");
                getImage().scale(60,60);
                setSwordType("iron");
                setExtradamage(20);
                break;
        }

    }
    //inumerations anschauen


    @Override
    public void onUse(MovingActor trigger){
        setDamageBefore(trigger.getDamage());
        trigger.setDamage(trigger.getDamage()+getExtradamage());
    }
    public void onNouse(MovingActor trigger){
        trigger.setDamage(getDamageBefore());
    }


}

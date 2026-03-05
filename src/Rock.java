import greenfoot.*;


public class Rock extends ImprovedActor
{
    //attribute
    private int damage;
    private int benotigteSchlaege;
    //KOnstructor
    public Rock(int rockDamage) {
        this.benotigteSchlaege = Greenfoot.getRandomNumber(4)+1;
        setDamage(rockDamage);
    }
    //setter und Getter
    public int  getBenotigteSchlaege() {
        return benotigteSchlaege;
    }

    public void setBenotigteSchlaege(int ZerstorSchlaege) {
        this.benotigteSchlaege = ZerstorSchlaege;
    }
    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

	//Methoden
    private void spawnGoldnugget(){
        getWorld().addObject(new GoldNugget(),  getX(), getY());
    }

    private void spawnGoldnuggetChance(int oneIN){
        int number = Greenfoot.getRandomNumber(oneIN);
        if(number == 1){
            spawnGoldnugget();
        }
    }

	public void hit(){
		getWorld().addObject(new Star(), getX(), getY()); //Bei einem Treffer wird kurz ein Stern eingeblendet
        setBenotigteSchlaege(getBenotigteSchlaege()-1);
		if(getBenotigteSchlaege()<=0){
            spawnGoldnuggetChance(3);
            getWorld().removeObject(this);

        }
	}

}
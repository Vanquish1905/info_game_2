import greenfoot.*;


public class Rock extends WorldBuildingObject implements Destructible, NotWalkthroughObject
{
    //attribute



    //KOnstructor
    public Rock(int rockDamage, int hardness) {
        setLife(hardness);
        setDamage(rockDamage);
    }
    //setter und Getter



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
    @Override
	public void onDestruction(MovingActor trigger){
		getWorld().addObject(new Star(), getX(), getY()); //Bei einem Treffer wird kurz ein Stern eingeblendet
        setLife(getLife()-trigger.getDestructableDamage());
		if(getLife()<=0){
            spawnGoldnuggetChance(3);
            getWorld().removeObject(this);

        }
	}

}
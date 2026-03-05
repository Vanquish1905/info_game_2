import greenfoot.Actor;

public class Star extends Actor {

	private final long destroyAfter = System.currentTimeMillis() + 500;

	public Star(){
		getImage().scale(40, 40);
	}

	public void act(){
		if (destroyAfter < System.currentTimeMillis()){
			getWorld().removeObject(this);
		}
	}

}

import java.util.List;

public class Merchant extends MovingActor{
    public Merchant(){
        setImage("Merchant.png");
        getImage().scale(60, 60);

    }
    private void callPlayer(){
        List<Player> players = getObjectsInRange(1,Player.class);
        if(!players.isEmpty()){
            say("Press B to see my shop");
        }
    }
    public void act(){
        callPlayer();
    }


}

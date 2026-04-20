import java.util.List;

public class Lever extends Item implements Usable{
    public Boolean getState() {
        return state;
    }

    public void setState(Boolean state) {
        this.state = state;
    }

    private boolean state;


    public Lever(boolean state) {
        if (state == true ){
            setImage("Lever_on.png");
            setState(true);
        } else if ( state == false) {
            setImage("Lever_off.png");
            setState(false);
        }

    }
    @Override
    public void onUse(MovingActor trigger){
        if (getState()== true){
            setState(false);
        } else if (getState()==false){
            setState(true);
        }
    }

    public void removeUndestructable(int x, int y){
        List<WorldBuildingObject> undestructables = getWorld().getObjectsAt(x ,y, WorldBuildingObject.class);
        if (!undestructables.isEmpty()){
            WorldBuildingObject undestructable = undestructables.get(0);
            getWorld().removeObject(undestructable);
        }
    }
}

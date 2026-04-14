import java.util.List;

public class Lever extends Item implements Usable{
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    private String state;


    public Lever(String state) {
        if (state == "on" || state == "On"){
            setImage("Lever_on.png");
            setState("on");
        } else if (state=="off" || state=="Off") {
            setImage("Lever_off.png");
            setState("off");
        }

    }
    @Override
    public void onUse(MovingActor trigger){
        if (getState()=="on"||getState()=="On"){
            setState("off");
        } else if (getState()=="off"||getState()=="Off"){
            setState("on");
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

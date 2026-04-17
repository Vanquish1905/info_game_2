public interface NotWalkthroughObject {
    default void onHit(MovingActor trigger){
        WorldBuildingObject worldBuildingObject = (WorldBuildingObject) this;
        worldBuildingObject.getWorld().addObject(new Star(), worldBuildingObject.getX(), worldBuildingObject.getY());
        trigger.setLife(trigger.getLife()- worldBuildingObject.getDamage());
    }
}

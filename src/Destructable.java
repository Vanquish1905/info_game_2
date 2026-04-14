public interface Destructable {
    default void onDestruction(MovingActor trigger){
        ImprovedActor Destructable = (ImprovedActor) this;
        Destructable.setLife(Destructable.getLife() - trigger.getDestructableDamage());
        if (Destructable.getLife()>=0){
            Destructable.getWorld().removeObject(Destructable);
        }
    }
}

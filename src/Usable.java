public interface Usable {
    default void onUse(MovingActor trigger){
        Item item = (Item) this;
        item.getWorld().removeObject(item);
    }
}

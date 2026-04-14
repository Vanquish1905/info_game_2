public interface Pickable {
    default Item onPick(MovingActor trigger){
        Item item = (Item) this;
        item.getWorld().removeObject(item);
        return item;
    }

}

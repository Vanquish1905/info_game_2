public class Edible extends Item implements Pickable, Usable{
    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    private int weight;

    public boolean isPicked() {
        return picked;
    }

    public void setPicked(boolean picked) {
        this.picked = picked;
    }

    private boolean picked;

    public void act(){
        draw(getWeight());
    }


    public Edible(String Fruit, int weight){
        setWeight(weight);
        setPicked(false);
        switch (Fruit){
            case "Carrot":
                setImage("karotte.png");
                getImage().scale(40, 40);
                break;
            case "Apple":
                setImage("apple1.png");
                getImage().scale(35, 35);
                break;
            case "Banana":
                setImage("bananas.png");
                getImage().scale(50, 50);
                break;
        }
    }
    @Override
    public void onUse(MovingActor trigger){
        if (trigger.getLife()<100){
            trigger.setLife(trigger.getLife() + getWeight());
        }
        if (trigger.getLife()>100){
            trigger.setLife(100);
        }
        if (!isPicked()){
            getWorld().removeObject(this);
        }

    }
    @Override
    public Item onPick(MovingActor trigger){
        Item item = (Item) this;
        item.getWorld().removeObject(item);
        setPicked(true);
        return item;

    }


}

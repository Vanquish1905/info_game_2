import greenfoot.*;
import java.util.*;

public class Player extends Character{

    //Attribute
    private final Item[] inventory = new Item[10];
    private InventoryVisualizer inventoryVisualizer;
    private final int TOTAL_SLOTS = 10;
    private boolean isListening = false;




    //Konstruktoren
    //this. immer zum bestimmen der Value vom Object(Object muss voher definieret worden sein in Attributen)

    public Player(int startingLives) {
        setLife(startingLives);
        setDamage(20);
        setGold(100);
        setDestructableDamage(5);
        setFistDamage(20);
    }

    //Methoden


    //act
    public void act() {
        getsHitByMonster();
        enterLevel2();
        selectSlot();
        checkControlls();
        calculateDamage();
    }




    //Methoden

    //debug
    private void debugValues() {
       //debug
        System.out.println("lifes: " + getLife());
        System.out.println("Gold: " + getGold());
        System.out.println("Damage: " + getDamage());

    }

    //inventory

    @Override
    protected void addedToWorld(World world) {
        this.inventoryVisualizer = new InventoryVisualizer(this.inventory,this);
        world.addObject(this.inventoryVisualizer, 0,  getWorld().getHeight()+1);
        setSelectedSlot(1);
    }




    public void checkControlls(){
        if (Greenfoot.isKeyDown("E")){
           hitAnything();
        }
        if (Greenfoot.isKeyDown("F")){
            pickUPAnything();
        }
        if (Greenfoot.isKeyDown("C")) {
            putAnything();
        }
        if (Greenfoot.isKeyDown("Q")) {
            eatAnyFruit();
            equipArmour();
        }
        if (Greenfoot.isKeyDown("B")) {
            enterMerchantWorld();
        }
        if (Greenfoot.isKeyDown("Up")) {
            turnNorth();
        }
        if (Greenfoot.isKeyDown("Left")) {
            turnWest();
        }
        if (Greenfoot.isKeyDown("Down")) {
            turnSouth();
        }
        if (Greenfoot.isKeyDown("Right")) {
            turnEast();
        }
        if (Greenfoot.isKeyDown("W")) {
            moveNorth();
        }
        if (Greenfoot.isKeyDown("D")) {
            moveEast();
        }
        if (Greenfoot.isKeyDown("S")) {
            moveSouth();
        }
        if (Greenfoot.isKeyDown("A")) {
            moveWest();
        }
        if (Greenfoot.isKeyDown("F1")) {
            debugValues();
        }
        if (Greenfoot.isKeyDown("F2")) {
            invincibleMode();
        }
        if(Greenfoot.isKeyDown("U")){
            flipLever();
        }
    }


    public void calculateDamage(){
        Item currentObjet = inventory[getSelectedSlot()];
        if (currentObjet instanceof Sword){
            setSwordDamage(((Sword) currentObjet).getExtradamage());
            setDamage(getSwordDamage());
        }else{
            setDamage(getFistDamage());
        }
    }


    public void hitAnything(){
            hitDestructable();
            hitMovingactor(this);

    }

    public void pickUPAnything(){

        if(isTouching(Item.class)){
            List<Pickable> objecteAnFeld = getWorld().getObjectsAt(getX(), getY(), Pickable.class);
            Pickable object = objecteAnFeld.get(0);
            if (inventory[getSelectedSlot()] == null) {
                inventory[getSelectedSlot()] = object.onPick(this);
            }else {
                getWorld().addObject(inventory[getSelectedSlot()],getX(), getY());
                inventory[getSelectedSlot()] = null;
                inventory[getSelectedSlot()] = object.onPick(this);
            }
        }
    }

    //move in inv
   /* private void ensureMouseListener()
    {
        if (!isListening && getWorld() != null)
        {
            Greenfoot.getWorld().addMouseWheelListener(new MouseWheelListener() {
                @Override
                public void mouseWheelMoved(MouseWheelEvent e) {
                    // This runs every time the wheel moves
                    handleScroll(e.getWheelRotation());
                }
            });
            isListening = true;
        }
    }
    private void handleScroll(int rotation)
    {
        setSelectedSlot((getSelectedSlot() + rotation + TOTAL_SLOTS) % TOTAL_SLOTS);

    }
    */
    private void selectSlot() {
        String lastKey = Greenfoot.getKey();
        for(int i = 0; i < inventory.length+1; i++) {
            if(lastKey != null  && lastKey.equals(String.valueOf(i))) {
                if(i == 0 ) {
                    if(getWorld().getWidth() < 10) {
                        return;
                    }
                    setSelectedSlot(9);
                    return;
                }
                setSelectedSlot(i-1);
                return;
            }
        }
    }


    private void putAnything() {

        if (inventory[getSelectedSlot()] != null) {
            getWorld().addObject(inventory[getSelectedSlot()], getX(), getY()); //place the Carrot
            inventory[getSelectedSlot()] = null; // Setzt das feld auf null so das die carott raus aus dem inv ist
        }

    }
    private void invincibleMode(){
        setLife(999999999);
    }
    //eating Fruits
    private void eatAnyFruit(){

        List <Edible> edibles = getWorld().getObjectsAt(getX(), getY(), Edible.class);
        if (!edibles.isEmpty()) {
            Edible objectToEat = edibles.get(0);
            if (getLife() < 100) {
                objectToEat.onUse(this);
            }
        } else {
            int selectedSolt = getSelectedSlot();
            Item objectToEat = inventory[selectedSolt];
            if (objectToEat instanceof Edible){
                ((Edible) objectToEat).onUse(this);
                inventory[selectedSolt] = null;
            }
        }

        if (getLife() > 100){
            setLife(100);
        }

    }

    private void equipArmour(){

        List <Armour> armours = getWorld().getObjectsAt(getX(), getY(), Armour.class);
        if (!armours.isEmpty()) {
            Armour armour = armours.get(0);
            if (getLife() < 100) {
                armour.onUse(this);
            }
        } else {
            int selectedSolt = getSelectedSlot();
            Item objectToEat = inventory[selectedSolt];
            if (objectToEat instanceof Armour){
                ((Armour) objectToEat).onUse(this);
                inventory[selectedSolt] = null;
            }
        }
    }


    //check for Rock
    private boolean checkForRock(int x, int y){
        List<Rock> rocks = getWorld().getObjectsAt(x,y,Rock.class);
        return !rocks.isEmpty();
    }

    private void hitDestructable(){

        List<Destructible> destructibles = getWorld().getObjectsAt(getNextX(1), getNextY(1), Destructible.class);
        if (!destructibles.isEmpty()) {
            Destructible destructible = destructibles.get(0);
            destructible.onDestruction(this);
        }

    }

    //Bewegung

    public void moveNorth(){
        turn(Direction.NORTH);
        move();
    }
    public void moveWest(){
        turn(Direction.WEST);
        move();
    }
    public void moveSouth(){
        turn(Direction.SOUTH);
        move();
    }
    public void moveEast(){
        turn(Direction.EAST);
        move();
    }
    public void turnNorth(){
        turn(Direction.NORTH);
    }
    public void turnWest(){
        turn(Direction.WEST);
    }
    public void turnSouth(){
        turn(Direction.SOUTH);
    }
    public void turnEast(){
        turn(Direction.EAST);
    }

    //damage
    public void damagePlayer(int damage){
        setLife(getLife()-damage);
        if (getLife() <= 0) {
            Greenfoot.stop();
            getWorld().showText("You Lost", 5 ,5);
            getWorld().removeObject(this);
        }
    }


    private void getsHitByMonster(){
        if (isTouching(Monster.class)) {
            List<Monster> monster = getWorld().getObjectsAt(getX(), getY(), Monster.class);
            Monster hitter = monster.get(0);
            damagePlayer(hitter.getDamage());
            getWorld().addObject(new Star(), getX(), getY());
        }
    }



    //sets  the damage when walking against something

    private void move() {
        if (canMove()) {
            move(1);
        } else {
            int nextX = getNextX(1);
            int nextY = getNextY(1);
            List<Rock> rocks = getWorld().getObjectsAt(nextX, nextY, Rock.class);
            if (!rocks.isEmpty()) {
                getWorld().addObject(new Star(), getNextX(1), getNextY(1));
                Rock rock = rocks.get(0);
                int damageFromRock = rock.getDamage();
                damagePlayer(damageFromRock);
            }
        }
    }
    //check for Direction its facing
    public boolean isFacingSouth(){
        return getRotation() == 90;
    }
    public boolean isFacingWest(){
        return getRotation() == 180;
    }

    public boolean isFacingNorth(){
        return getRotation() == 270;
    }

    public boolean isFacingEast(){
        return getRotation() == 0;
    }

    public String getDirectioncurrontlyFacing(){
        if(isFacingEast()){
            return "east";
        } else if (isFacingNorth()) {
            return "north";
        } else if (isFacingSouth()) {
            return "south";
        } else if (isFacingWest()) {
            return "west";
        }else System.out.println( "error: object isn't facing any direction directly");
        return "Error";
    }
    //gold


    //enter next level
    private void enterLevel2(){
        if (getX() >= getWorld().getWidth()-1){
            if(isFacingEast()){
                if(Greenfoot.isKeyDown("D")){
                    Greenfoot.setWorld(new Level2(this));
                }
            }
        }
    }
    private boolean checkForMerchant(){
        List<Merchant> merchant = getNeighbours(1, true, Merchant.class);
        return !merchant.isEmpty();
    }

    private void enterMerchantWorld(){

        if(checkForMerchant()){
            Greenfoot.setWorld(new MerchantWorld(this));
        }


    }
    public void flipLever(){
        if (isTouching(Lever.class)){
            List<Lever> levers = getWorld().getObjectsAt(getX(),getY(),Lever.class);
            Lever lever = levers.get(0);
            lever.onUse(this);
        }
    }

}




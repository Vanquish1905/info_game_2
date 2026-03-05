import greenfoot.*;
import java.util.*;

public class Player extends MovingActor{

    //Attribute
    private int life;
    private ImprovedActor[] inventory = new ImprovedActor[10];
    private int damage;
    private int gold;
    private InventoryVisualizer inventoryVisualizer;

    //Variablen für Konstruktoren
    private int startingCarrots = 20;




    //Konstruktoren
    //this. immer zum bestimmen der Value vom Object(Object muss voher definieret worden sein in Attributen)

    public Player(int startingLives) {
        this.life = startingLives;
        /*for (int i = 0; i < startingCarrots; i++) {
            this.inventory[i] = new Carrot();
        }*/
        this.damage = 20;
        this.gold = 100;
    }


    //Methoden

    //myWorld für methoden aus world
    World myWorld = getWorld();

    //act
    public void act() {
        performMovementW();
        performMovementA();
        performMovementS();
        performMovementD();
        putCarrot();
        debugValues();
        eatCarrot();
        turnEast();
        turnWest();
        turnNorth();
        turnSouth();
        hitAnything();
        pickUPAnything();
        getsHitByMonster();
        enterLevel2();
        enterMerchantWorld();
        selectItem();


        }

    //setter und getter

    //setter und getter für damage
    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }
    //setter und getter für gold
    public int getGold() {
        return gold;
    }

    public void setGold(int Gold) {
        this.gold = Gold;
    }



    //Setter und Getter für Leben
    public int getLife() {
        return this.life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    /*Setter und Getter für Carrots
    public int getCarrots() {
        int count = 0;
        for (int i = 0; i < inventory.length; i++) {
            if (inventory[i] != null) {
                count++;
            }
        }
        return count;
    }

    */


    //Methoden

    //debug
    private void debugValues() {
        if (Greenfoot.isKeyDown("F1")) { //debug
            System.out.println("lifes: " + getLife());
            System.out.println("Gold: " + getGold());
            System.out.println("Damage: " + getDamage());
        }
    }

    //inventory

    @Override
    protected void addedToWorld(World world) {
        this.inventoryVisualizer = new InventoryVisualizer(this.inventory);
        world.addObject(this.inventoryVisualizer, 0,  13);
    }


    public void hitAnything(){
        if (Greenfoot.isKeyDown("E")){
            hitRock();
            hitMonster();
        }
    }

    public void pickUPAnything(){
        if (Greenfoot.isKeyDown("F")){
            pickupCarrot();
            pickupGold();
            pickupDamageBuff();
            pickupFullLiveBuff();
        }
    }
    //move in inv
    public int inventorySlot = 0;
    public void selectItem(){
        if (Greenfoot.isKeyDown("1")){
            inventorySlot = 0;
        } else if (Greenfoot.isKeyDown("2")) {
            inventorySlot = 1;
        } else if (Greenfoot.isKeyDown("3")) {
            inventorySlot = 2;
        } else if (Greenfoot.isKeyDown("4")) {
            inventorySlot = 3;
        } else if (Greenfoot.isKeyDown("5")) {
            inventorySlot = 4;
        } else if (Greenfoot.isKeyDown("6")) {
            inventorySlot = 5;
        } else if (Greenfoot.isKeyDown("7")) {
            inventorySlot = 6;
        } else if (Greenfoot.isKeyDown("8")) {
            inventorySlot = 7;
        } else if (Greenfoot.isKeyDown("9")) {
            inventorySlot = 8;
        } else if (Greenfoot.isKeyDown("0")) {
            inventorySlot = 9;
        }

    }



    //Kann Carrot Ablegen
    private void placeCarrot(){
        getWorld().addObject(new Carrot(),getX(),  getY());
    }

    private void putCarrot() {
        if (Greenfoot.isKeyDown("C")) {
            if (inventory[inventorySlot] != null) {
                getWorld().addObject(inventory[inventorySlot], getX(), getY()); //place the Carrot
                inventory[inventorySlot] = null; // Setzt das feld auf null so das die carott raus aus dem inv ist
            }

        }
    }
    //check for Carrot at xy
    private boolean checkForCarrot(int x, int y){
        List<Carrot> carrots = getWorld().getObjectsAt(x, y, Carrot.class);
        return !carrots.isEmpty();
    }
    //pickup Carrot
    private void pickupCarrot() {
        List<Carrot> carrotsOnField = getWorld().getObjectsAt(getX(), getY(), Carrot.class);
        if (!carrotsOnField.isEmpty()) {  //checkt ob Carrots auf dem field sind
            Carrot carrotToPick = carrotsOnField.get(0);//identifiziert die Carrot die gepickt werden soll
            for (int i = 0; i < inventory.length; i++) { // findet den ersten leeren slot
                if (inventory[i] == null) { //wenn der inv slot leer ist
                    inventory[i] = carrotToPick;  // setzt die Carrot ins inv
                    getWorld().removeObject(carrotToPick);  //picks Carrot
                    break;
                }
            }
        }
    }

    //eating carrots
    private void eatCarrot(){
        if (Greenfoot.isKeyDown("Q")) {
            if(checkForCarrot(getX(), getY())){
                List<Carrot> carrots = getWorld().getObjectsAt(getX(), getY(), Carrot.class);
                Carrot carrot = carrots.get(0);
                getWorld().removeObject(carrot);
                if(getLife()<100) {
                    setLife(getLife() + carrots.get(0).getWeight());
                }
                if(getLife()>100){
                    setLife(100);
                }
            }
        }
    }

    //check for Rock
    private boolean checkForRock(int x, int y){
        List<Rock> rocks = getWorld().getObjectsAt(x,y,Rock.class);
        return !rocks.isEmpty();
    }

    private void hitRock(){
        if(checkForRock(getNextX(1), getNextY(1))){
            List<Rock> rocks = getWorld().getObjectsAt(getNextX(1), getNextY(1), Rock.class);
            Rock rock = rocks.get(0);
            rock.hit();
        }
    }



    //Bewegung
    private void performMovementW() {
        if (Greenfoot.isKeyDown("W")) {
            turn(Direction.NORTH);
            move();
        }                 
    }
    private void performMovementA() {
        if (Greenfoot.isKeyDown("A")) {
            turn(Direction.WEST);
            move();
        }
    }
    private void performMovementS() {
        if (Greenfoot.isKeyDown("s")) {
            turn(Direction.SOUTH);
            move();
        }
    }
    private void performMovementD() {
        if (Greenfoot.isKeyDown("D")) {
            turn(Direction.EAST);
            move();
        }
    }
    private void turnNorth() {
        if (Greenfoot.isKeyDown("Up")) {
            turn(Direction.NORTH);
        }
    }
    private void turnWest() {
        if (Greenfoot.isKeyDown("Left")) {
            turn(Direction.WEST);
        }
    }
    private void turnSouth() {
        if (Greenfoot.isKeyDown("Down")) {
            turn(Direction.SOUTH);
        }
    }
    private void turnEast() {
        if (Greenfoot.isKeyDown("Right")) {
            turn(Direction.EAST);
        }
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

    private void hitMonster(){

        List<Monster> monster = getWorld().getObjectsAt(getX(), getY(), Monster.class);
        if (!monster.isEmpty()) {
            Monster hitter = monster.get(0);
            hitter.hit(getDamage());
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
        if(getRotation()==90){
            return true;
        }
        else{
            return false;
        }
    }
    public boolean isFacingWest(){
        if(getRotation()==180){
            return true;
        }
        else{
            return false;
        }
    }

    public boolean isFacingNorth(){
        if(getRotation()==270){
            return true;
        }
        else{
            return false;
        }
    }

    public boolean isFacingEast(){
        if(getRotation()==0){
            return true;
        }
        else{
            return false;
        }
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
        }else return "error: object isn't facing any direction directly";


    }
    //gold
    private boolean checkForGold(int x, int y){
        List<GoldNugget> goldNuggets = getWorld().getObjectsAt(x, y, GoldNugget.class);
        return !goldNuggets.isEmpty();
    }

    private void pickupGold(){
        if(checkForGold(getX(), getY())){
            List<GoldNugget> goldNuggets = getWorld().getObjectsAt(getX(), getY(), GoldNugget.class);
            GoldNugget gold = goldNuggets.get(0);
            getWorld().removeObject(gold);
            setGold(getGold()+1);
        }
    }


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
        if (Greenfoot.isKeyDown("B")){
                if(checkForMerchant()){
                    Greenfoot.setWorld(new MerchantWorld(this));
                }

        }
    }
    private boolean checkForDamageBuff(){
        List<more5Damage> damageBuff = getWorld().getObjectsAt(getX(), getY(), more5Damage.class);
        return !damageBuff.isEmpty();
    }
    private void pickupDamageBuff(){

        if (checkForDamageBuff()) {
            if (getGold() >= 20) {
                List<more5Damage> damageBuff = getWorld().getObjectsAt(getX(), getY(), more5Damage.class);
                more5Damage buff = damageBuff.get(0);
                getWorld().removeObject(buff);
                setDamage(getDamage() + 5);
                setGold(getGold()-20);
            }
        }
    }
    private boolean checkForFullLiveBuff(){
        List<FullLive> fullLiveBuff = getWorld().getObjectsAt(getX(), getY(), FullLive.class);
        return !fullLiveBuff.isEmpty();
    }
    private void pickupFullLiveBuff(){

        if (checkForFullLiveBuff()) {
            if (getGold() >= 25) {
                if (getLife()<100) {
                    List<FullLive> Buff = getWorld().getObjectsAt(getX(), getY(), FullLive.class);
                    FullLive buff = Buff.get(0);
                    getWorld().removeObject(buff);
                    setLife(100);
                    setGold(getGold() - 25);
                }
            }
        }
    }

}




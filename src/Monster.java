import greenfoot.Greenfoot;

import java.util.*;

public class Monster extends MovingActor{
    //Atributes
    private int lifes;
    private int damage;



    //Konstructor
    public Monster() {
        this.lifes = 50;
        this.damage = 10;
    }

    //setter und getter
    public int getLife() {
        return lifes;
    }

    public void setLife(int life) {
        this.lifes = life;
    }
    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }


    public void act() {
        moveToPlayer();
    }
    //Methoden

    public void hit(int damageTaken) {
        getWorld().addObject(new Star(), getX(), getY());
        setLife(lifes - damageTaken);
        if (getLife() <= 0) {
            getWorld().removeObject(this);
        }
    }
    private void moveToXY(int x, int y){
        int distanceX = x - getX();
        int distanceY = y - getY();
        if (distanceX != 0) {
            if (distanceX > 0) turn(Direction.EAST); else turn(Direction.WEST);
            if (canMove()) {
                move(1);
                return; // nur 1 schritt pro act
            }
        }

        // wenn blcokiert oder horizontal richtig
        if (distanceY != 0) {
            if (distanceY > 0) turn(Direction.SOUTH); else turn(Direction.NORTH);
            if (canMove()) {
                move(1);
                return;
            }
        }
        // wenn blockiert drehen bis es geht
        if (!canMove()) {
            turn(getRotation() + 90);
        }
    }
    private void moveToPlayer(){
        List<Player> players = getNeighbours(5, true, Player.class);
        if (!players.isEmpty()) {
            Player target = players.get(0);
            moveToXY(target.getX(), target.getY());
        }
    }









}

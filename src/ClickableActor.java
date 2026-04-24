
import greenfoot.*;

public class ClickableActor extends Actor implements Clickable {

    private boolean clicked = false;

    @Override
    public void act() {
        checkClicked();
        updateAppearance();
    }

    @Override
    public void onClicked() {
        clicked = !clicked;
        updateAppearance();
        System.out.println("Actor was clicked! Toggled to: " + clicked);
    }

    private void updateAppearance() {
        GreenfootImage img = new GreenfootImage(60, 60);
        img.setColor(clicked ? Color.GREEN : Color.RED);
        img.fillOval(0, 0, 60, 60);
        setImage(img);
    }
}

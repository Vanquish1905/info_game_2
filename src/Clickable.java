import greenfoot.*;
public interface Clickable {

    default void checkClicked() {
        if (Greenfoot.mouseClicked(this)) {
            onClicked();
        }
    }

    void onClicked();
}
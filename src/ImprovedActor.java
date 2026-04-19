import greenfoot.Actor;
import greenfoot.GreenfootImage;

public class ImprovedActor extends Actor {
    private GreenfootImage currentImage; //stored to ensure paint can be removed
    private int life;
    private int lastDrawnValue = -1;

    public int getLife() {
        return this.life;
    }
    public void setLife(int life) {
        this.life = life;
    }



    /**
     * Sets the image saving the old image for later use.
     * @param image to set
     */
    @Override
    public void setImage(GreenfootImage image){
        currentImage = image;
        super.setImage(new ImprovedGreenfootImage(image));
    }


    /**
     * Draws the text over the current image.
     * @param text to be drawn
     */
    public void draw(String text){
        ImprovedGreenfootImage image = new ImprovedGreenfootImage(currentImage);
        image.drawString(text, 0, 10);
        super.setImage(image);
    }

    /**
     * Draws the int value over the current image.
     * @param value to be drawn
     */
    public void draw(int value) {
        if (value == lastDrawnValue) return; // Optimization: Don't redraw if same

        lastDrawnValue = value;
        ImprovedGreenfootImage image = new ImprovedGreenfootImage(currentImage);
        image.drawString(String.valueOf(value), 0, 10);
        super.setImage(image);
    }
}

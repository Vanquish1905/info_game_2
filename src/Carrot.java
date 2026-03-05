
public class Carrot extends ImprovedActor
{
    //TODO: Identifiziere Attribute, Methoden und Konstruktoren 
    //und markiere diese entsprechend durch Kommentare.
    private int weight;
   
    public Carrot(){
        setWeight(5);
    }
    
    public Carrot(int weight){
        setWeight(weight);
    }

    public int getWeight() {
        return this.weight;
    }
    
    public void setWeight(int newWeight){
        this.weight = newWeight;
        draw(this.weight);
    }
}

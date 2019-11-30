/**
 * Class food for every food object
 */
public class Food {
    /**
     * instance variables
     * Enjoyment - double : each food item has an enjoyment
     * Volume - double : Volume for each food item
     */
    private double Enjoyment;
    private double Volume;

    /**
     * constructor --> sets the value of the instance variables
     * @param Volume - double
     * @param Enjoyment - double
     */
    public Food(double Volume, double Enjoyment){
        this.Volume = Volume;
        this.Enjoyment = Enjoyment;
    }

    /**
     * accessor method to get the instance variable Volume
     * @return double - volume of the food
     */
    public double getVolume(){
        return Volume;
    }

    /**
     * accessor method to get the instance variable Enjoyment
     * @return double - enjoyment of the food
     */
    public double getEnjoyment(){
        return Enjoyment;
    }
}

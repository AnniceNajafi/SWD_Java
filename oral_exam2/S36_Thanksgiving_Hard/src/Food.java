public class Food {
    private double Enjoyment;
    private double Volume;
    public Food(double Volume, double Enjoyment){
        this.Volume = Volume;
        this.Enjoyment = Enjoyment;
    }
    public double getVolume(){
        return Volume;
    }
    public double getEnjoyment(){
        return Enjoyment;
    }
}

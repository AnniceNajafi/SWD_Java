import java.util.List;

/**
 * @author Annice Najafi
 * Level of difficulty: Hard
 * Description: Your grandmother has put together a Thanksgiving spread with copious amounts of turkey, pumpkin pie,
 * mashed potatoes, gravy, stuffing, cranberries, and casserole. The food is almost unlimited, but unfortunately the
 * volume of your stomach is not. Naturally, as an engineering student, you want to optimize the usage of your
 * stomach space. Each unit of food has a volume (v), and enjoyment (e). The goal is to maximize your total enjoyment
 * such that the total volume of food you’ve eaten is less than or equal to C, your stomach capacity. For each of the
 * foods listed above, define an object representing one unit of the food, with volume and enjoyment as attributes.
 * Write an algorithm that takes as input (f, C) where f is a list of food unit objects and C is the capacity of your stomach.
 * The volume and enjoyment of each food unit, as well as stomach capacity, should be entered by the user.
 * The program output should be an optimal enjoyment E based on the values entered, as well as the quantity of each food item
 * you should consume in order to attain optimal enjoyment.
 * Fall 2019 SWD_Advanced problems
 */
public class EnjoymentCalculator {
    /**
     * static so that we only have a single copy of the variables
     * foodlist contains a list of food objects to calculate the optimal enjoyment based on
     */
    private static List<Food> foodList;
    /**
     * static so that we only have a single copy of the variables
     * double - capacity : capacity of the stomach
     */
    private static double capacity;

    /**
     * Constructor: sets the value of the instance variables
     * @param foodList is a list of food objects
     * @param capacity - type: double, capacity of stomach
     */
    public EnjoymentCalculator(List<Food> foodList, double capacity){
        this.foodList = foodList;
        this.capacity = capacity;
    }

    /**
     * calculates the optimalEnjoyment based on the food present and their relative enjoyment
     * receives nothing uses instance variables
     * @return double optimalEnjoyment based on food objects
     */
    public double optimalEnjoyment(){
        ///Set inital totalEnjoyment to zero
        double totalEnjoyment=0;
        ///While stomach is still not full and there is food in the foodlist
        do{
            ///Find the food with the maximum enjoyment ot volume ratio
            Food mostEnjoyable = findMaxRatio();
            ///store the maximum number of the food with the max enjoyment to volume ratio
            int store = (int) (capacity/mostEnjoyable.getVolume());
            ///add the enjoyment to the totalEnjoyment
            totalEnjoyment+=store*mostEnjoyable.getEnjoyment();
            ///subtract from the capacity of the stomach
            capacity-=mostEnjoyable.getVolume()*store;
            ///remove that food from the foodList
            foodList.remove(mostEnjoyable);
            ///repeat till stomach is full
        }while(capacity>0 && !foodList.isEmpty());
        return totalEnjoyment;
    }

    /**
     * function is meant to be used by optimalEnjoyment
     * finds the food in the foodList with the maximum Enjoyment to volume ratio
     * @return the food object in the foodList with the max enjoyment to volume ratio
     */
    private Food findMaxRatio(){
        ///start from the first food in the foodList and find the enjoyment ot volume ratio
        double keep = (foodList.get(0).getEnjoyment())/(foodList.get(0).getVolume());
        ///loop through the foodList and look for the max ratio
        for(int i=0; i<foodList.size();i++){
                if((foodList.get(i).getEnjoyment())/(foodList.get(i).getVolume())>keep){
                    keep = (foodList.get(i).getEnjoyment())/(foodList.get(i).getVolume());
                }
        }
        ///link the ratio to the food
        Food result=new Food(foodList.get(0).getEnjoyment(), foodList.get(0).getVolume());
        ///by looping though the foodList
        for(Food food: foodList){
            if(food.getEnjoyment()/food.getVolume()==keep){
                result = food;
            }
        }
        ///return the food with the max ratio
        return result;
    }
}

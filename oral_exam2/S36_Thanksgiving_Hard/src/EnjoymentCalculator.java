import java.util.List;

public class EnjoymentCalculator {
    private static List<Food> foodList;
    private static double capacity;
    public EnjoymentCalculator(List<Food> foodList, double capacity){
        this.foodList = foodList;
        this.capacity = capacity;
    }
    public double optimalEnjoyment(){
        double totalEnjoyment=0;
//        double totalWeight=0;
        do{
            Food mostEnjoyable = findMaxRatio();
            int store = (int) (capacity/mostEnjoyable.getVolume());
            totalEnjoyment+=store*mostEnjoyable.getEnjoyment();
//            totalWeight+=store*mostEnjoyable.getVolume();
            capacity-=mostEnjoyable.getVolume()*store;
            foodList.remove(mostEnjoyable);

        }while(capacity>0 && !foodList.isEmpty());
        return totalEnjoyment;
    }
    public Food findMaxRatio(){
        double keep = (foodList.get(0).getEnjoyment())/(foodList.get(0).getVolume());
        for(int i=0; i<foodList.size();i++){
                if((foodList.get(i).getEnjoyment())/(foodList.get(i).getVolume())>keep){
                    keep = (foodList.get(i).getEnjoyment())/(foodList.get(i).getVolume());
                }
        }
        Food result=new Food(foodList.get(0).getEnjoyment(), foodList.get(0).getVolume());
        for(Food food: foodList){
            if(food.getEnjoyment()/food.getVolume()==keep){
                result = food;
            }
        }
        return result;
    }
}

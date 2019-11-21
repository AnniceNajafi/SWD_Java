import org.junit.jupiter.api.Test;


import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EnjoymentCalculatorTest {
    @Test
    public void Test1(){
        Food Turkey = new Food(3, 4);
        Food Pie = new Food(2, 2);
        Food Potatoes = new Food(4, 5);
        Food Gravy = new Food(10, 10);
        Food Stuffing = new Food (2,3);
        Food Cranberries = new Food(7,5);
        Food Casserole = new Food(12, 17);
        List<Food> foodList1= new ArrayList<Food>();
        foodList1.add(Turkey);
        foodList1.add(Pie);
        foodList1.add(Potatoes);
        foodList1.add(Gravy);
        foodList1.add(Stuffing);
        foodList1.add(Cranberries);
        foodList1.add(Casserole);
        EnjoymentCalculator test1 = new EnjoymentCalculator(foodList1, 20);
        assertEquals(30, test1.optimalEnjoyment());
    }
    @Test
    public void Test2(){
        Food Turkey = new Food(3, 4);
        Food Pie = new Food(2, 2);
        Food Potatoes = new Food(4, 5);
        Food Gravy = new Food(1, 1);
        Food Stuffing = new Food (2,3);
        Food Cranberries = new Food(10,14);
        Food Casserole = new Food(15, 24);
        List<Food> foodList2= new ArrayList<Food>();
        foodList2.add(Turkey);
        foodList2.add(Pie);
        foodList2.add(Potatoes);
        foodList2.add(Gravy);
        foodList2.add(Stuffing);
        foodList2.add(Cranberries);
        foodList2.add(Casserole);
        EnjoymentCalculator test2 = new EnjoymentCalculator(foodList2, 41);
        assertEquals(64, test2.optimalEnjoyment());
    }
    @Test
    public void Test3(){
        Food Turkey = new Food(3, 5);
        Food Pie = new Food(4, 12);
        Food Potatoes = new Food(1, 1);
        Food Gravy = new Food(2, 5);
        Food Stuffing = new Food (1,1);
        Food Cranberries = new Food(2,2);
        Food Casserole = new Food(3, 3);
        List<Food> foodList3= new ArrayList<Food>();
        foodList3.add(Turkey);
        foodList3.add(Pie);
        foodList3.add(Potatoes);
        foodList3.add(Gravy);
        foodList3.add(Stuffing);
        foodList3.add(Cranberries);
        foodList3.add(Casserole);
        EnjoymentCalculator test3 = new EnjoymentCalculator(foodList3, 7);
        assertEquals(18, test3.optimalEnjoyment());
    }
    @Test
    public void Test4(){
        Food Turkey = new Food(3, 6);
        Food Pie = new Food(4, 9);
        Food Potatoes = new Food(1, 0.5);
        Food Gravy = new Food(2, 4);
        Food Stuffing = new Food (1,1);
        Food Cranberries = new Food(2,2);
        Food Casserole = new Food(3, 3);
        List<Food> foodList4= new ArrayList<Food>();
        foodList4.add(Turkey);
        foodList4.add(Pie);
        foodList4.add(Potatoes);
        foodList4.add(Gravy);
        foodList4.add(Stuffing);
        foodList4.add(Cranberries);
        foodList4.add(Casserole);
        EnjoymentCalculator test4 = new EnjoymentCalculator(foodList4, 9);
        assertEquals(19, test4.optimalEnjoyment());
    }
    @Test
    public void Test5(){
        Food Turkey = new Food(6, 7);
        Food Pie = new Food(7, 8);
        Food Potatoes = new Food(8, 9);
        Food Gravy = new Food(9, 10);
        Food Stuffing = new Food (10,11);
        Food Cranberries = new Food(11,12);
        Food Casserole = new Food(12, 13);
        List<Food> foodList5= new ArrayList<Food>();
        foodList5.add(Turkey);
        foodList5.add(Pie);
        foodList5.add(Potatoes);
        foodList5.add(Gravy);
        foodList5.add(Stuffing);
        foodList5.add(Cranberries);
        foodList5.add(Casserole);
        EnjoymentCalculator test5 = new EnjoymentCalculator(foodList5, 5);
        assertEquals(0, test5.optimalEnjoyment());
    }
}

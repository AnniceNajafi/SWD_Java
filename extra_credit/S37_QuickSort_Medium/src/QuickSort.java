/**
 * @author Annice Najafi
 * Extra Credit
 * @LevelOfDifficulty:Medium
 */

import java.util.Arrays;
import java.util.Random;

public class QuickSort {
    /**
     * instance variables
     */
    private int[] arr;
    private static int indexOfLeftPivot;
    private static int indexOfRightPivot;
    private int[] subarr1;
    private int[] subarr2;
    private int[] subarr3;
    public QuickSort(){
        ///generate 100 random numbers between 0-100 store them in an array
        arr = new int[100];
        for(int i=0; i<arr.length; i++){
            Integer num = new Random().nextInt(100) ;
            arr[i]=num;
            System.out.print(arr[i]+" ");
        }
        selectPivots(0,100);
        partition();
        System.out.println(indexOfLeftPivot+" "+indexOfRightPivot);
//        System.out.println(subarr1.length);
//        System.out.println(subarr2.length);
//        System.out.println(subarr3.length);
        sort(subarr1, 0, subarr1.length-1);
        sort(subarr2, 0, subarr2.length-1);
        sort(subarr3, 0, subarr3.length-1);
    }

    public void sort(int[] array, int from, int to){
        ///recursive method, call method until you reach the point when the two pivots are next to each other
        if(to > from){
            int newPivot = subPartition(array, from, to);
            sort(array, from, newPivot-1);
            sort(array, newPivot+1, to);
        }
    }
    //First step, randomly select two pivots
    public void selectPivots(int from, int to){
        indexOfLeftPivot = new Random().nextInt(49);
        indexOfRightPivot = new Random().nextInt(50)+50;
        System.out.println(indexOfLeftPivot+" "+indexOfRightPivot);
    }
    public int subPivot(int from, int to){
        int num = new Random().nextInt((to - from)+1)+from;
        return num;
    }
    //second step create three subarrays
    public void partition()
    {
        int countfirst=0;
        int countsecond=0;
        int countthird=0;
        int k=0;
        while(k<arr.length){
            if(arr[k]<=arr[indexOfLeftPivot]){
                countfirst++;
            }else if(arr[k]>=arr[indexOfRightPivot]){
                countthird++;
            }else if(arr[k]<arr[indexOfRightPivot] && arr[k]>arr[indexOfLeftPivot]){
                countsecond++;
            }
            k++;
        }
        subarr1 = new int[countfirst];
        subarr2 = new int[countsecond];
        subarr3 = new int[countthird];
        int addfirst=0;
        int addsecond=0;
        int addthird=0;
        int j=0;
        while(j<arr.length){
            if(arr[j]<=arr[indexOfLeftPivot]){
                subarr1[addfirst]=arr[j];
                addfirst++;
            }else if(arr[j]>=arr[indexOfRightPivot]){
                subarr3[addthird]=arr[j];
                        addthird++;
            }else{
                subarr2[addsecond]=arr[j];
                addsecond++;
            }
            j++;
        }
    }
    private int subPartition(int[] array, int from, int to){
        swapTwoNums(array, from, subPivot(from, to));
        int second = from +1;
        for(int i=second; i<=to; i++){
            if(array[i]<array[from]){
                swapTwoNums(array, i, second++);
            }
        }
        swapTwoNums(array, from, second-1);
        return second -1;
    }
    private void swapTwoNums(int [] array, int from, int to){
        int hold = array[from];
        array[from] = array[to];
        array[to]= hold;
    }
    public int[] getResult(){
        int [] holdResult = new int [subarr1.length + subarr2.length];
        System.arraycopy(subarr1, 0, holdResult, 0, subarr1.length);
        System.arraycopy(subarr2, 0, holdResult, subarr1.length, subarr2.length);
        int [] result = new int [holdResult.length + subarr3.length];
        System.arraycopy(holdResult, 0, result, 0, holdResult.length);
        System.arraycopy(subarr3, 0, result, holdResult.length, subarr3.length);
        return result;
    }
    public static void main(String[] args){
        QuickSort test = new QuickSort();
        System.out.println(Arrays.toString(test.subarr1)+" "+Arrays.toString(test.subarr2)+" "+ Arrays.toString(test.subarr3));
        System.out.println(Arrays.toString(test.getResult()));
    }
}


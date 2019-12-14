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
    /**
     * indexOfLeftPivot holds the index of the first pivot
     */
    private static int indexOfLeftPivot;
    /**
     * indexOfRightPivot holds the index of the second pivot
     */
    private static int indexOfRightPivot;
    /**
     * subarray1 holds the first array of number
     */
    private int[] subarr1;
    /**
     * subarray1 holds the second array of number
     */
    private int[] subarr2;
    /**
     * subarray3 holds the thirs array of number
     */
    private int[] subarr3;

    /**
     * constructor, calls the selectPivots function to finds the two pivot points then divides the array into three subarrays then calls
     * the sort function to sort each subarray
     */
    public QuickSort(){
        ///generate 100 random numbers between 0-100 store them in an array
        arr = new int[100];
        for(int i=0; i<arr.length; i++){
            Integer num = new Random().nextInt(100) ;
            arr[i]=num;
            System.out.print(arr[i]+" ");
        }
        selectPivots();
        partition();
        System.out.println(indexOfLeftPivot+" "+indexOfRightPivot);
//        System.out.println(subarr1.length);
//        System.out.println(subarr2.length);
//        System.out.println(subarr3.length);
        sort(subarr1, 0, subarr1.length-1);
        sort(subarr2, 0, subarr2.length-1);
        sort(subarr3, 0, subarr3.length-1);
    }

    /**
     * Given an array, it sorts that array by comparing the numbers to the pivot point, recursive funciton
     * @param array an array of integer is passed to the function
     * @param from we need to indicate which range of numbers needs to be sorted once that range is sorted we change the range
     * @param to we need to indicate which range of numbers needs to be sorted once that range is sorted we change the range
     */
    public void sort(int[] array, int from, int to){
        ///recursive method, call method until you reach the point when the two pivots are next to each other
        if(to > from){
            int newPivot = subPartition(array, from, to);
            sort(array, from, newPivot-1);
            sort(array, newPivot+1, to);
        }
    }

    /**
     * selects two random pivot points, one form 0 to 49 the other from 50 to 100.
     */
    //First step, randomly select two pivots
    public void selectPivots(){
        indexOfLeftPivot = new Random().nextInt(49);
        indexOfRightPivot = new Random().nextInt(50)+50;
        System.out.println(indexOfLeftPivot+" "+indexOfRightPivot);
    }

    /**
     * Find a new pivot point given a range, generates a random index
     * @param from int index of the lowest index
     * @param to int the highest index
     * @return an integer a new index for a number in an array
     */
    private int subPivot(int from, int to){
        int num = new Random().nextInt((to - from)+1)+from;
        return num;
    }

    /**
     * This function divides the array into three arrays, using the specified instance variables.
     */
    //second step create three subarrays
    private void partition()
    {
        ///First find out the size of each subarray
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
        ///Place the numbers in the related subarray
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

    /**
     * The function
     * @param array array passed to the function to be partitioned
     * @param from index the lowest index of the array
     * @param to index the highest index of the array
     * @return an integer new pivot point
     */
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

    /**
     * function swaps two numbers given an array and the indices of the ones we want to swap
     * @param array an array of integer passed to the function
     * @param from the index of the number we want to swap
     * @param to the index of the other number we want to swap
     */
    private void swapTwoNums(int [] array, int from, int to){
        int hold = array[from];
        array[from] = array[to];
        array[to]= hold;
    }

    /**
     * concatenates three subarrays
     * @return a sorted array
     */
    private int[] getResult(){
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


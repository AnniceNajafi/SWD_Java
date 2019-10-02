/**
@Author: Annice Najafi
Date: 10 September 2019
Name: S27_Machine_learning
Level of Difficulty: Hard
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.io.FileInputStream;
import java.io.File;
import java.io.*;
import java.util.Scanner;
import java.io.IOException;
import java.util.Random;



public class MachineLearning {
    public static void main (String args[])throws IOException
    {

    double [] arr1 = new double [] {1,2};
    double [] arr2 = new double [] {3,4};
    double [] arr3 = new double [] {1.5535,3.2192,1.4367,2.5803,5.0741};
    String str1 = "Experience";
    String str2 = "Experiment";
    System.out.println(cosineSim(arr1, arr2));
    System.out.println(HammingDist(str1, str2));
    System.out.println(EuclideanDist(arr1, arr2));
    File myFile = new File("S27-MLMedium.csv");
    Scanner sc = new Scanner(myFile);
        File myFile2 = new File("S27-MLHard.csv");
        Scanner sc2 = new Scanner(myFile2);
    knearest("S27-MLMedium.csv", arr3, 5);
    kmeans("S27-MLHard.csv", 5);

}


    ///Method #1: Cosine similarity
    static double cosineSim (double [] arr1, double [] arr2){
        if(arr1.length!=arr2.length){
            System.out.println("Error, undefined");
            return 0;
        }
        else{
            double sum=0;
            double sum1=0;
            double sum2=0;
            for(int i=0; i<arr1.length; i++){
                sum= sum + arr1[i]*arr2[i];
                sum1 = sum1+ Math.pow(arr1[i], 2);
                sum2 = sum2+ Math.pow(arr2[i], 2);
            }
            double result = sum/(Math.sqrt(sum1)*Math.sqrt(sum2));
            return result;
        }

    }
    ///Method #2: Hamming distance
    static int HammingDist (String str1, String str2){
        if(str1.length()!=str2.length()){
            System.out.println("Error, the strings are not equal in size");
            return 0;
        }
        else{
            int sum=0;
            for(int i=0; i<str1.length(); i++){
                if (str1.charAt(i)!=str2.charAt(i)) {
                    sum++;
                }
            }
            return sum;
        }
    }
    ///Method #3: Euclidean Distance
    static double EuclideanDist (double [] arr1, double [] arr2){
        if(arr1.length!=arr2.length){
            System.out.println("Error, undefined");
            return 0;
        }
        else{
            double ddist=0;
            for(int i=0; i<arr1.length; i++){
                ddist = ddist + Math.pow(arr2[i] - arr1[i], 2);
            }
            double dist = Math.sqrt(ddist);
            return dist;
        }
    }

    ////The medium part - k-nearest algorithm

    public static void knearest(String str, double[] arr, int k) throws IOException
    {
        ///First Read in the file
        File myFile = new File(str);
        Scanner theFile = new Scanner(myFile);
        /**
        In this part of the program,
        I. the program should read in each line then separate the line into 5 numbers and one
        string then it should store the Euclidean distance and the related class in a 2 x length matrix.
        II. the program should read in the first column of the matrix and find the k lowest numbers then it should read
        the relative class in the second column and find the majority of the class #.
        For example if (k=3) 2/3 of the lowest 3 numbers or more belong to class 1 then the
        new data set belongs to class 1. else, the data set belongs to class 2.
         */
        //Create 2 arraylists, one string arraylist and one double arraylist
        ArrayList<Double> Distlist = new ArrayList<Double>();
        ArrayList<String> stringList = new ArrayList<String>();
        ArrayList<Double> eachDist = new ArrayList<Double>();

        while(theFile.hasNextLine()) {
           String line =  theFile.nextLine();
           Scanner sc = new Scanner(line);
           sc.useDelimiter(",");
           while(sc.hasNextDouble()){
               eachDist.add(sc.nextDouble());
           }
           stringList.add(sc.next());

        }

        System.out.println(stringList);
        System.out.println(eachDist);

        double [] storeArray = new double [k];
        for(int j=0; j<eachDist.size(); j+=5 ) {
            for (int i = 0; i < k; i++) {
                storeArray[i]=eachDist.get(i+j);
            }
            Distlist.add(EuclideanDist(storeArray, arr));
        }
//        System.out.println(Distlist.size());
//        System.out.println(stringList.size());
//        System.out.println(Distlist);
        ///Now at this point, we should find the k nearest neighbors by finding the ones with the lowest Euclidean distance
        System.out.println(Distlist);
        String[] finalStore = new String[k];
        for(int j=0; j<k; j++) {
            double min = 100;
            for (int i = 0; i < Distlist.size(); i++) {
                if (min > Distlist.get(i)) {
                    min = Distlist.get(i);
                    finalStore[j] = stringList.get(i);
                    Distlist.remove(i);
                    stringList.remove(i);

                }
            }
        }
        ///Do the k neighbors belong to class 1 or class 2?
        int vote1 =0;
        int vote2 =0;
        for(int p=0; p<k; p++) {
//            System.out.println(finalStore[p]);
            //If they belong to class 1 then the new data set belongs to class 1 else it belongs ot class 2
           if(finalStore[p].equals("\"class1\"")){
               vote1++;
           }
           else if(finalStore[p].equals("\"class2\"")){
               vote2++;
           }
        }
//        System.out.println(vote1 + " "+vote2);
        if(vote1>=vote2){
            System.out.println("\"class1\"");
        }
        else{
            System.out.println("\"class2\"");
        }

    }

    ///The Hard Part
    ///K-means clustering algorithm
    ///Based on file of data points with two features.
    public static void kmeans(String str, int k) throws IOException{
        ///Read in from the file and find the length of the file.

        File myFile = new File("S27-MLHard.csv");
        Scanner theFile = new Scanner(myFile);
        ///Define an array list to store the Euclidean distances between the cluster points and the data sets.
        ArrayList<Double> eachnum = new ArrayList<Double>();
        ArrayList<Double> Distlist = new ArrayList<Double>();
        int length=0;
        ///read in from the file and store all data in an arraylist called eachnum
        while(theFile.hasNextLine()) {
            String line =  theFile.nextLine();
            Scanner sc = new Scanner(line);
            length++;
            sc.useDelimiter(",");
            while(sc.hasNextDouble()){
                eachnum.add(sc.nextDouble());
            }

        }
        System.out.println(length);
        System.out.println("each num"+eachnum);

        ///First assign an array to store the cluster centers
        double [] storeArr = new double[k];
        ///Assign k random numbers for k cluster centers. find the index of
        for(int p=0; p<k; p++){
            int randomCluster =(int)(Math.random()*length);
            randomCluster = randomCluster*2+1;
            ///Now we just have the index of the cluster centers.
            storeArr[p]=randomCluster;
        }
        System.out.println(storeArr[0]);
        System.out.println(storeArr[1]);
        System.out.println(storeArr[2]);
        ///Find the cluster centers and store in



    }

}

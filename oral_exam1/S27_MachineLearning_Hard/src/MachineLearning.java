/**
@Author: Annice Najafi
Date: 10 September 2019
Name: S27_Machine_learning
Level of Difficulty: Hard
 */

import java.util.*;
import java.io.File;
import java.io.IOException;


public class MachineLearning {
    public static void main(String args[]) throws IOException {

        double[] arr1 = new double[]{1, 2};
        double[] arr2 = new double[]{3, 4};
        double[] arr3 = new double[]{1.5535, 3.2192, 1.4367, 2.5803, 5.0741};
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
        kmeans("S27-MLHard.csv", 4);

    }


    ///Method #1: Cosine similarity
    static double cosineSim(double[] arr1, double[] arr2) {
        if (arr1.length != arr2.length) {
            System.out.println("Error, undefined");
            return 0;
        } else {
            double sum = 0;
            double sum1 = 0;
            double sum2 = 0;
            for (int i = 0; i < arr1.length; i++) {
                sum = sum + arr1[i] * arr2[i];
                sum1 = sum1 + Math.pow(arr1[i], 2);
                sum2 = sum2 + Math.pow(arr2[i], 2);
            }
            double result = sum / (Math.sqrt(sum1) * Math.sqrt(sum2));
            return result;
        }

    }

    ///Method #2: Hamming distance
    static int HammingDist(String str1, String str2) {
        if (str1.length() != str2.length()) {
            System.out.println("Error, the strings are not equal in size");
            return 0;
        } else {
            int sum = 0;
            for (int i = 0; i < str1.length(); i++) {
                if (str1.charAt(i) != str2.charAt(i)) {
                    sum++;
                }
            }
            return sum;
        }
    }

    ///Method #3: Euclidean Distance
    static double EuclideanDist(double[] arr1, double[] arr2) {
        if (arr1.length != arr2.length) {
            System.out.println("Error, undefined");
            return 0;
        } else {
            double ddist = 0;
            for (int i = 0; i < arr1.length; i++) {
                ddist = ddist + Math.pow(arr2[i] - arr1[i], 2);
            }
            double dist = Math.sqrt(ddist);
            return dist;
        }
    }

    ////The medium part - k-nearest algorithm

    public static String knearest(String str, double[] arr, int k) throws IOException {
        ///First Read in the file
        File myFile = new File(str);
        Scanner theFile = new Scanner(myFile);
        /*
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

        while (theFile.hasNextLine()) {
            String line = theFile.nextLine();
            Scanner sc = new Scanner(line);
            sc.useDelimiter(",");
            while (sc.hasNextDouble()) {
                eachDist.add(sc.nextDouble());
            }
            stringList.add(sc.next());

        }

        System.out.println(stringList);
        System.out.println(eachDist);

        double[] storeArray = new double[k];
        for (int j = 0; j < eachDist.size(); j += 5) {
            for (int i = 0; i < k; i++) {
                storeArray[i] = eachDist.get(i + j);
            }
            Distlist.add(EuclideanDist(storeArray, arr));
        }
//        System.out.println(Distlist.size());
//        System.out.println(stringList.size());
//        System.out.println(Distlist);
        ///Now at this point, we should find the k nearest neighbors by finding the ones with the lowest Euclidean distance
        System.out.println(Distlist);
        String[] finalStore = new String[k];
        for (int j = 0; j < k; j++) {
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
        int vote1 = 0;
        int vote2 = 0;
        for (int p = 0; p < k; p++) {
//            System.out.println(finalStore[p]);
            //If they belong to class 1 then the new data set belongs to class 1 else it belongs ot class 2
            if (finalStore[p].equals("\"class1\"")) {
                vote1++;
            } else if (finalStore[p].equals("\"class2\"")) {
                vote2++;
            }
        }
        String res;
//        System.out.println(vote1 + " "+vote2);
        if (vote1 >= vote2) {
            System.out.println("\"class1\"");
            res = "class1";
        } else {
            System.out.println("\"class2\"");
            res = "class2";
        }
        return res;

    }

    ///The Hard Part
    ///K-means clustering algorithm
    ///Based on file of data points with two features.
    public static String kmeans(String str, int k) throws IOException {
        /*
        For k-means clustering we first have to assign k  numbers as the centroids of k clusters then find the Euclidean distance of each
        data point from the cluster centers, the centroid closest to the data point means that the data point belongs to that cluster.
        The next step is to find the centroid within each cluster and assign data points to clusters again and redefine clusters.
         */
        ///Read in from the file and find the length of the file.
        File myFile = new File("S27-MLHard.csv");
        Scanner theFile = new Scanner(myFile);
        ///First store all data in one row
        ///read in from the file and store all data in an arraylist called eachnum, the data numbers with odd index are the first feature
        ///with the even feature are the second feature. --> eachnum
        ArrayList<Double> eachnum = new ArrayList<Double>();
        int length = 0;
        while (theFile.hasNextLine()) {
            String line = theFile.nextLine();
            Scanner sc = new Scanner(line);
            length++;
            sc.useDelimiter(",");
            while (sc.hasNextDouble()) {
                eachnum.add(sc.nextDouble());
            }

        }


        /////I. Assign initial centroids
        double[] clusterPoints = new double[k * 2];
        for (int i = 0; i < k * 2 - 1; i++) {
            clusterPoints[i] = eachnum.get(length * (i + 1) / k);
            clusterPoints[i + 1] = eachnum.get((length * (i + 1) / k) + 1);

        }
        Map<double[], Integer> firstItr = new HashMap<double[], Integer>();
        ////II. Find the distance between each data point and the centroids and classify them
        ArrayList<Double> Distlist = new ArrayList<Double>();
        for (int i = 0; i < length * 2; i = i + 2) {
            ///array of data point
            double[] dataPoint = new double[2];
            dataPoint[0] = eachnum.get(i);
            dataPoint[1] = eachnum.get(i + 1);
            ///to this point we have the data point and we have to find the Euclidean distance
            ///loop through the cluster points
            for (int j = 0; j < clusterPoints.length; j = j + 2) {
                double[] clustSub = new double[2];
                clustSub[0] = clusterPoints[j];
                clustSub[1] = clusterPoints[j + 1];
                Distlist.add(EuclideanDist(dataPoint, clustSub));
            }
            int minIndex = Distlist.indexOf(Collections.min(Distlist));
            Distlist.clear();
            firstItr.put(dataPoint, minIndex);

        }
        double[][] dataset = new double[eachnum.size()][2];
        for (int i = 0; i < eachnum.size(); i = i + 2) {
            dataset[i][0] = eachnum.get(i);
            dataset[i][1] = eachnum.get(i + 1);
        }
        ///iterate through the firstItr map and find the centroid of each class again
        for (int i = 0; i < 25; i++) {
            double[][] cen = centroidFinder(firstItr, k);
            firstItr = classifier(dataset, cen, k);
        }
        String result = "";
        for (int j = 0; j < k; j++) {
            int sum = 0;
            for (Map.Entry<double[], Integer> i : firstItr.entrySet()) {


                if (i.getValue() == j) {
                    sum++;
                }

            }


            result = result + "There are " + sum + " numbers in this cluster\n";
        }
        return result;

    }


    public static double[][] centroidFinder(Map<double [], Integer> datapoints, int k){
        double [][] store = new double [k][2];

            for(int j=0; j<k; j++){
                double sumx = 0;
                double sumy = 0;
                double sum = 0;
                for(Map.Entry<double[], Integer> i: datapoints.entrySet()) {
                    if (i.getValue() == j) {
                        sumx = sumx + i.getKey()[0];
                        sumy = sumy + i.getKey()[1];
                        sum++;
                    }
                }
                    double centroidx = sumx / sum;
                    double centroidy = sumy / sum;
                    store[j][0]=centroidx;
                    store[j][1]=centroidy;
            }

        return store;

    }
    public static Map<double[], Integer> classifier (double[][] dataset, double[][] centroid, int k){
        Map<double[], Integer> itr = new HashMap<double[], Integer>();
        ArrayList<Double> DistList = new ArrayList<Double>();
        for(int i=0; i<dataset.length; i=i+2){
            for(int j=0; j<centroid.length; j++){
                DistList.add(EuclideanDist(dataset[i], centroid[j]));
            }
            int c = DistList.indexOf(Collections.min(DistList));
            DistList.clear();
            itr.put(dataset[i], c);
        }
        return itr;
    }

}

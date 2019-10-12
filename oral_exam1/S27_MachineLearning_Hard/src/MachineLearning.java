/**
@author Annice Najafi
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

    /**
     * Returns the cosine of the angle between two vectors
     * @param arr1 First array of values of type double / vector 1
     * @param arr2 Second array of values of type double / vector 2
     * @return double - cosine of the angle between two vectors
     */
    ///Method #1: Cosine similarity
    public static double cosineSim(double[] arr1, double[] arr2) {
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

    /**
     * Returns the Hamming distance if given two words or numbers as String / Hamming dist is the difference of the number of letters of two words
     * @param str1 String - the first word
     * @param str2 String - the second word
     * @return int - the number of different letters
     */
    ///Method #2: Hamming distance
    public static int HammingDist(String str1, String str2) {
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

    /**
     * Returns the Euclidean Distance given two vectors as arrays
     * @param arr1 double array / First vector
     * @param arr2 double array / Second vector
     * @return the Euclidean distance between two vectors
     */
    ///Method #3: Euclidean Distance
    public static double EuclideanDist(double[] arr1, double[] arr2) {
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

    /**
     * This method receives a file with datasets with five features each belonging to a class either class 1 or class 2. If given a new dataset it can classify
     * that as belonging to class 1 or class 2 based on the k nearest neighbors
     * @param str - the path of the file passed to the method as a String
     * @param arr - the new dataset consisting of five features
     * @param k - number of nearest neighbors to be evaluated
     * @return String - class 1 or class 2
     * @throws IOException - if file could not be opened
     */
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

    /**
     * This method first defines k random numbers as centroids of k clusters then finds the Euclidean distance between each datapoint and
     * each centroid, the one closest to the dataset gets classified as belonging to that cluster then it finds the centroids of those clusters again
     * and reclassifies the clusters again and again.
     * @param str - The path of the file containing datasets as a String
     * @param k - the K clusters
     * @return String the clusters and the number of datasets in each cluster
     * @throws IOException - if could not open file
     */
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
            firstItr = classifier(dataset, cen);
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

    /**
     * Finds the centroid of a cluster if given clusters
     * @param datapoints as Map of double arrays relating to a cluster
     * @param k : number of clusters
     * @return the centroid of the clusters
     */

    private static double[][] centroidFinder(Map<double [], Integer> datapoints, int k){
        ///instantiate a double matrix
        double [][] store = new double [k][2];
            ///for each cluster
            for(int j=0; j<k; j++){
                double sumx = 0;
                double sumy = 0;
                double sum = 0;
                ///find the centroid which is basically the mean of each paramter
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

    /**
     * classifer method classifies datasets based on centroids given
     * @param dataset double matrix - matrix of datasets given ready to be classified
     * @param centroid double matrix - centroids
     * @return A map that relates datasets to an int which is the number of cluster
     */
    private static Map<double[], Integer> classifier (double[][] dataset, double[][] centroid){
        ///Create a map
        Map<double[], Integer> itr = new HashMap<double[], Integer>();
        ArrayList<Double> DistList = new ArrayList<Double>();
        ///find the Euclidean Distance and classify as that cluster if the Euclidean distance is the smallest
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

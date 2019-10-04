/**
 * Test File
 * @Author: Annice Najafi
 */

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
public class MachineLearningTest {
    @Test
    public void addFirstTest_easy(){
        MachineLearning test = new MachineLearning();
        double[] arr1 = {1,2,3};
        double[] arr2 =  {2,6,3};
        String word1="0110101";
        String word2="1110010";
        assertEquals(0.8781440805693944,test.cosineSim(arr1, arr2) );
        assertEquals(4.123105625617661, test.EuclideanDist(arr1, arr2));
        assertEquals(4,test.HammingDist(word1, word2));
    }
    @Test
    public void addSecondTest_medium() throws IOException {
        MachineLearning test = new MachineLearning();
        double[] arr1 = {1.5, 3.5, 2, 2, 8};
        double[] arr2 = {3, 3, 2, 2, 1};
        String res1="class1";
        String res2="class2";
        assertEquals(res1, test.knearest("njafi_swd/oral_exam1/S27_MachineLearning_Hard/src/S27-MLMedium.csv",arr1, 5));
        assertEquals(res2, test.knearest("njafi_swd/oral_exam1/S27_MachineLearning_Hard/src/S27-MLMedium.csv",arr2, 5));

    }
    @Test
    public void addThirdTest_hard() throws IOException {
       MachineLearning test = new MachineLearning();
        test.kmeans("S27-MLHard.csv", 4);
    }

}

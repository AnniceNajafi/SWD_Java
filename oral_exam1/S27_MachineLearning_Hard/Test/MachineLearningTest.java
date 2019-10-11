import java.io.IOException;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test File
 * @author: Annice Najafi
 */
public class MachineLearningTest {
    @Test
    public void addFirstTest_easy(){
        MachineLearning test = new MachineLearning();
        double[] arr1 = {1,2,3};
        double[] arr2 =  {2,6,3};
        double[] arr3 ={3,4,5};
        double[] arr4 ={1,2,1};
        double[] arr5 ={1,1,1};
        double[] arr6={0,1,1};
        String word1="0110101";
        String word2="1110010";
        String word3="1110011";
        String word4="Java";
        String word5="Lava";
        ////Cosine similarity function test
        assertEquals(0.8781440805693944,test.cosineSim(arr1, arr2) );
        assertEquals(0.9237604307034013,test.cosineSim(arr3, arr4));
        assertEquals(0.9797958971132713,test.cosineSim(arr3, arr5));
        assertEquals(0.8164965809277259,test.cosineSim(arr5, arr6));
        assertEquals(0.944911182523068,test.cosineSim(arr1, arr6));
        ////Euclidean Distance test
        assertEquals(4.123105625617661, test.EuclideanDist(arr1, arr2));
        assertEquals(4.123105625617661,test.EuclideanDist(arr1, arr2) );
        assertEquals(4.898979485566356,test.EuclideanDist(arr3, arr4));
        assertEquals(5.385164807134504,test.EuclideanDist(arr3, arr5));
        assertEquals(1,test.EuclideanDist(arr5, arr6));
        assertEquals(0,test.EuclideanDist(arr1, arr1));
        ////Hamming Distance test
        assertEquals(4, test.HammingDist(word1, word2));
        assertEquals(1, test.HammingDist(word2, word3));
        assertEquals(0, test.HammingDist(word1, word1));
        assertEquals(1, test.HammingDist(word4, word5));
        ///prints error to screen
        assertEquals(0, test.HammingDist(word2, word4));
    }
    @Test
    public void addSecondTest_medium() throws IOException {
        MachineLearning test = new MachineLearning();
        double[] arr1 = {1.5, 3.5, 2, 2, 8};
        double[] arr2 = {3, 3, 2, 2, 1};
        String res1="class1";
        String res2="class2";
        assertEquals(res1, test.knearest("S27-MLMedium.csv",arr1, 5));
        assertEquals(res2, test.knearest("S27-MLMedium.csv",arr2, 5));

    }
    @Test
    public void addThirdTest_hard() throws IOException {
        MachineLearning test = new MachineLearning();
        System.out.println(test.kmeans("S27-MLHard.csv", 4));
        assertEquals(("There are "+20+" numbers in this cluster\n"+"There are "+30+" numbers in this cluster\n"+"There are "+20+" numbers in this cluster\n"+"There are "+50+" numbers in this cluster\n"), test.kmeans("S27-MLHard.csv", 4));
    }

}

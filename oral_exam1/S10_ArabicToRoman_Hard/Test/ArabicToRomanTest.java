import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;

public class ArabicToRomanTest {
    @Test
    public void test1(){
        ArabicToRoman test1 = new ArabicToRoman();
        assertEquals("MCMLIII",test1.arabicToRoman("1953"));
        assertEquals("MMMCCCIII", test1.arabicToRoman("3303"));
        assertEquals("XVII", test1.arabicToRoman("17"));
        assertEquals("DCCL", test1.arabicToRoman("750"));
    }
    @Test
    public void test2(){
        ArabicToRoman test2 = new ArabicToRoman();
        assertEquals("1953", test2.romanToArabic("MCMLIII"));
        assertEquals("3724", test2.romanToArabic("MMMDCCXXIV"));
        assertEquals("750", test2.romanToArabic("DCCL"));
        assertEquals("3303", test2.romanToArabic("MMMCCCIII"));

    }
    @Test
    public void test3(){
        ArabicToRoman test3 = new ArabicToRoman();
        assertEquals("Invalid input, Roman number rules violated", test3.romanToArabic("IIX"));
        assertEquals("Invalid input, Roman number rules violated", test3.romanToArabic("XXXXX"));
        assertEquals("Invalid input, Roman number rules violated", test3.romanToArabic("MLL"));
        assertEquals("Invalid input, Roman number rules violated", test3.romanToArabic("CCM"));
    }
}

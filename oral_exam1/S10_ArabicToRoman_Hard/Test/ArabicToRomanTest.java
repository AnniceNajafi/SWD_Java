import org.junit.Test;


import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
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
}

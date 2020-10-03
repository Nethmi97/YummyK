package com.example.yummyk;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    private Conversion conversionActivity;

    @Before
    public void  setUp(){
        conversionActivity = new Conversion();
    }

    //Test case 1
    @Test
    public void convertToOunce_Test(){
        float result = conversionActivity.convertToOunce(10);
        assertEquals(0.35274,result,0.001);
    }

    @Test
    public void convertToMg_Test(){
        float result = (float) conversionActivity.convertToMilligram(10);
        assertEquals(10000,result,0.001);
    }

    public void convertToPint_Test(){
        float result = conversionActivity.convertToPint(10);
        assertEquals(0.0211338,result,0.001);
    }

    public void convertToCup_Test(){
        float result = (float) conversionActivity.convertToCups(10);
        assertEquals(0.0416667,result,0.001);
    }
}
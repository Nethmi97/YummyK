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


    @Test
    public void convertToOunce_Test(){
        float result1 = conversionActivity.convertToOunce(10);
        assertEquals(0.35274,result1,0.001);

        float result2 = conversionActivity.convertToOunce(5);
        assertEquals(0.17637,result2,0.001);

        float result3 = conversionActivity.convertToOunce(1);
        assertEquals(0.035274,result3,0.001);

        float result4 = conversionActivity.convertToOunce(150);
        assertEquals(5.29109,result3,0.001);
    }

    @Test
    public void convertToMg_Test(){
        float result1 = (float) conversionActivity.convertToMilligram(10);
        assertEquals(10000,result1,0.001);

        float result2 = (float) conversionActivity.convertToMilligram(5);
        assertEquals(5000,result2,0.001);

        float result3 = (float) conversionActivity.convertToMilligram(1);
        assertEquals(1000,result3,0.001);

        float result = (float) conversionActivity.convertToMilligram(150);
        assertEquals(150000,result,0.001);
    }

    public void convertToPint_Test(){
        float result1 = conversionActivity.convertToPint(10);
        assertEquals(0.0211338,result1,0.001);

        float result2 = conversionActivity.convertToPint(5);
        assertEquals(0.0105669,result2,0.001);

        float result3 = conversionActivity.convertToPint(1);
        assertEquals(0.00211338,result3,0.001);

        float result4 = conversionActivity.convertToPint(150);
        assertEquals(0.317006,result4,0.001);
    }

    public void convertToCup_Test(){
        float result1 = (float) conversionActivity.convertToCups(10);
        assertEquals(0.0416667,result1,0.001);

        float result2 = (float) conversionActivity.convertToCups(5);
        assertEquals(0.0208333,result2,0.001);

        float result3 = (float) conversionActivity.convertToCups(1);
        assertEquals(0.00416667,result3,0.001);

        float result4 = (float) conversionActivity.convertToCups(150);
        assertEquals(0.625,result3,0.001);




    }


}
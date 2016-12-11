package dicestats;

import java.util.Random;

/****
 * (1,1)(1,2)(1,3)(1,4)(1,5)(1,6)
 * (2,1)(2,2)(2,3)(2,4)(2,5)(2,6)
 * (3,1)(3,2)(3,3)(3,4)(3,5)(3,6)
 * (4,1)(4,2)(4,3)(4,4)(4,5)(4,6)
 * (5,1)(5,2)(5,3)(5,4)(5,5)(5,6)
 * (6,1)(6,2)(6,3)(6,4)(6,5)(6,6)
 * 
 * 2,3,4,5,6,7
 * 3,4,5,6,7,8
 * 4,5,6,7,8,9
 * 5,6,7,8,9,10
 * 6,7,8,9,10,11
 * 7,8,9,10,11,12
 * 
 * Chances
 *  2: 1/36 = 2.7777%
 *  3: 2/36 = 5.5555%
 *  4: 3/36 = 8.3333%
 *  5: 4/36 = 11.111%
 *  6: 5/36 = 13.888%
 *  7: 6/36 = 16.666%
 *  8: 5/36 = 13.888%
 *  9: 4/36 = 11.111%
 * 10: 3/36 = 8.3333%
 * 11: 2/36 = 5.5555%
 *  2: 1/36 = 2.7777%
 * 
 */

public class DiceStats {
    static final float[] EXPECTED_CHANCES_2_DICE = {
        1f/36f*100,
        2f/36f*100,
        3f/36f*100,
        4f/36f*100,
        5f/36f*100,
        6f/36f*100,
        5f/36f*100,
        4f/36f*100,
        3f/36f*100,
        2f/36f*100,
        1f/36f*100
    };
    
    static float[] expectedChances12 = new float[11];

    
    public static void main(String[] args) {
        int[] rollValues = new int[11];
        int iterations = 10_000_000;
        int[] rollValuesAgain = new int[11];
        for (short i = 0; i < expectedChances12.length; i++) {
            expectedChances12[i] = 1f/11f*100;
        }
        
        Die d1 = new Die();
        Die d2 = new Die();
        
        for (int i = 0; i < iterations; i++) {
            rollValues[rollDice(d1, d2) - 2]++;
        }
        
        Random r = new Random();
        for (int i = 0; i < iterations; i++) {
            int roll = r.nextInt(11) + 2;
            rollValuesAgain[roll - 2]++;
        }
        
        
        printStats(rollValues, EXPECTED_CHANCES_2_DICE, iterations);
        printStats(rollValuesAgain, expectedChances12, iterations);
    }
    
    public static int rollDice(Die a, Die b) {
        return a.roll() + b.roll();
    }
    
    public static void printStats(int[] theArray, float[] expected, int num) {
        float totalPercent = 0.0f;
        System.out.printf("%s   %s   %s   %s   %s%n", "VALUE", "OCCURENCES", "ACTUAL%", "EXPECTED%", "OFF-BY");
        for (short i = 0; i < theArray.length; i++) {
            float percent = (float)theArray[i]/num * 100;
            float offBy = Math.abs(percent - expected[i]);
            totalPercent += percent;
            System.out.printf("%5d%13d%9.2f%%%11.2f%%%9.2f%n",
                    i + 2, theArray[i], percent, 
                    expected[i], offBy);
        }
        System.out.printf("%n%21s%5.2f%s%n%n%n", "Total ", totalPercent, "%");
    } 
}

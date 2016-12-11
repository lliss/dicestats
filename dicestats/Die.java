package dicestats;

import java.util.Random;

public class Die {
    public int currentValue;
    
    public int roll() {
        Random r = new Random();
        currentValue = r.nextInt(6) + 1;
        return currentValue;
    }
}
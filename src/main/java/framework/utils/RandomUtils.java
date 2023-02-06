package framework.utils;

import java.util.concurrent.ThreadLocalRandom;

public class RandomUtils {

    public static String getRandomString(int n){
        StringBuilder sb = new StringBuilder(n);
        for (int i = 0; i < n; i++) {
            if (i%4 == 0) {
                sb.append(Constants.LETTERS_UPPER_CASE.charAt((int) (Constants.LETTERS_UPPER_CASE.length() * Math.random())));
                continue;
            }
            if (i%3 == 0) {
            sb.append(Constants.CYRRILIC_LOWER_CASE.charAt((int) (Constants.CYRRILIC_LOWER_CASE.length() * Math.random())));
                continue;
            }
            if (i%2 == 0) {
            sb.append(Constants.LETTERS_LOWER_CASE.charAt((int) (Constants.LETTERS_LOWER_CASE.length() * Math.random())));
                continue;
            }
            sb.append(Constants.NUMBERS.charAt((int) (Constants.NUMBERS.length() * Math.random())));
        }
        return sb.toString();
    }

    public static int getRandomInt(int min, int max){
        int randomNum = ThreadLocalRandom.current().nextInt(min, max + 1);
        return randomNum;
    }
    public static int getRandomInt(){
        int randomNum = ThreadLocalRandom.current().nextInt(Integer.MIN_VALUE, Integer.MIN_VALUE);
        return randomNum;
    }
}

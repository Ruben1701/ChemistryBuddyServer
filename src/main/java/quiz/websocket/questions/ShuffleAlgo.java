package quiz.websocket.questions;

import java.security.SecureRandom;

public class ShuffleAlgo {
    public static void shuffleArray(int[] a) {
        int n = a.length;
        SecureRandom random = new SecureRandom();
        random.nextInt();
        for (int i = 0; i < n; i++) {
            int change = i + random.nextInt(n - i);
            swap(a, i, change);
        }
    }

    private static void swap(int[] a, int i, int change) {
        int helper = a[i];
        a[i] = a[change];
        a[change] = helper;
    }

    public String shuffleNumbers(int questioncount) {

        //Create array of question count
        int[] a = new int[questioncount];
        for (int i = 1; i < questioncount; ++i) {
            a[i] = i;
        }

        shuffleArray(a);
//        for (int i : a) {
//            System.out.println(i);
//        }

        int r = a[0];
        if (r == 0){
            r = 1;
        }
        return String.valueOf(r);
    }
}

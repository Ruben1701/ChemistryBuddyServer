package quiz.server.interfaces;

import java.security.SecureRandom;

public interface iShuffle {

    static void shuffleArray(int[] a) {
        int n = a.length;
        SecureRandom random = new SecureRandom();
        random.nextInt();
        for (int i = 0; i < n; i++) {
            int change = i + random.nextInt(n - i);
            iShuffle.swap(a, i, change);
        }
    }

    static void swap(int[] a, int i, int change) {
        int helper = a[i];
        a[i] = a[change];
        a[change] = helper;
    }

    String shuffleNumbers(int questioncount);
}

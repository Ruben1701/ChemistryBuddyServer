package quiz.server.logic;

import quiz.server.interfaces.iShuffle;

public class ShuffleLogic implements iShuffle {

    @Override
    public String shuffleNumbers(int questioncount) {

        //Create array of question count
        int[] a = new int[questioncount];
        for (int i = 1; i < questioncount; ++i) {
            a[i] = i;
        }

        iShuffle.shuffleArray(a);
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

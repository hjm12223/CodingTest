package programmers.sort.levelTwo;

import java.util.Arrays;

public class Hindex3 {
    public static void main(String[] args) {
        int solution = solution(new int[]{1, 1, 1, 2, 3, 4});
        System.out.println("solution = " + solution);
    }

    public static int solution(int[] citations) {
        Arrays.sort(citations);
        int length = citations.length;

        for (int i = 0; i < length; i++) {
            int hIndex = length - i;
            if (citations[i] >= hIndex) {
                return hIndex;
            }
        }

        return 0;
    }
}

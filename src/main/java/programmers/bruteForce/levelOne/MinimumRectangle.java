package programmers.bruteForce.levelOne;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class MinimumRectangle {
    public static void main(String[] args) {
        int solution = solution(new int[][]{{60, 50}, {30, 70}, {60, 30}, {80, 40}});
        System.out.println("solution = " + solution);
    }

    public static int solution(int[][] sizes) {
        int width = 0;
        int weight = 0;
        for (int[] size : sizes) {
            //둘중 작은값들 중에서도 가장 큰값을 구함
            int widthMin = Math.min(size[0], size[1]);
            int weightMax = Math.max(size[0],size[1]);
            if (widthMin > width) {
                width= widthMin;
            }
            if (weightMax > weight ){
                weight =weightMax;
            }
        }
        return weight*width;
    }
}
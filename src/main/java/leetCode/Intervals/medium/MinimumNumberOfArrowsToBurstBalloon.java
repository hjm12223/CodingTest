package leetCode.Intervals.medium;

import java.util.ArrayList;
import java.util.Arrays;

public class MinimumNumberOfArrowsToBurstBalloon {
    public static void main(String[] args) {
        int[][] points = new int[][]{{9, 12}, {1, 10}, {4, 11}, {8, 12}, {3, 9}, {6, 9}, {6, 7}};
        int minArrowShots = findMinArrowShots(points);
        System.out.println("minArrowShots = " + minArrowShots);
    }

    public static int findMinArrowShots(int[][] points) {
        Arrays.sort(points, (a, b) -> Integer.compare(a[1], b[1]));

        int result = 1;

        int previous = 0;

        for (int i = 1; i < points.length; i++) {
            if (points[i][0] > points[previous][1]) {
                result++;
                previous = i;
            }
        }
        return result;

    }
}
/**
 *
 * 1,6 2,8 7,12 10,16
 * length - 2 가 맞음
 *
 *
 * 1,2 2,3 2,3 4,5 4,5 4,5
 * length  = 6 - 4 이 됌
 * 그러면 실패
 *
 * 카운트가 3까지 올라감
 * 근데 2발을 쏘는거임
 * 그러면 안됌
 */
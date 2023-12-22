package leetCode.Intervals.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class insertInterval {
    public static void main(String[] args) {
        int[][] intervals = {{1, 5}};
        int[] newInterval = {2, 5};
        int[][] insert = insert(intervals, newInterval);
        System.out.println("insert = " + Arrays.deepToString(insert));
    }

    public static int[][] insert(int[][] intervals, int[] newInterval) {
        if (intervals == null || intervals.length == 0) {
            return new int[][]{newInterval};
        }

        List<int[]> mergedIntervals = new ArrayList<>();
        boolean isMerged = false;

        for (int i = 0 ; i < intervals.length ; i++){
            if (intervals[i][1] < newInterval[0]){
                mergedIntervals.add(intervals[i]);
            } else if (intervals[i][0] >  newInterval[1]) {
                if (!isMerged){
                    mergedIntervals.add(newInterval);
                    isMerged = true;
                }
                mergedIntervals.add(intervals[i]);
            }else {
                newInterval[0] = Math.min(newInterval[0],intervals[i][0]);
                newInterval[1] = Math.max(newInterval[1],intervals[i][1]);
            }
        }

        if (!isMerged) {
            mergedIntervals.add(newInterval);
        }

        return mergedIntervals.toArray(new int[0][]);
    }
}
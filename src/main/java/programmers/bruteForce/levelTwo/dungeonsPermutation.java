package programmers.bruteForce.levelTwo;

import java.util.Arrays;

public class dungeonsPermutation {
    public static void main(String[] args) {
        int solution = solution(40, new int[][]{{40, 20}, {10, 10}, {10, 10}, {10, 10}, {10, 10}});
        System.out.println("solution = " + solution);
    }

    public static int max = -1;
    public static int solution(int k, int[][] dungeons) {
        int answer= 0;
        boolean[] isVisited = new boolean[dungeons.length];
        int[][] output = new int[dungeons.length][dungeons[0].length];

        permu(0, dungeons.length,isVisited,output,dungeons,k);
        answer = max;
        return answer;
    }

    public static void permu(int depth, int r, boolean[] isVisited, int[][] output, int[][] dungeons, int k) {
        if (depth == r){
            System.out.println("r = " + r);
            int cnt = 0;
            for (int[] ints : output) {
                if (k >= ints[0]) {
                    k -= ints[1];
                    cnt++;
                }
            }
            System.out.println("cnt = " + cnt);
            max = Math.max(max,cnt);
            return;
        }
        for (int i = 0 ; i< dungeons.length; i++){
            if (!isVisited[i]){
                System.out.println("depth = " + depth);
                System.out.println("isVisited = " + Arrays.toString(isVisited));
                isVisited[i] = true;
                output[depth] = dungeons[i];
                permu(depth+1,r,isVisited,output,dungeons,k);
                isVisited[i] = false;
            }
        }
}
}
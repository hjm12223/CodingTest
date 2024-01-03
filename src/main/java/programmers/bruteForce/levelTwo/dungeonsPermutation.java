package programmers.bruteForce.levelTwo;

public class dungeonsPermutation {
    public static void main(String[] args) {
        int solution = solution(40, new int[][]{{40, 20}, {10, 10}, {10, 10}, {10, 10}, {10, 10}});
        System.out.println("solution = " + solution);
    }
    static int max = -1;
    public static int solution(int k, int[][] dungeons) {
        boolean[] isVisited = new boolean[dungeons.length];
        int[][] output = new int[dungeons.length][dungeons[0].length];
        permu(dungeons,isVisited,dungeons.length,0,output,k);
        return max;
    }

    private static void permu(int[][] dungeons, boolean[] isVisited, int r, int depth, int[][] output, int k) {
        if (r == depth){
            int cnt = 0;
            for(int i = 0 ; i< r ; i++){
                if (k >= output[i][0]){
                    k -= output[i][1];
                    cnt++;
                }
            }
            max = Math.max(cnt,max);
            return;
        }
        for(int i = 0 ; i< r ; i++){
            if (!isVisited[i]){
                isVisited[i] = true;
                output[depth] = dungeons[i];
                permu(dungeons,isVisited,r,depth+1,output,k);
                isVisited[i] = false;
            }
        }
    }

}
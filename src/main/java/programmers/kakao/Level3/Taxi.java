package programmers.kakao.Level3;

import javax.lang.model.SourceVersion;
import java.util.*;

public class Taxi {
    public static void main(String[] args) {
//        System.out.println(solution(6,4,6,2, new int[][]{{4, 1, 10}, {3, 5, 24}, {5, 6, 2}, {3, 1, 41}, {5, 1, 24}, {4, 6, 50}, {2, 4, 66}, {2, 3, 22}, {1, 6, 25}}));
//        int solution = solution(6, 4, 5, 6, new int[][]{{2, 6, 6}, {6, 3, 7}, {4, 6, 7}, {6, 5, 11}, {2, 5, 12}, {5, 3, 20}, {2, 4, 8}, {4, 3, 9}});
        System.out.println(solution(6,4,5,6,new int[][]{{2, 6, 6}, {6, 3, 7}, {4, 6, 7}, {6, 5, 11}, {2, 5, 12}, {5, 3, 20}, {2, 4, 8}, {4, 3, 9}}));
//        System.out.println("solution = " + solution);
}
    public static int solution(int n, int s, int a, int b, int[][] fares) {
        int answer = Integer.MAX_VALUE;
        int[][] dist = new int[n+1][n+1];
        for (int i = 1 ; i<= n ; i++){
            for (int j = 1 ; j <= n ; j++){
                if (i==j) dist[i][j] = 0;
                else dist[i][j] = Integer.MAX_VALUE;
            }
        }
        for (int[] fare : fares) {
            int from = fare[0];
            int to = fare[1];
            int value = fare[2];
            dist[from][to] = value;
            dist[to][from] = value;

        }
        System.out.println("dist = " + Arrays.deepToString(dist));
        for (int k = 1 ; k <= n ; k++){
            for (int i = 1 ; i <= n ; i++){
                for (int j = 1 ; j <= n ; j++){
                    if (dist[i][k] != Integer.MAX_VALUE && dist[k][j] != Integer.MAX_VALUE){
                        dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                    }
                }
            }
        }
        for (int i = 1 ; i<= n ; i++){
         answer = Math.min(answer,dist[s][i] + dist[i][a] + dist[i][b]);
        }
        return answer;
    }

    }
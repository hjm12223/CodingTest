package programmers.kakao.Level3;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class DontCrushedBuilding {
    public static void main(String[] args) {
        int solution = solution(new int[][]{{5, 5, 5, 5, 5}, {5, 5, 5, 5, 5}, {5, 5, 5, 5, 5}, {5, 5, 5, 5, 5}}, new int[][]{{1, 0, 0, 3, 4, 4}, {1, 2, 0, 2, 3, 2}, {2, 1, 0, 3, 1, 2}, {1, 0, 1, 3, 3, 1}});// 1 = 공격 2 = 회복
        System.out.println("solution = " + solution);
    }
    public static int solution(int[][] board, int[][] skill) {
        /*

        1000*1000* 250000 = 2.5 * 10^11  = 250,000,000,000 절대 못품

        1,000,000^2
        dfs, bfs
        범위로만 지정이 가능
        bfs>
         */
        int answer = 0;
        Queue<Skill> q = new LinkedList<>();
        for (int[] sk : skill) {
            q.add(new Skill(sk[0],sk[1],sk[2],sk[3],sk[4],sk[5]));
        }
        while (!q.isEmpty()){
            Skill curr = q.poll();
            if (curr.type ==1 ) {
                for (int i = curr.c1; i <= curr.c2; i++) {
                    for (int j = curr.r1; j <= curr.r2; j++) {
                        board[i][j] -= curr.value;;
                    }
                }
            }else{
                for (int i = curr.c1; i <= curr.c2; i++) {
                    for (int j = curr.r1; j <= curr.r2; j++) {
                        board[i][j] += curr.value;;
                    }
                }
            }
        }
        for (int  i = 0 ; i< board.length; i++){
            for (int j = 0 ; j< board[i].length; j++){
                if (board[i][j] > 0) answer++;
            }
        }
        return answer;
    }
    public static class Skill{
        public Skill(int type, int c1, int r1, int c2, int r2, int value) {
            this.type = type;
            this.c1 = c1;
            this.r1 = r1;
            this.c2 = c2;
            this.r2 = r2;
            this.value = value;
        }

        int type;
        int c1;
        int r1;
        int c2;
        int r2;
        int value;
    }
}
